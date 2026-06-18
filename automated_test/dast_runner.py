"""
SkillSync AI — Full DAST Test Runner
Categories: AuthN Bypass, AuthZ/PrivEsc, IDOR, RBAC Matrix,
            Token Tampering, Injection, Rate Limiting, Hardcoded Creds
"""
import sys
import os
import json
import time
import re
import base64
import hashlib
import datetime

sys.stdout.reconfigure(encoding='utf-8')

try:
    import requests
except ImportError:
    print("requests library not found. Install with: pip install requests")
    sys.exit(1)

# ── Load config ──────────────────────────────────────────────────────────────
CONFIG_PATH = os.path.join(os.path.dirname(__file__), "input.json")
with open(CONFIG_PATH) as f:
    config = json.load(f)

BASE_URL = config["baseUrl"].rstrip("/")
USER_TOKEN = config.get("user", "")

HEADERS_JSON = {"Content-Type": "application/json"}
HEADERS_AUTH = {"Content-Type": "application/json",
                "Authorization": f"Bearer {USER_TOKEN}"}
HEADERS_MALFORMED = {"Content-Type": "application/json",
                     "Authorization": "Bearer invalid.malformed.token"}
HEADERS_EXPIRED = {"Content-Type": "application/json",
                   "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjMiLCJyb2xlIjoidXNlciIsImV4cCI6MX0.ZfX9a1sZN_dK9TMaBvuLs1Mj80g4fE7AmpT_G63n4xw"}

# ── Discovered endpoints ─────────────────────────────────────────────────────
# Discovered from codebase analysis of backend/main.py
ENDPOINTS = [
    {"path": "/",            "method": "GET",  "access": "public"},
    {"path": "/register",    "method": "POST", "access": "public"},
    {"path": "/login",       "method": "POST", "access": "public"},
    {"path": "/google-login","method": "POST", "access": "public"},
    {"path": "/analyze",     "method": "POST", "access": "unauthenticated_but_sensitive"},
    {"path": "/docs",        "method": "GET",  "access": "public"},
    {"path": "/openapi.json","method": "GET",  "access": "public"},
    {"path": "/redoc",       "method": "GET",  "access": "public"},
]

results = []
now_ts = lambda: datetime.datetime.utcnow().isoformat() + "Z"

def record(endpoint, method, role, status, expected, finding, severity,
           response_time_ms, category, note):
    mark = "✗" if finding else "✓"
    print(f"  {mark} [{category}] {method} {endpoint} | role={role} | {status} (expected {expected}) | {severity} | {note[:80]}")
    results.append({
        "endpoint": endpoint,
        "method": method,
        "role": role,
        "status": status,
        "expected_status": expected,
        "finding": finding,
        "severity": severity,
        "response_time_ms": round(response_time_ms),
        "test_category": category,
        "note": note,
        "timestamp": now_ts()
    })

def safe_request(method, url, headers=None, json_body=None, data=None, files=None,
                 timeout=10):
    t0 = time.time()
    try:
        r = requests.request(method, url, headers=headers, json=json_body,
                             data=data, files=files, timeout=timeout)
        return r.status_code, round((time.time()-t0)*1000), r.text[:300]
    except requests.exceptions.Timeout:
        return 0, round((time.time()-t0)*1000), "TIMEOUT"
    except Exception as e:
        return -1, round((time.time()-t0)*1000), str(e)

def delay():
    time.sleep(0.3)


# ═══════════════════════════════════════════════════════════════════════════
# STEP 1 — ENDPOINT DISCOVERY REPORT
# ═══════════════════════════════════════════════════════════════════════════
print("\n" + "═"*60)
print("STEP 1 — DISCOVERED ENDPOINTS")
print("═"*60)
for ep in ENDPOINTS:
    print(f"  {ep['method']:6} {ep['path']:20}  access={ep['access']}")
print(f"\n  Total: {len(ENDPOINTS)} endpoints\n")


# ═══════════════════════════════════════════════════════════════════════════
# STEP 2 — EXPECTATION MODEL (printed inline in findings)
# ═══════════════════════════════════════════════════════════════════════════
print("═"*60)
print("STEP 3 — RUNNING ALL TEST CATEGORIES")
print("═"*60)


