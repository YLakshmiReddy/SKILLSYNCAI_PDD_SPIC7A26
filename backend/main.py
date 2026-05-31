import os
import requests
import hashlib
import secrets
from fastapi import FastAPI, UploadFile, File, HTTPException
from fastapi.responses import JSONResponse
from pydantic import BaseModel
from dotenv import load_dotenv
from pypdf import PdfReader
from docx import Document
import io
from skills_data import SKILL_MAP

from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origin_regex=r"https?://.*",
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Database Connection (Supabase REST API)
load_dotenv()
SUPABASE_URL = os.environ.get("SUPABASE_URL", "").strip()
SUPABASE_KEY = os.environ.get("SUPABASE_KEY", "").strip()

HEADERS = {
    "apikey": SUPABASE_KEY,
    "Authorization": f"Bearer {SUPABASE_KEY}",
    "Content-Type": "application/json",
    "Prefer": "return=representation"
}

@app.get("/")
def home():
    return {"status": "Online", "total_domains": len(SKILL_MAP)}

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

def hash_password(password: str) -> str:
    """Simple SHA-256 hash — no extra dependencies needed."""
    return hashlib.sha256(password.encode()).hexdigest()

@app.post("/register")
def register(req: RegisterRequest):
    """Register a new user via Supabase Auth."""
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
    # Check if user is returned directly or inside a wrapper
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
    """Authenticate a user via Supabase Auth."""
    login_url = f"{SUPABASE_URL}/auth/v1/token?grant_type=password"
    body = {
        "email": req.email,
        "password": req.password
    }
    res = requests.post(login_url, headers=HEADERS, json=body)
    
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
async def analyze_resume(file: UploadFile = File(...)):
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
            # Score is (matches / total_keywords_in_domain) * 100
            # We'll also add a small base score if any match is found to make it feel better
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

    # 5. Save to Supabase (via REST)
    try:
        # Insert resume
        resume_data = {"user_id": 1, "resume_text": text}
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
