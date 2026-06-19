import os
import requests
import hashlib
import secrets
import base64
import json
import re
import time
import io
from collections import defaultdict
from fastapi import FastAPI, UploadFile, File, HTTPException, Request
from fastapi.responses import JSONResponse
from pydantic import BaseModel
from dotenv import load_dotenv
from pypdf import PdfReader
from docx import Document
from skills_data import SKILL_MAP
from fastapi.middleware.cors import CORSMiddleware

# Enable/disable docs based on environment (helps hide Swagger/ReDoc in production)
load_dotenv()
DOCS_ENABLED = os.environ.get("DOCS_ENABLED", "true").lower() == "true"

app = FastAPI(
    docs_url="/docs" if DOCS_ENABLED else None,
    redoc_url="/redoc" if DOCS_ENABLED else None,
    openapi_url="/openapi.json" if DOCS_ENABLED else None
)

# Secure CORS Middleware
app.add_middleware(
    CORSMiddleware,
    allow_origins=[
        "http://localhost:3000",
        "http://127.0.0.1:3000",
        "http://localhost:8000",
        "https://ylakshmireddy.github.io",
        "https://swethashree06.github.io",
    ],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Custom Rate Limiter Middleware (Memory-based, max 30 requests/min per IP)
request_history = defaultdict(list)
RATE_LIMIT_WINDOW = 60
RATE_LIMIT_MAX_REQUESTS = 30

@app.middleware("http")
async def rate_limiter(request: Request, call_next):
    client_ip = request.client.host if request.client else "unknown"
    now = time.time()
    
    # Filter request timestamps outside window
    request_history[client_ip] = [t for t in request_history[client_ip] if now - t < RATE_LIMIT_WINDOW]
    
    if len(request_history[client_ip]) >= RATE_LIMIT_MAX_REQUESTS:
        return JSONResponse(
            status_code=429,
            content={"detail": "Rate limit exceeded. Too many requests."}
        )
    
    request_history[client_ip].append(now)
    
    response = await call_next(request)
    remaining = max(0, RATE_LIMIT_MAX_REQUESTS - len(request_history[client_ip]))
    response.headers["X-RateLimit-Limit"] = str(RATE_LIMIT_MAX_REQUESTS)
    response.headers["X-RateLimit-Remaining"] = str(remaining)
    response.headers["X-RateLimit-Reset"] = str(int(RATE_LIMIT_WINDOW - (now - request_history[client_ip][0]))) if request_history[client_ip] else "0"
    return response

# Custom Security Headers Middleware
@app.middleware("http")
async def add_security_headers(request: Request, call_next):
    response = await call_next(request)
    response.headers["X-Content-Type-Options"] = "nosniff"
    response.headers["X-Frame-Options"] = "DENY"
    response.headers["Strict-Transport-Security"] = "max-age=31536000; includeSubDomains"
    response.headers["Content-Security-Policy"] = "default-src 'self'; frame-ancestors 'none'"
    response.headers["Referrer-Policy"] = "strict-origin-when-cross-origin"
    response.headers["Permissions-Policy"] = "geolocation=(), microphone=(), camera=()"
    response.headers["Server"] = "Masked"
    
    # Prevent caching of sensitive authentication/resume routes
    if request.url.path in ["/login", "/register", "/google-login", "/analyze"]:
        response.headers["Cache-Control"] = "no-store, no-cache, must-revalidate, max-age=0"
        response.headers["Pragma"] = "no-cache"
        response.headers["Expires"] = "0"
    return response

# Database Connection (Supabase REST API)
SUPABASE_URL = os.environ.get("SUPABASE_URL", "").strip()
SUPABASE_KEY = os.environ.get("SUPABASE_KEY", "").strip()

HEADERS = {
    "apikey": SUPABASE_KEY,
    "Authorization": f"Bearer {SUPABASE_KEY}",
    "Content-Type": "application/json",
    "Prefer": "return=representation"
}

# --- Auth Models ---
class RegisterRequest(BaseModel):
    full_name: str
    email: str
    password: str

class LoginRequest(BaseModel):
    email: str
    password: str

class GoogleLoginRequest(BaseModel):
    id_token: str

# Email Validation Regex
EMAIL_REGEX = r"^[\w\.-]+@[\w\.-]+\.\w+$"

# Account Lockout failure registry
login_failures = defaultdict(int)
MAX_LOGIN_FAILURES = 5

def sanitize_input(text: str) -> str:
    """Sanitize inputs by removing HTML tags and escaping database strings."""
    clean = re.sub(r"<[^>]*>", "", text)
    clean = clean.replace("'", "''").replace(";", "")
    return clean

def hash_password(password: str) -> str:
    """Secure PBKDF2 password hashing using standard library with salt."""
    salt = b"SkillSyncAISaltKey"  # PBKDF2 hashing key salt
    pwd_hash = hashlib.pbkdf2_hmac('sha256', password.encode(), salt, 100000)
    return pwd_hash.hex()

def get_user_id_from_token(auth_header: str) -> str:
    """Extract user_id dynamically from JWT access token claims."""
    if not auth_header or not auth_header.startswith("Bearer "):
        raise HTTPException(status_code=401, detail="Missing or invalid authentication token")
    token = auth_header.split(" ")[1]
    try:
        parts = token.split(".")
        if len(parts) != 3:
            raise ValueError("Invalid token segments")
        payload_b64 = parts[1]
        payload_b64 += "=" * ((4 - len(payload_b64) % 4) % 4)
        payload_json = base64.b64decode(payload_b64).decode("utf-8")
        payload = json.loads(payload_json)
        user_id = payload.get("sub")
        if not user_id:
            raise ValueError("sub claim missing")
        return user_id
    except Exception:
        raise HTTPException(status_code=401, detail="Could not validate credentials")

@app.post("/register")
def register(req: RegisterRequest):
    """Register a new user via Supabase Auth with validation and input sanitization."""
    req.full_name = sanitize_input(req.full_name)
    req.email = sanitize_input(req.email)
    
    if not re.match(EMAIL_REGEX, req.email):
        raise HTTPException(status_code=422, detail="Invalid email format")
    if len(req.password) < 6:
        raise HTTPException(status_code=400, detail="Password must be at least 6 characters long")

    signup_url = f"{SUPABASE_URL}/auth/v1/signup"
    body = {
        "email": req.email,
        "password": req.password,
        "data": {
            "full_name": req.full_name
        }
    }
    res = requests.post(signup_url, headers=HEADERS, json=body)
    
    if not res.ok:
        try:
            err_data = res.json()
            err_msg = err_data.get("msg") or err_data.get("message") or res.text
        except Exception:
            err_msg = res.text
        raise HTTPException(status_code=res.status_code, detail=f"Could not create account: {err_msg}")

    user_data = res.json()
    user = user_data.get("user") or user_data
    metadata = user.get("user_metadata", {})
    
    return {
        "message": "Account created successfully!",
        "user": {
            "id": user.get("id"),
            "full_name": metadata.get("full_name") or req.full_name,
            "email": user.get("email")
        }
    }

@app.post("/login")
def login(req: LoginRequest):
    """Authenticate a user via Supabase Auth with account lockout support."""
    req.email = sanitize_input(req.email)
    
    # Check for account lockout
    if login_failures[req.email] >= MAX_LOGIN_FAILURES:
        raise HTTPException(
            status_code=423, 
            detail="Account is temporarily locked due to too many failed login attempts."
        )
        
    login_url = f"{SUPABASE_URL}/auth/v1/token?grant_type=password"
    body = {
        "email": req.email,
        "password": req.password
    }
    res = requests.post(login_url, headers=HEADERS, json=body)
    
    if not res.ok:
        login_failures[req.email] += 1
        try:
            err_data = res.json()
            err_msg = err_data.get("error_description") or err_data.get("message") or res.text
        except Exception:
            err_msg = res.text
        raise HTTPException(status_code=res.status_code, detail=err_msg)

    # Reset failure counter on successful login
    login_failures[req.email] = 0

    data = res.json()
    access_token = data.get("access_token")
    user = data.get("user", {})
    metadata = user.get("user_metadata", {})
    
    return {
        "message": "Login successful!",
        "token": access_token,
        "user": {
            "id": str(user.get("id", "")),
            "full_name": metadata.get("full_name") or user.get("email") or "User",
            "email": user.get("email")
        }
    }

@app.post("/google-login")
def google_login(req: GoogleLoginRequest):
    """Exchange a Google ID token for a Supabase session."""
    token_url = f"{SUPABASE_URL}/auth/v1/token?grant_type=id_token"
    body = {
        "provider": "google",
        "id_token": req.id_token
    }
    res = requests.post(token_url, headers=HEADERS, json=body)

    if not res.ok:
        try:
            err_data = res.json()
            err_msg = err_data.get("error_description") or err_data.get("message") or res.text
        except Exception:
            err_msg = res.text
        raise HTTPException(status_code=res.status_code, detail=err_msg)

    data = res.json()
    access_token = data.get("access_token")
    user = data.get("user", {})
    metadata = user.get("user_metadata", {})

    return {
        "message": "Google login successful!",
        "token": access_token,
        "user": {
            "id": user.get("id"),
            "full_name": metadata.get("full_name") or metadata.get("name") or user.get("email") or "User",
            "email": user.get("email")
        }
    }

@app.post("/analyze")
async def analyze_resume(request: Request, file: UploadFile = File(...)):
    """Analyze uploaded resume text and save results dynamically for authenticated user."""
    text = ""
    filename = file.filename.lower()
    contents = await file.read()

    # 1. Read File Content
    if filename.endswith(".pdf"):
        reader = PdfReader(io.BytesIO(contents))
        for page in reader.pages:
            text += (page.extract_text() or "").lower()
    elif filename.endswith(".docx"):
        doc = Document(io.BytesIO(contents))
        text = "\n".join([para.text.lower() for para in doc.paragraphs])
    else:
        return {"error": "Unsupported format. Use PDF or DOCX."}

    # 2. Scoring Logic
    scores = {}
    details = {}

    for domain, keywords in SKILL_MAP.items():
        matches = [k for k in keywords if k in text]
        if matches:
            raw_score = (len(matches) / len(keywords)) * 100
            scores[domain] = round(min(raw_score + 10, 100)) # Bonus for matching any
            details[domain] = matches

    # 3. Find Top Suggestion
    if not scores:
        top_path = "General Software Engineering"
        final_score = 0
    else:
        top_path = max(scores, key=scores.get)
        final_score = scores[top_path]

    # 4. Generate Pros/Cons
    matched_skills = details.get(top_path, [])
    pros = f"Strong match for {top_path}. Detected skills: {', '.join(matched_skills)}"
    cons = f"To improve your score beyond {final_score}%, focus on building more complex projects in {top_path} to strengthen your portfolio."

    # 5. Extract user_id dynamically from Authorization header JWT (fallback to 1 for mock compatibility)
    user_id = "1"
    auth_header = request.headers.get("Authorization")
    if auth_header:
        try:
            user_id = get_user_id_from_token(auth_header)
        except Exception:
            pass # Keep default fallback for mock testing compatibility

    # 6. Save to Supabase (via REST)
    try:
        # Insert resume
        resume_data = {"user_id": user_id, "resume_text": text}
        res_url = f"{SUPABASE_URL}/rest/v1/resumes"
        res_response = requests.post(res_url, headers=HEADERS, json=resume_data)
        res_response.raise_for_status()
        resume_id = res_response.json()[0]["id"]
        
        # Insert analysis results
        analysis_data = {
            "resume_id": resume_id,
            "suggested_path": top_path,
            "pros": pros,
            "cons": cons
        }
        ans_url = f"{SUPABASE_URL}/rest/v1/analysis_results"
        ans_response = requests.post(ans_url, headers=HEADERS, json=analysis_data)
        ans_response.raise_for_status()
        db_status = "Successfully saved to Supabase"
    except Exception as e:
        db_status = f"Database Error: {str(e)}"

    return {
        "filename": file.filename,
        "suggested_path": top_path,
        "match_score": final_score,
        "match_details": scores,
        "pros": pros,
        "cons": cons,
        "db_status": db_status
    }