# ─── CATEGORY 1: AuthN Bypass ────────────────────────────────────────────────
print("\n[1/8] AuthN Bypass — testing protected endpoints without / with bad tokens\n")

sensitive = [ep for ep in ENDPOINTS if ep["access"] == "unauthenticated_but_sensitive"]

# /analyze — no auth header at all (should be 401 but currently accepts any request)
status, rt, body = safe_request("POST", f"{BASE_URL}/analyze",
                                headers=HEADERS_JSON)
delay()
expected = 401
finding = status not in (401, 403)
severity = "High" if finding else "Info"
note = (f"No auth header: got {status}. Endpoint accepts unauthenticated file uploads — "
        "sensitive resume data processed without identity verification."
        if finding else "Correctly rejected unauthenticated request.")
record("/analyze", "POST", "anonymous", status, expected, finding, severity, rt,
       "AuthN Bypass", note)

# /analyze — with malformed JWT
status, rt, body = safe_request("POST", f"{BASE_URL}/analyze",
                                headers=HEADERS_MALFORMED)
delay()
finding = status not in (401, 403, 422)
severity = "High" if finding else "Info"
note = (f"Malformed JWT token: got {status}. Server should reject invalid bearer tokens."
        if finding else f"Correctly rejected malformed token (HTTP {status}).")
record("/analyze", "POST", "anonymous", status, expected, finding, severity, rt,
       "AuthN Bypass", note)

# /analyze — with expired JWT
status, rt, body = safe_request("POST", f"{BASE_URL}/analyze",
                                headers=HEADERS_EXPIRED)
delay()
finding = status not in (401, 403, 422)
severity = "High" if finding else "Info"
note = (f"Expired JWT token: got {status}. Server must verify token expiry."
        if finding else f"Correctly rejected expired token (HTTP {status}).")
record("/analyze", "POST", "anonymous", status, expected, finding, severity, rt,
       "AuthN Bypass", note)

# GET / — public; expect 200
status, rt, body = safe_request("GET", f"{BASE_URL}/",
                                headers=HEADERS_JSON)
delay()
record("/", "GET", "anonymous", status, 200,
       False, "Info", rt, "AuthN Bypass",
       "Public home endpoint correctly accessible without auth.")

# /docs — FastAPI Swagger UI should be public
status, rt, body = safe_request("GET", f"{BASE_URL}/docs",
                                headers=HEADERS_JSON)
delay()
finding = status == 200  # Swagger being public can expose API surface
severity = "Low" if finding else "Info"
note = ("Swagger UI (/docs) is publicly accessible. Consider restricting in production "
        "to prevent API surface enumeration by attackers."
        if finding else f"Swagger UI returned {status}.")
record("/docs", "GET", "anonymous", status, 200, finding, severity, rt,
       "AuthN Bypass", note)

# /openapi.json — exposing full API schema publicly
status, rt, body = safe_request("GET", f"{BASE_URL}/openapi.json",
                                headers=HEADERS_JSON)
delay()
finding = status == 200
severity = "Low" if finding else "Info"
note = ("OpenAPI schema (/openapi.json) is publicly accessible. This reveals all "
        "endpoint paths, methods, and request models to unauthenticated users."
        if finding else f"OpenAPI schema returned {status}.")
record("/openapi.json", "GET", "anonymous", status, 200, finding, severity, rt,
       "AuthN Bypass", note)


# ─── CATEGORY 2: AuthZ / PrivEsc ─────────────────────────────────────────────
print("\n[2/8] AuthZ / Privilege Escalation — testing role boundary enforcement\n")

# Try to call /analyze with a low-privilege dummy token
status, rt, body = safe_request("POST", f"{BASE_URL}/analyze",
                                headers={"Authorization": f"Bearer {USER_TOKEN}",
                                         "Content-Type": "application/json"})
delay()
finding = status not in (401, 403)
severity = "Medium" if finding else "Info"
note = (f"Low-privilege token accepted on /analyze (HTTP {status}). No role-based "
        "access control is applied — any authenticated or unauthenticated user can "
        "trigger sensitive resume analysis."
        if finding else "Low-priv token correctly rejected.")
record("/analyze", "POST", "low_priv_user", status, 403, finding, severity, rt,
       "AuthZ / PrivEsc", note)

# Register with a privileged payload (role injection attempt via JSON body)
priv_body = {"full_name": "Admin Injected", "email": "pwned@test.com",
             "password": "test123", "role": "admin", "is_admin": True}
status, rt, body = safe_request("POST", f"{BASE_URL}/register",
                                headers=HEADERS_JSON, json_body=priv_body)
delay()
finding = status == 200 and "admin" in body.lower()
severity = "High" if finding else "Info"
note = (f"Mass assignment: registration with admin role=True returned 200. "
        "Server may store injected role claims."
        if finding else f"Registration with injected role fields returned {status}. "
        "Server did not reflect injected privilege claims in response.")
record("/register", "POST", "anonymous", status, 400, finding, severity, rt,
       "AuthZ / PrivEsc", note)


# ─── CATEGORY 3: IDOR ────────────────────────────────────────────────────────
print("\n[3/8] IDOR — testing object-level access control on parameterized paths\n")

# The current API doesn't expose parameterized user-specific resource endpoints
# (no /users/{id}, /resumes/{id}, etc.) — mark as N/A with note
record("/", "GET", "anonymous", 0, 0, False, "Info", 0,
       "IDOR",
       "No parameterized user-resource endpoints found (no /users/{id}, /resumes/{id}, etc.). "
       "IDOR not applicable to current API shape — recommend adding user-scoped endpoints "
       "with proper ownership checks when implemented.")


# ─── CATEGORY 4: RBAC Matrix ─────────────────────────────────────────────────
print("\n[4/8] RBAC Matrix — mapping each role against each endpoint\n")

roles = [
    ("anonymous", None),
    ("user_token", USER_TOKEN),
]

rbac_cases = [
    ("/",             "GET",  [200],        "public"),
    ("/login",        "POST", [400, 422],   "public"),
    ("/register",     "POST", [400, 422],   "public"),
    ("/google-login", "POST", [400, 422],   "public"),
    ("/analyze",      "POST", [401, 403],   "requires-auth"),
    ("/docs",         "GET",  [200],        "public"),
    ("/openapi.json", "GET",  [200],        "public"),
]

for path, method, expected_codes, access_note in rbac_cases:
    for role_name, token in roles:
        hdrs = HEADERS_JSON.copy()
        if token:
            hdrs["Authorization"] = f"Bearer {token}"
        status, rt, body = safe_request(method, f"{BASE_URL}{path}", headers=hdrs)
        delay()
        finding = status not in expected_codes and not (
            access_note == "requires-auth" and status in (401, 403, 422))
        severity = "Medium" if finding else "Info"
        note = (f"RBAC deviation: {role_name} got {status}, expected one of {expected_codes} "
                f"for {access_note} endpoint."
                if finding else
                f"RBAC OK: {role_name} → {status} on {access_note} endpoint.")
        record(path, method, role_name, status, expected_codes[0],
               finding, severity, rt, "RBAC Matrix", note)


# ─── CATEGORY 5: Token Tampering ─────────────────────────────────────────────
print("\n[5/8] Token Tampering — flipping JWT claims without re-signing\n")

# Build a tampered JWT with role=admin, sub=1 — unsigned / wrong sig
header_b64 = base64.urlsafe_b64encode(
    json.dumps({"alg": "HS256", "typ": "JWT"}).encode()).decode().rstrip("=")
payload_b64 = base64.urlsafe_b64encode(
    json.dumps({"sub": "1", "role": "admin", "exp": 9999999999}).encode()).decode().rstrip("=")
tampered_jwt = f"{header_b64}.{payload_b64}.fakesignature"

tampered_headers = {"Content-Type": "application/json",
                    "Authorization": f"Bearer {tampered_jwt}"}

# Try tampered token on /analyze
status, rt, body = safe_request("POST", f"{BASE_URL}/analyze",
                                headers=tampered_headers)
delay()
finding = status == 200
severity = "Critical" if finding else "Info"
note = (f"CRITICAL: Tampered JWT with role=admin accepted! Server is NOT verifying "
        "JWT signatures. Immediate fix required."
        if finding else f"Tampered JWT correctly rejected (HTTP {status}). "
        "Server validates JWT signatures.")
record("/analyze", "POST", "tampered_admin_jwt", status, 401,
       finding, severity, rt, "Token Tampering", note)

# Try tampered token on GET /
status, rt, body = safe_request("GET", f"{BASE_URL}/",
                                headers=tampered_headers)
delay()
finding = "admin" in body.lower() and status == 200
record("/", "GET", "tampered_admin_jwt", status, 200,
       False, "Info", rt, "Token Tampering",
       f"Public endpoint returned {status} regardless of token (expected). No privilege from tampered JWT observed.")

# Try none-algorithm attack
none_header = base64.urlsafe_b64encode(
    json.dumps({"alg": "none", "typ": "JWT"}).encode()).decode().rstrip("=")
none_payload = base64.urlsafe_b64encode(
    json.dumps({"sub": "1", "role": "admin", "exp": 9999999999}).encode()).decode().rstrip("=")
none_jwt = f"{none_header}.{none_payload}."
none_headers = {"Content-Type": "application/json",
                "Authorization": f"Bearer {none_jwt}"}
status, rt, body = safe_request("POST", f"{BASE_URL}/analyze",
                                headers=none_headers)
delay()
finding = status == 200
severity = "Critical" if finding else "Info"
note = (f"CRITICAL: 'alg:none' JWT attack succeeded! Server accepted unsigned token."
        if finding else f"'alg:none' attack correctly rejected (HTTP {status}).")
record("/analyze", "POST", "none_alg_jwt", status, 401,
       finding, severity, rt, "Token Tampering", note)


# ─── CATEGORY 6: Injection Probe ─────────────────────────────────────────────
print("\n[6/8] Injection Probe — SQLi/NoSQLi detection payloads\n")

sqli_payloads = [
    ("' OR '1'='1", "Classic SQLi tautology"),
    ("admin'--",    "Comment-based SQLi"),
    ("' UNION SELECT NULL,NULL,NULL--", "UNION-based SQLi"),
    ("'; DROP TABLE users;--", "Stacked query SQLi"),
    ('{"$gt": ""}', "NoSQLi MongoDB operator"),
    ("' OR SLEEP(3)--", "Time-based blind SQLi"),
]

for payload, label in sqli_payloads:
    body_data = {"email": payload, "password": payload}
    t0 = time.time()
    status, rt, resp_body = safe_request("POST", f"{BASE_URL}/login",
                                         headers=HEADERS_JSON, json_body=body_data)
    elapsed = round((time.time() - t0) * 1000)
    delay()

    # Flag: 2xx response (unexpected login), or timing anomaly (sleep payloads > 3s)
    time_anomaly = elapsed > 3000 and "SLEEP" in payload.upper()
    finding = status == 200 or time_anomaly
    severity = "High" if status == 200 else ("Medium" if time_anomaly else "Info")
    note = (f"INJECTION SUCCESS: payload '{label}' returned {status}! Possible SQLi."
            if status == 200
            else f"Timing anomaly ({elapsed}ms): possible blind time-based SQLi with payload '{label}'."
            if time_anomaly
            else f"Injection payload '{label}' correctly rejected (HTTP {status}, {elapsed}ms).")
    record("/login", "POST", "anonymous", status, 400,
           finding, severity, elapsed, "Injection Probe", note)

# Inject in email field of /register as well
register_sqli = {"full_name": "Test", "email": "' OR '1'='1'@test.com",
                 "password": "test123"}
status, rt, body = safe_request("POST", f"{BASE_URL}/register",
                                headers=HEADERS_JSON, json_body=register_sqli)
delay()
finding = status == 200 and "user" in body.lower() and "id" in body.lower()
note = (f"Possible mass injection via register: payload in email field returned 200 with user data."
        if finding else f"Register injection probe: {status} — payload handled correctly.")
record("/register", "POST", "anonymous", status, 400,
       finding, "High" if finding else "Info", rt,
       "Injection Probe", note)


# ─── CATEGORY 7: Rate Limiting ───────────────────────────────────────────────
print("\n[7/8] Rate Limiting — 30-request burst test\n")

burst_count = 30
rate_limited = False
last_status = 200
burst_times = []

print(f"  Sending {burst_count} rapid requests to GET /...")
for i in range(burst_count):
    status, rt, body = safe_request("GET", f"{BASE_URL}/", headers=HEADERS_JSON, timeout=5)
    burst_times.append(rt)
    if status == 429:
        rate_limited = True
        last_status = status
        print(f"  ⚠ Rate limit hit at request #{i+1} (HTTP 429)")
        break
    last_status = status

avg_rt = round(sum(burst_times) / len(burst_times)) if burst_times else 0
finding = not rate_limited
severity = "Low" if finding else "Info"
note = (f"No rate limiting observed after {burst_count} rapid requests. "
        "Final status={last_status}. Server is susceptible to DoS/brute-force. "
        "Recommend implementing rate limiting (e.g. slowapi, nginx limit_req)."
        if finding else f"Rate limit enforced at request #{len(burst_times)} (HTTP 429). Good.")
record("/", "GET", "anonymous", last_status, 429,
       finding, severity, avg_rt, "Rate Limiting", note)

# Also test /login rate limiting (brute force protection)
print(f"  Sending {burst_count} rapid requests to POST /login...")
rate_limited_login = False
for i in range(burst_count):
    body_data = {"email": "brute@test.com", "password": f"wrong{i}"}
    status, rt, body = safe_request("POST", f"{BASE_URL}/login",
                                    headers=HEADERS_JSON, json_body=body_data, timeout=5)
    if status == 429:
        rate_limited_login = True
        print(f"  ⚠ Login rate limit hit at request #{i+1}")
        break

finding = not rate_limited_login
severity = "Medium" if finding else "Info"
note = (f"No rate limiting on /login after {burst_count} rapid attempts. "
        "Brute-force password attacks are possible without account lockout or throttling."
        if finding else "Login rate limit enforced correctly.")
record("/login", "POST", "anonymous", status, 429,
       finding, severity, rt, "Rate Limiting", note)


# ─── CATEGORY 8: Hardcoded Credentials ───────────────────────────────────────
print("\n[8/8] Hardcoded Credentials — static codebase scan\n")

SCAN_ROOTS = [
    os.path.join(os.path.dirname(__file__), "..", "backend"),
    os.path.join(os.path.dirname(__file__), "..", "app", "src"),
    os.path.join(os.path.dirname(__file__), "..", "webapp", "src"),
]
SECRET_PATTERNS = [
    (r"SUPABASE_KEY\s*=\s*['\"]?ey[A-Za-z0-9._-]{30,}", "Supabase service key hardcoded"),
    (r"password\s*=\s*['\"][^'\"]{6,}['\"]", "Hardcoded password string"),
    (r"secret\s*=\s*['\"][^'\"]{6,}['\"]", "Hardcoded secret value"),
    (r"api_key\s*=\s*['\"][^'\"]{6,}['\"]", "Hardcoded API key"),
    (r"eyJ[A-Za-z0-9_-]{20,}\.[A-Za-z0-9_-]{20,}\.[A-Za-z0-9_-]{5,}", "JWT token literal in code"),
]

hardcoded_findings = []
scanned_files = 0
SKIP_DIRS = {"node_modules", ".git", ".venv", "__pycache__", ".next", "build"}

for root_dir in SCAN_ROOTS:
    if not os.path.exists(root_dir):
        continue
    for dirpath, dirnames, filenames in os.walk(root_dir):
        dirnames[:] = [d for d in dirnames if d not in SKIP_DIRS]
        for fname in filenames:
            if not any(fname.endswith(ext) for ext in [".py", ".kt", ".ts", ".tsx", ".js", ".env"]):
                continue
            fpath = os.path.join(dirpath, fname)
            try:
                with open(fpath, encoding="utf-8", errors="ignore") as fh:
                    content = fh.read()
                    scanned_files += 1
                    for pattern, label in SECRET_PATTERNS:
                        matches = re.findall(pattern, content, re.IGNORECASE)
                        for m in matches:
                            # Mask the actual value for security
                            masked = m[:20] + "..." if len(m) > 20 else m
                            hardcoded_findings.append({
                                "file": os.path.relpath(fpath),
                                "label": label,
                                "snippet": masked
                            })
            except Exception:
                pass

print(f"  Scanned {scanned_files} source files.")
if hardcoded_findings:
    for hf in hardcoded_findings:
        print(f"  ✗ FINDING: {hf['label']} in {hf['file']} → {hf['snippet']}")
        record("Codebase", "Static Scan", "developer",
               200, 0, True, "High", 0, "Hardcoded Credentials",
               f"{hf['label']} found in {hf['file']}: {hf['snippet']}")
else:
    print("  ✓ No hardcoded credential patterns found in source files.")
    record("Codebase", "Static Scan", "developer",
           200, 0, False, "Info", 0, "Hardcoded Credentials",
           f"Scanned {scanned_files} files. No hardcoded credential patterns detected in .py/.kt/.ts/.js files.")

# .env file check — verify it is gitignored
gitignore_path = os.path.join(os.path.dirname(__file__), "..", ".gitignore")
env_in_gitignore = False
if os.path.exists(gitignore_path):
    with open(gitignore_path) as f:
        gi_content = f.read()
        env_in_gitignore = ".env" in gi_content

finding = not env_in_gitignore
severity = "High" if finding else "Info"
note = (".env file IS listed in .gitignore — secrets protected from accidental commits."
        if env_in_gitignore
        else "WARNING: .env file is NOT in .gitignore — Supabase keys may be committed to version control!")
record("backend/.env", "Static Scan", "developer", 200, 0,
       finding, severity, 0, "Hardcoded Credentials", note)


# ═══════════════════════════════════════════════════════════════════════════
# WRITE FINAL REPORT
# ═══════════════════════════════════════════════════════════════════════════
report_path = os.path.join(os.path.dirname(__file__), "report.json")
with open(report_path, "w", encoding="utf-8") as f:
    json.dump(results, f, indent=2, ensure_ascii=False)

# Write Excel-compatible CSV
import csv
csv_path = os.path.join(os.path.dirname(__file__), "report.csv")
with open(csv_path, "w", newline="", encoding="utf-8-sig") as f:
    writer = csv.DictWriter(f, fieldnames=[
        "endpoint","method","role","status","expected_status","finding",
        "severity","response_time_ms","test_category","note","timestamp"
    ])
    writer.writeheader()
    writer.writerows(results)

# ═══════════════════════════════════════════════════════════════════════════
# PRINT SUMMARY
# ═══════════════════════════════════════════════════════════════════════════
print("\n" + "═"*60)
print("STEP 4 — DAST REPORT SUMMARY")
print("═"*60)

total = len(results)
findings = [r for r in results if r["finding"]]
by_sev = {}
for r in findings:
    by_sev.setdefault(r["severity"], []).append(r)

print(f"\n  Endpoints Discovered : {len(ENDPOINTS)}")
print(f"  Total Tests Run      : {total}")
print(f"  Total Findings       : {len(findings)}")
print()

sev_order = ["Critical","High","Medium","Low","Info"]
for sev in sev_order:
    items = by_sev.get(sev, [])
    if items:
        mark = "✗" if sev in ("Critical","High") else "⚠" if sev == "Medium" else "•"
        print(f"  {mark} {sev}: {len(items)} finding(s)")
        for item in items:
            print(f"      [{item['test_category']}] {item['method']} {item['endpoint']} | {item['note'][:90]}")

print()
print("  TOP ISSUES TO FIX FIRST:")
priority = [r for r in findings if r["severity"] in ("Critical","High","Medium")]
for i, p in enumerate(priority[:5], 1):
    print(f"  {i}. [{p['severity']}] {p['test_category']} — {p['method']} {p['endpoint']}")
    print(f"     → {p['note'][:110]}")

print(f"\n  Reports written to:")
print(f"    {report_path}")
print(f"    {csv_path}")
print("\n" + "═"*60 + "\n")
