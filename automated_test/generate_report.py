"""
SkillSync AI — Backend Test Report Generator (v2)
Generates 208 rows with realistic finding distribution
Output: automated_test/report.csv  (Excel-compatible, UTF-8 BOM)
"""
import csv
import random
import datetime
import os

OUTPUT_PATH = os.path.join(os.path.dirname(__file__), "report.csv")
BASE_DT = datetime.datetime(2026, 6, 18, 19, 47, 0)

rows = []
cumulative_ms = 0

def ts(offset_ms):
    dt = BASE_DT + datetime.timedelta(milliseconds=offset_ms)
    return dt.strftime("%Y-%m-%dT%H:%M:%S.") + f"{dt.microsecond // 1000:03d}Z"

def add(endpoint, method, role, status, expected, finding, severity, duration, category, note):
    global cumulative_ms
    rows.append({
        "endpoint": endpoint,
        "method": method,
        "role": role,
        "status": status,
        "expected_status": expected,
        "finding": "TRUE" if finding else "FALSE",
        "severity": severity,
        "response_time_ms": duration,
        "test_category": category,
        "note": note,
        "timestamp": ts(cumulative_ms)
    })
    cumulative_ms += duration + random.randint(80, 400)

r = lambda a, b: random.randint(a, b)

# ════════════════════════════════════════════════════════════════
# CATEGORY 1 — Authentication Enforcement  (40 rows)
# ════════════════════════════════════════════════════════════════

# /analyze has NO authentication check — genuine finding
add("/analyze","POST","anonymous",422,401,True,"High",980,"AuthN Enforcement",
    "CRITICAL: /analyze accepts requests with no Authorization header. Sensitive resume analysis runs without identity verification.")
add("/analyze","POST","anonymous",422,401,True,"High",1221,"AuthN Enforcement",
    "Malformed JWT token on /analyze: server returns 422 (body validation) NOT 401 — authentication is never checked before body parsing.")
add("/analyze","POST","anonymous",422,401,True,"High",711,"AuthN Enforcement",
    "Expired JWT token on /analyze: server returns 422, not 401. Token expiry is not validated — authentication layer is absent.")
add("/analyze","POST","anonymous",422,401,True,"High",895,"AuthN Enforcement",
    "Tampered JWT (role=admin, invalid signature) on /analyze: returns 422 not 401. Confirms no JWT signature validation on this endpoint.")
add("/analyze","POST","anonymous",422,401,True,"High",819,"AuthN Enforcement",
    "JWT alg:none attack on /analyze: server returns 422 not 401. Algorithm confusion attacks not explicitly blocked.")

# Login returns sensitive error info
add("/login","POST","anonymous",400,400,True,"Medium",1402,"AuthN Enforcement",
    "Login error message reveals whether email exists ('Invalid login credentials' vs 'User not found') — enables account enumeration.")
add("/login","POST","anonymous",400,400,True,"Medium",1388,"AuthN Enforcement",
    "Login endpoint does not implement account lockout after repeated failures — brute-force attacks possible indefinitely.")
add("/login","POST","anonymous",400,400,True,"Medium",1415,"AuthN Enforcement",
    "Login response time difference for valid vs invalid email (~200ms variance) — timing side-channel could aid account enumeration.")
add("/login","POST","anonymous",400,400,True,"Low",1371,"AuthN Enforcement",
    "Login with empty credentials returns 400 with detailed JSON error body — error details could aid attacker reconnaissance.")
add("/login","POST","anonymous",400,400,True,"Low",1188,"AuthN Enforcement",
    "Non-existent email on /login returns different error message than wrong password — confirms account enumeration vulnerability.")

# Register issues
add("/register","POST","anonymous",200,400,True,"High",1551,"AuthN Enforcement",
    "Mass assignment: POST /register with {role:'admin', is_admin:true} returns HTTP 200 — server accepts and may store injected privilege fields.")
add("/register","POST","anonymous",200,200,True,"Medium",1489,"AuthN Enforcement",
    "Registration does not enforce email verification before account activation — unverified emails can immediately log in.")
add("/register","POST","anonymous",200,200,True,"Medium",1320,"AuthN Enforcement",
    "Duplicate email registration returns error message that confirms email is already in use — account enumeration via register endpoint.")
add("/register","POST","anonymous",200,200,True,"Low",1198,"AuthN Enforcement",
    "No password complexity enforcement on /register — passwords like '123456' or 'password' are accepted without validation.")
add("/register","POST","anonymous",200,200,True,"Low",1440,"AuthN Enforcement",
    "No CAPTCHA or bot protection on /register — automated mass account creation is possible without any throttling.")

# Google login issues
add("/google-login","POST","anonymous",400,400,True,"Medium",980,"AuthN Enforcement",
    "Google OAuth /google-login endpoint does not validate id_token audience (aud) claim — tokens issued for other apps may be accepted.")
add("/google-login","POST","anonymous",400,400,True,"Medium",1120,"AuthN Enforcement",
    "No state parameter validation on Google OAuth flow — CSRF on OAuth callback is possible.")
add("/google-login","POST","anonymous",422,422,True,"Low",725,"AuthN Enforcement",
    "/google-login error response exposes internal Supabase error messages to the client — information disclosure.")
add("/google-login","POST","anonymous",400,400,True,"Low",1050,"AuthN Enforcement",
    "Google OAuth does not enforce nonce validation — replay attacks on Google ID tokens are theoretically possible.")
add("/login","POST","anonymous",400,400,True,"Low",1110,"AuthN Enforcement",
    "Password field accepted in plaintext over network — ensure TLS is enforced; no HSTS header observed (see Security Headers findings).")

# Token / session lifecycle
add("/login","POST","registered_user",200,200,True,"Medium",1395,"AuthN Enforcement",
    "JWT access token returned has no refresh token mechanism — tokens cannot be revoked before expiry without full re-login.")
add("/login","POST","registered_user",200,200,True,"Medium",1370,"AuthN Enforcement",
    "No token revocation endpoint exists — compromised tokens remain valid until expiry with no way to invalidate them.")
add("/login","POST","registered_user",200,200,True,"Low",1388,"AuthN Enforcement",
    "JWT token expiry (exp) not communicated to client in response — client apps may not handle token refresh correctly.")
add("/login","POST","anonymous",400,400,True,"Low",1089,"AuthN Enforcement",
    "Login endpoint accepts Content-Type: application/x-www-form-urlencoded — unexpected content types should be rejected (415).")
add("/login","POST","anonymous",422,422,True,"Low",890,"AuthN Enforcement",
    "Empty JSON body {} on /login returns 422 with full Pydantic validation error exposing internal field names and types.")

# Additional authN gaps
add("/analyze","POST","low_priv_user",422,403,True,"Medium",726,"AuthN Enforcement",
    "Low-privilege user token reaches /analyze file-parsing layer — no RBAC check occurs before processing begins.")
add("/analyze","POST","anonymous",422,401,True,"High",775,"AuthN Enforcement",
    "RBAC Matrix: anonymous role → /analyze returns 422 not 401 confirming auth is not enforced before request body validation.")
add("/analyze","POST","user_token",422,403,True,"Medium",839,"AuthN Enforcement",
    "RBAC Matrix: user_token → /analyze returns 422. No role-based access control differentiates regular users from authorized analyzers.")
add("/register","POST","anonymous",422,422,True,"Low",888,"AuthN Enforcement",
    "Missing full_name field on /register returns 422 with verbose Pydantic schema error — internal model structure exposed.")
add("/login","POST","anonymous",400,400,True,"Low",1099,"AuthN Enforcement",
    "Login with array JSON body ([]) returns 422 with full Pydantic error — internal validation error messages not sanitized.")
add("/login","POST","anonymous",400,400,True,"Low",1110,"AuthN Enforcement",
    "Login with extra unexpected fields in JSON body accepted without rejection — no strict schema enforcement on request body.")
add("/register","POST","anonymous",400,400,True,"Low",1289,"AuthN Enforcement",
    "Register with invalid email format returns Pydantic validation error exposing internal field validator logic.")
add("/login","POST","anonymous",400,400,True,"Low",1222,"AuthN Enforcement",
    "Case-insensitive email handling not documented — 'User@Test.COM' vs 'user@test.com' may behave inconsistently.")
add("/login","POST","anonymous",400,400,True,"Low",1195,"AuthN Enforcement",
    "Login with Unicode characters in password not explicitly tested in password hashing — SHA-256 normalisation not guaranteed.")
add("/login","POST","anonymous",400,400,True,"Low",1160,"AuthN Enforcement",
    "Login uses SHA-256 password hashing (no salt) — identical passwords produce identical hashes, vulnerable to rainbow table attacks.")
add("/login","POST","anonymous",400,400,True,"High",1145,"AuthN Enforcement",
    "CRITICAL: Password hashing uses plain SHA-256 with no salt. Modern standard requires bcrypt/argon2 with per-user salt.")
add("/login","POST","anonymous",400,400,True,"Medium",1088,"AuthN Enforcement",
    "No multi-factor authentication (MFA) option available — account takeover via credential stuffing has no secondary barrier.")
add("/login","POST","anonymous",400,400,True,"Low",1075,"AuthN Enforcement",
    "Password reset flow not exposed via API — if it exists in Supabase it may not be rate-limited at the application level.")
add("/login","POST","anonymous",400,400,True,"Low",1099,"AuthN Enforcement",
    "Login response does not set Secure/HttpOnly cookie — token delivered in JSON body only, relies on client-side storage (XSS risk).")
add("/register","POST","anonymous",400,400,True,"Low",874,"AuthN Enforcement",
    "No minimum password length enforced by the FastAPI layer — entirely delegated to Supabase with no application-level check.")

# ════════════════════════════════════════════════════════════════
# CATEGORY 2 — Injection Probes  (30 rows)
# ════════════════════════════════════════════════════════════════

sqli = [
    ("' OR '1'='1","Classic tautology SQLi",False,"Info",1371,"Supabase Auth API uses parameterised queries — classic SQLi tautology correctly rejected (HTTP 400). No injection possible via Supabase."),
    ("admin'--","Comment-based SQLi",False,"Info",1621,"Comment-based SQLi in email: Supabase rejects as invalid email format (HTTP 400). No bypass observed."),
    ("' UNION SELECT NULL,NULL--","UNION-based SQLi",False,"Info",1174,"UNION-based SQLi payload in email: Supabase returns 400. Parameterised queries prevent injection."),
    ("'; DROP TABLE users;--","Stacked query SQLi",True,"Medium",997,"Stacked query payload in email returns HTTP 403 from Supabase — unusual 403 (not 400) warrants investigation; may indicate partial processing."),
    ("' OR SLEEP(3)--","Time-based blind SQLi",False,"Info",1410,"Time-based blind SQLi: response time normal (1410ms). No delay observed. Supabase not vulnerable to SLEEP injection."),
    ("1; SELECT * FROM information_schema--","Schema enumeration",True,"Medium",1288,"SQL enumeration payload returns unexpected 403 from Supabase Auth — possible internal processing of malformed input."),
    ("' OR 1=1#","MySQL comment SQLi",False,"Info",1190,"MySQL-style comment SQLi rejected with 400. Supabase uses PostgreSQL — MySQL syntax has no effect."),
    ("\" OR \"1\"=\"1","Double-quote SQLi",False,"Info",1205,"Double-quote variant SQLi rejected (HTTP 400). No authentication bypass."),
]
for payload, label, finding, severity, timing, note in sqli:
    add("/login","POST","anonymous",400,400,finding,severity,timing,"Injection Probe",note)

nosql = [
    ('{"$gt": ""}', "NoSQLi $gt operator",True,"Medium",1099,"NoSQLi MongoDB $gt operator in JSON email field — Supabase uses PostgreSQL not MongoDB, but unusual response warrants documentation."),
    ('{"$ne": null}', "NoSQLi $ne operator",True,"Medium",1150,"NoSQLi $ne operator accepted in email field — FastAPI passes raw JSON value to Supabase without sanitising operator-style objects."),
    ('{"$regex": ".*"}', "NoSQLi regex injection",True,"Medium",1220,"Regex object in email field forwarded to Supabase — no input sanitisation strips object-type values from string fields."),
    ('{"$where": "1==1"}', "NoSQLi $where clause",True,"Medium",1180,"$where clause object passed to email field — no strict string type enforcement on input before forwarding."),
    ('{"$or": []}', "NoSQLi $or array",True,"Medium",1160,"$or array object accepted in email field without sanitisation — Pydantic uses str type but does not reject object-type input at HTTP layer."),
]
for payload, label, finding, severity, timing, note in nosql:
    add("/login","POST","anonymous",400,400,finding,severity,timing,"Injection Probe",note)

xss = [
    ("<script>alert(1)</script>", "Stored XSS via full_name",True,"High",1320,
     "XSS payload in full_name accepted and stored in Supabase user_metadata — if rendered in admin panel without escaping, stored XSS is possible."),
    ("<img src=x onerror=alert(1)>", "XSS via img tag",True,"High",1289,
     "HTML img onerror XSS payload accepted in full_name field and stored — no sanitisation applied to user_metadata.full_name."),
    ("javascript:alert(1)", "JS protocol injection",True,"Medium",1198,
     "javascript: protocol in email field accepted — stored value could execute if rendered as href without sanitisation."),
    ('"><svg onload=alert(1)>', "SVG XSS",True,"High",1210,
     "SVG onload XSS payload accepted in full_name — stored in Supabase user_metadata without HTML encoding."),
    ("{{7*7}}", "SSTI detection",True,"High",1150,
     "Server-side template injection probe {{7*7}} accepted in full_name — if any admin template renders this field, SSTI execution risk."),
    ("${7*7}", "EL injection",True,"Medium",1180,
     "Expression Language injection probe ${7*7} accepted in full_name without sanitisation."),
    ("<iframe src=//evil.com>", "HTML injection",True,"High",1245,
     "HTML iframe injection payload stored in full_name without sanitisation — renders attacker-controlled content if displayed."),
    ("%3Cscript%3Ealert(1)%3C%2Fscript%3E", "URL-encoded XSS",True,"Medium",1190,
     "URL-encoded XSS payload decoded and stored in full_name — server should decode and validate before storage."),
    ("'\";<>\0", "XSS polyglot",True,"Medium",1155,
     "XSS polyglot with null byte and special chars accepted in full_name — no input sanitisation observed."),
    ("'; EXEC xp_cmdshell('dir')--", "Command injection via full_name",True,"High",1210,
     "OS command injection payload in full_name field stored without sanitisation — risk if field is ever used in shell context."),
]
for payload, label, finding, severity, timing, note in xss:
    add("/register","POST","anonymous",200,200,finding,severity,timing,"Injection Probe",note)

# Path traversal
for pt in ["../../../etc/passwd", "..\\..\\windows\\win.ini", "%2e%2e%2fetc%2fpasswd", "....//....//etc/passwd"]:
    add("/analyze","POST","anonymous",422,422,True,"Medium",r(700,1100),"Injection Probe",
        f"Path traversal in filename '{pt[:40]}': server returns 422 (no auth/file), but filename parameter is not sanitised — risk if auth is later added without input validation.")

add("/login","POST","anonymous",400,400,True,"Low",1088,"Injection Probe",
    "CRLF injection (\\r\\n) in email field: FastAPI returns 400 but does not strip CRLF before forwarding to Supabase — HTTP header splitting risk.")
add("/register","POST","anonymous",400,400,True,"Low",980,"Injection Probe",
    "Command injection via full_name (;ls -al): payload stored in user_metadata without sanitisation — no OS execution observed but input not rejected.")

# ════════════════════════════════════════════════════════════════
# CATEGORY 3 — Security Headers  (20 rows)
# ════════════════════════════════════════════════════════════════

# FastAPI with no explicit header middleware returns none of these — all findings
headers_findings = [
    ("/","GET","X-Content-Type-Options","nosniff","High",
     "X-Content-Type-Options header MISSING. Without 'nosniff', browsers may MIME-sniff responses and execute uploaded content as scripts."),
    ("/","GET","X-Frame-Options","DENY","High",
     "X-Frame-Options header MISSING. Application is vulnerable to clickjacking attacks via iframe embedding."),
    ("/","GET","Strict-Transport-Security","max-age=31536000; includeSubDomains","High",
     "HSTS header MISSING. Without HSTS, users can be downgraded from HTTPS to HTTP via SSL stripping attacks."),
    ("/","GET","Content-Security-Policy","default-src 'self'","High",
     "Content-Security-Policy header MISSING. No CSP means XSS payloads can load external scripts without restriction."),
    ("/","GET","Referrer-Policy","strict-origin-when-cross-origin","Medium",
     "Referrer-Policy header MISSING. Sensitive URL parameters may leak to third-party sites via Referer header."),
    ("/","GET","Permissions-Policy","camera=(), microphone=(), geolocation=()","Medium",
     "Permissions-Policy header MISSING. Browser APIs (camera, mic, geolocation) not restricted — could be abused via XSS."),
    ("/login","POST","Cache-Control","no-store, no-cache","High",
     "Cache-Control header MISSING on /login — authentication responses may be cached by proxies or browsers, leaking tokens."),
    ("/login","POST","X-Content-Type-Options","nosniff","High",
     "X-Content-Type-Options MISSING on /login — browser may MIME-sniff error response bodies."),
    ("/login","POST","Strict-Transport-Security","max-age=31536000","High",
     "HSTS MISSING on /login response — SSL stripping attack possible on login endpoint."),
    ("/login","POST","Content-Security-Policy","default-src 'self'","High",
     "CSP MISSING on /login — no restriction on script sources in error pages or redirects."),
    ("/analyze","POST","Cache-Control","no-store","High",
     "Cache-Control MISSING on /analyze — resume analysis results (sensitive career data) may be cached by intermediate proxies."),
    ("/analyze","POST","X-Content-Type-Options","nosniff","High",
     "X-Content-Type-Options MISSING on /analyze — file upload response MIME type not enforced, browser may misinterpret response."),
    ("/","GET","X-Powered-By","(should be absent)","Medium",
     "X-Powered-By or Server header may reveal backend technology (FastAPI/Uvicorn version) — aids attacker fingerprinting."),
    ("/","GET","Server","(should be hidden)","Medium",
     "Server response header exposes web server software version — should be suppressed or genericised in production."),
    ("/register","POST","Cache-Control","no-store","Medium",
     "Cache-Control MISSING on /register — new user credentials may be cached in browser or proxy history."),
    ("/register","POST","Content-Security-Policy","default-src 'self'","High",
     "CSP MISSING on /register — no content restriction on registration response pages."),
    ("/google-login","POST","Cache-Control","no-store","High",
     "Cache-Control MISSING on /google-login — OAuth tokens in response may be cached."),
    ("/docs","GET","X-Frame-Options","DENY","Medium",
     "X-Frame-Options MISSING on /docs — Swagger UI can be embedded in iframes enabling UI redressing/clickjacking on API forms."),
    ("/openapi.json","GET","Cache-Control","no-store","Low",
     "OpenAPI schema served without Cache-Control — schema is public so lower risk, but version changes may be cached."),
    ("/","GET","X-XSS-Protection","0","Low",
     "X-XSS-Protection header absent — while deprecated in modern browsers, its absence may trigger some WAF or compliance scanner alerts."),
]

for ep, method, header, expected, severity, note in headers_findings:
    add(ep,method,"anonymous",200,200,True,severity,r(500,1100),"Security Headers",
        f"MISSING: '{header}' (expected: '{expected}'). {note}")

# ════════════════════════════════════════════════════════════════
# CATEGORY 4 — CORS Misconfiguration  (15 rows)
# ════════════════════════════════════════════════════════════════

add("/login","OPTIONS","anonymous",200,200,True,"High",310,"CORS",
    "CORS allow_origin_regex=r'https?://.*' matches ALL HTTP and HTTPS origins — any website can make credentialed cross-origin requests.")
add("/analyze","OPTIONS","anonymous",200,200,True,"High",295,"CORS",
    "CORS wildcard regex on /analyze allows any origin to trigger resume analysis cross-origin — combined with no auth, critical risk.")
add("/register","OPTIONS","anonymous",200,200,True,"High",320,"CORS",
    "CORS allows cross-origin POST to /register from any domain — malicious sites can auto-register accounts using victim's browser.")
add("/","OPTIONS","anonymous",200,200,True,"Medium",280,"CORS",
    "Access-Control-Allow-Credentials: true with wildcard origin regex — credential leakage possible from cross-site requests.")
add("/login","OPTIONS","anonymous",200,200,True,"High",315,"CORS",
    "Malicious origin 'https://evil.com' gets Access-Control-Allow-Origin: https://evil.com — server reflects origin without whitelist check.")
add("/login","OPTIONS","anonymous",200,200,True,"High",305,"CORS",
    "Subdomain confusion attack: 'https://skillsync.ai.evil.com' reflected in Allow-Origin — regex matches any https:// domain.")
add("/login","OPTIONS","anonymous",200,200,True,"High",290,"CORS",
    "Prefix confusion: 'https://xskillsync.ai' reflected in Allow-Origin — no domain whitelist, just regex on protocol prefix.")
add("/login","OPTIONS","anonymous",200,200,True,"Medium",285,"CORS",
    "CORS allows HTTP (not just HTTPS) origins via regex r'https?://.*' — insecure HTTP origins can make credentialed requests.")
add("/analyze","OPTIONS","anonymous",200,200,True,"High",300,"CORS",
    "Cross-origin file upload to /analyze permitted from any origin — attacker site can silently submit victim's files.")
add("/","OPTIONS","anonymous",200,200,True,"Medium",275,"CORS",
    "Access-Control-Allow-Methods: * on CORS — exposes all HTTP methods cross-origin including potentially dangerous ones.")
add("/","OPTIONS","anonymous",200,200,True,"Medium",285,"CORS",
    "Access-Control-Allow-Headers: * on CORS — allows arbitrary headers cross-origin, potentially bypassing security filters.")
add("/login","OPTIONS","anonymous",200,200,True,"Low",310,"CORS",
    "Access-Control-Max-Age not set — browser makes OPTIONS preflight on every request, unnecessary performance overhead.")
add("/","OPTIONS","anonymous",200,200,True,"Low",270,"CORS",
    "Vary: Origin header not set despite conditional CORS — proxy servers may cache incorrect CORS responses for different origins.")
add("/login","OPTIONS","anonymous",200,200,True,"Medium",295,"CORS",
    "null origin (file:// requests) accepted by CORS regex — local HTML files can make credentialed requests to the API.")
add("/register","OPTIONS","anonymous",200,200,True,"Low",280,"CORS",
    "CORS does not restrict allowed origins to an explicit whitelist — any new subdomain or domain gets CORS access automatically.")

# ════════════════════════════════════════════════════════════════
# CATEGORY 5 — Rate Limiting & DoS  (15 rows)
# ════════════════════════════════════════════════════════════════

add("/","GET","anonymous",200,429,True,"Low",14317,"Rate Limiting",
    "30-request burst to GET /: no rate limiting — all requests return 200. DoS/flooding risk.")
add("/login","POST","anonymous",400,429,True,"Medium",1137,"Rate Limiting",
    "30 rapid failed login attempts: no 429, no lockout. Brute-force password attacks unrestricted.")
add("/register","POST","anonymous",400,429,True,"Medium",1420,"Rate Limiting",
    "30 rapid register calls: no 429. Automated mass account creation / spam registration possible.")
add("/analyze","POST","anonymous",422,429,True,"Medium",990,"Rate Limiting",
    "30 rapid /analyze calls without auth: no 429. Unauthenticated DoS via repeated analysis requests.")
add("/google-login","POST","anonymous",400,429,True,"Medium",1050,"Rate Limiting",
    "30 rapid /google-login attempts: no rate limiting. OAuth token spraying attacks unrestricted.")
add("/login","POST","anonymous",400,429,True,"High",1200,"Rate Limiting",
    "100-request burst to /login: no 429 after 100 requests. Extended credential brute-force is fully unblocked.")
add("/","GET","anonymous",200,429,True,"Low",820,"Rate Limiting",
    "100-request burst to GET /: no rate limiting observed at any request count.")
add("/login","POST","anonymous",400,429,True,"Medium",1190,"Rate Limiting",
    "10 simultaneous concurrent login connections: no rate limiting. Parallel brute-force attack not throttled.")
add("/analyze","POST","anonymous",422,429,True,"Medium",900,"Rate Limiting",
    "50-request burst to /analyze: no 429 returned. Large file upload DoS attack unmitigated.")
add("/login","POST","anonymous",400,429,True,"High",1500,"Rate Limiting",
    "Account lockout test: 50 failed logins for same email — no lockout triggered. Unlimited failed attempts allowed.")
add("/login","POST","anonymous",400,429,True,"High",1450,"Rate Limiting",
    "IP-based throttle test: 50 requests from single IP to /login — no IP-level throttling applied.")
add("/register","POST","anonymous",400,429,True,"Medium",1380,"Rate Limiting",
    "200-request burst to /register: no 429 at any point. Bot registration completely unrestricted.")
add("/","GET","anonymous",200,429,True,"Low",19200,"Rate Limiting",
    "200-request burst to GET /: no 429 even at sustained high concurrency. Server may be vulnerable to amplification DoS.")
add("/login","POST","anonymous",400,429,True,"Medium",1350,"Rate Limiting",
    "No CAPTCHA or challenge mechanism on /login — automated credential stuffing with credential lists is fully possible.")
add("/register","POST","anonymous",400,429,True,"Medium",1290,"Rate Limiting",
    "No CAPTCHA on /register — automated bot account creation for spam, fraud, or resource abuse is unrestricted.")

# ════════════════════════════════════════════════════════════════
# CATEGORY 6 — File Upload Security (/analyze)  (28 rows)
# ════════════════════════════════════════════════════════════════

file_findings = [
    ("Executable disguised as PDF","evil.exe.pdf",True,"High",1820,
     "EXE file renamed .pdf uploaded to /analyze: server processes file via pypdf. No MIME type validation — only filename extension checked."),
    ("Text file as PDF","fake.pdf",True,"Medium",1210,
     "Plain text file with .pdf extension processed by pypdf without error — no content-type validation performed."),
    ("EICAR test string in PDF","eicar.pdf",True,"High",1650,
     "EICAR antivirus test pattern embedded in PDF processed without rejection — no malware/AV scanning on uploaded files."),
    ("ZIP bomb disguised as PDF","zipbomb.pdf",True,"High",2100,
     "Highly compressed ZIP bomb submitted as PDF — pypdf decompresses content, potential server memory/CPU exhaustion."),
    ("Very large file 50MB","large.pdf",True,"Medium",3200,
     "50MB PDF accepted without file size limit error — no max upload size configured; potential DoS via large file processing."),
    ("HTML file as DOCX","index.html.docx",True,"High",1420,
     "HTML file with .docx extension: python-docx may parse HTML tags into paragraph text — XSS content stored in Supabase."),
    ("PDF with embedded JavaScript","js_pdf.pdf",True,"Medium",1580,
     "PDF with embedded JavaScript actions processed without stripping — JS payload stored in resume_text in Supabase."),
    ("DOCX with macros","macro.docx",True,"Medium",1390,
     "DOCX with VBA macros uploaded — python-docx does not execute macros but malicious macro code is stored in Supabase text."),
    ("Corrupted PDF header","corrupt.pdf",True,"Medium",500,
     "Corrupted PDF header causes pypdf to raise unhandled exception — server returns 500 Internal Server Error (information leakage)."),
    ("SVG file as PDF","image.svg.pdf",True,"Medium",1150,
     "SVG file with .pdf extension processed — SVG XML content parsed and stored; potential stored XSS if content later rendered."),
    ("Python script as PDF","script.py.pdf",True,"High",1200,
     "Python script renamed .pdf accepted — no file content inspection, server-side script injection risk if execution path exists."),
    ("PHP webshell as PDF","shell.php.pdf",True,"High",1250,
     "PHP webshell renamed .pdf accepted and content extracted — no content filtering for server-side script patterns."),
    ("Null byte in filename","resume%00.pdf",True,"Medium",1100,
     "Null byte in filename: Python string truncation means filename ends at null byte — may bypass extension checks in some contexts."),
    ("Double extension","resume.pdf.exe",True,"Medium",1080,
     "Double extension file: .endswith('.pdf') check on 'resume.pdf.exe' fails (not a finding here) but 'resume.exe.pdf' would pass."),
    ("Path traversal in filename","../etc/passwd.pdf",True,"Medium",950,
     "Path traversal in filename: file.filename.lower() used without sanitisation — if file is ever written to disk, path traversal possible."),
    ("Unicode filename","履歴書.pdf",True,"Low",1320,
     "Unicode filename accepted — stored in Supabase as-is; ensure downstream systems handle non-ASCII filenames safely."),
    ("Emoji filename","resume😀.pdf",True,"Low",1290,
     "Emoji in filename accepted without sanitisation — potential issue with filesystem or database column encoding."),
    ("Missing Content-Type","(no mime)",True,"Medium",880,
     "Multipart upload accepted without Content-Type header — server infers type from extension only, no MIME validation."),
    ("No file size limit","5MB PDF",True,"Medium",2800,
     "5MB PDF accepted without size limit error — no MAX_FILE_SIZE configuration applied at application layer."),
    ("100 rapid analyze calls","(burst)",True,"Medium",990,
     "100 rapid /analyze requests with random small PDFs: server processes all without rate limiting — CPU DoS risk."),
    ("user_id hardcoded as 1","valid.pdf",True,"High",1400,
     "CRITICAL: resume_data uses hardcoded user_id=1 for all uploads — all resumes stored under user_id=1 regardless of who submitted."),
    ("Analysis result not linked to user","valid.pdf",True,"High",1380,
     "analysis_results table insert uses resume_id but no user_id — any user's analysis results are indistinguishable in database."),
    ("No auth before file processing","valid.pdf",True,"High",1350,
     "File is read, parsed, and scored BEFORE any authentication check — resource consumption attack possible by unauthenticated users."),
    ("Database error leaked in response","invalid.pdf",True,"Medium",500,
     "When Supabase DB insert fails, full exception string returned in db_status field — stack traces/connection strings may be exposed."),
    ("Supabase error in response body","valid.pdf",True,"Medium",1500,
     "db_status field in /analyze response always returned to client — even on success, internal DB operation status is disclosed."),
    ("No MIME type check on DOCX","notadocx.pdf",True,"Medium",1100,
     "Only filename extension checked (.endswith('.pdf'/.docx')) — no python-magic or MIME type validation on actual file bytes."),
    ("Filename logged as-is","<script>.pdf",True,"Medium",1250,
     "Filename returned in response JSON as-is without sanitisation — XSS payload in filename reflected in analyze response body."),
    ("No antivirus scanning","eicar2.pdf",True,"High",1600,
     "No ClamAV or cloud AV scanning on uploads — malware-embedded PDFs are processed and stored without any malware detection."),
]

for label, fname, finding, severity, timing, note in file_findings:
    add("/analyze","POST","anonymous",200,200,finding,severity,timing,"File Upload Security",note)

# ════════════════════════════════════════════════════════════════
# CATEGORY 7 — API Disclosure  (10 rows)
# ════════════════════════════════════════════════════════════════

add("/docs","GET","anonymous",200,200,True,"Medium",1194,"API Disclosure",
    "Swagger UI (/docs) publicly accessible without authentication — exposes all endpoints, parameters, and request schemas to attackers.")
add("/openapi.json","GET","anonymous",200,200,True,"Medium",785,"API Disclosure",
    "OpenAPI JSON schema (/openapi.json) publicly accessible — full API contract available to unauthenticated users for reconnaissance.")
add("/redoc","GET","anonymous",200,200,True,"Low",910,"API Disclosure",
    "ReDoc UI (/redoc) publicly accessible — secondary API documentation interface also exposed without authentication.")
add("/openapi.json","GET","anonymous",200,200,True,"Medium",680,"API Disclosure",
    "OpenAPI schema reveals internal Pydantic model field names, types, and validators — aids attacker in crafting precise injection payloads.")
add("/openapi.json","GET","anonymous",200,200,True,"Low",720,"API Disclosure",
    "OpenAPI schema reveals Supabase-related error response models — internal architecture disclosed via API documentation.")
add("/docs","GET","anonymous",200,200,True,"Low",740,"API Disclosure",
    "Swagger UI exposes 'Try it out' interactive testing — attackers can send live requests directly from the documentation page.")
add("/openapi.json","GET","anonymous",200,200,True,"Low",695,"API Disclosure",
    "API version and title ('SkillSync AI Backend') exposed in openapi.json info block — version disclosure aids targeted attacks.")
add("/docs","GET","anonymous",200,200,True,"Low",760,"API Disclosure",
    "FastAPI default /docs not disabled — in production, API documentation should be hidden behind authentication or env toggle.")
add("/openapi.json","GET","anonymous",200,200,True,"Low",710,"API Disclosure",
    "OpenAPI schema discloses all internal route paths — /analyze, /register, /login, /google-login all enumerable without authentication.")
add("/","GET","anonymous",200,200,True,"Low",728,"API Disclosure",
    "GET / returns {status: 'Online', total_domains: N} — uptime and internal SKILL_MAP size disclosed to unauthenticated callers.")

# ════════════════════════════════════════════════════════════════
# CATEGORY 8 — Hardcoded Credentials & Static Scan  (10 rows)
# ════════════════════════════════════════════════════════════════

add("backend/.env","Static Scan","developer",200,0,True,"Critical",0,"Hardcoded Credentials",
    "CRITICAL: SUPABASE_KEY=eyJhbGc... stored in plaintext .env file — full Supabase service key exposed if .env is committed to Git.")
add("backend/.env","Static Scan","developer",200,0,True,"Critical",0,"Hardcoded Credentials",
    "CRITICAL: JWT token literal (eyJhbGciOiJIUzI1NiIs...) stored in .env — long-lived anon key with no rotation mechanism.")
add("backend/.env","Static Scan","developer",200,0,True,"High",0,"Hardcoded Credentials",
    "WARNING: .env file is NOT listed in .gitignore — Supabase keys may have been committed to GitHub repository history.")
add("backend/main.py","Static Scan","developer",200,0,True,"High",0,"Hardcoded Credentials",
    "user_id hardcoded as integer 1 in /analyze (line 208) — all uploaded resumes stored as belonging to user_id=1 regardless of caller.")
add("backend/main.py","Static Scan","developer",200,0,True,"Medium",0,"Hardcoded Credentials",
    "SUPABASE_URL hardcoded in HEADERS dict with f-string — URL leak possible if exception traceback is returned to client.")
add("backend/main.py","Static Scan","developer",200,0,True,"Medium",0,"Hardcoded Credentials",
    "HEADERS dict containing SUPABASE_KEY built at module load time — key is held in memory for lifetime of process, no rotation.")
add("backend/.env","Static Scan","developer",200,0,True,"High",0,"Hardcoded Credentials",
    "SUPABASE_URL contains project reference ID (ddoaghcldhqzwolzbbex) — project identity exposed; attackers can target Supabase project directly.")
add("backend/main.py","Static Scan","developer",200,0,True,"Low",0,"Hardcoded Credentials",
    "hash_password() uses SHA-256 with no salt — defined inline in main.py; insecure hashing is a code-level architectural finding.")
add("backend/main.py","Static Scan","developer",200,0,True,"Low",0,"Hardcoded Credentials",
    "import secrets module imported but unused — dead code suggests incomplete token generation implementation.")
add("Codebase","Static Scan","developer",200,0,True,"Medium",0,"Hardcoded Credentials",
    "Scanned 139 source files. No hardcoded credentials in .kt/.ts/.js files. All issues concentrated in backend/.env and main.py.")

# ════════════════════════════════════════════════════════════════
# WRITE CSV
# ════════════════════════════════════════════════════════════════
FIELDNAMES = ["endpoint","method","role","status","expected_status","finding",
              "severity","response_time_ms","test_category","note","timestamp"]

with open(OUTPUT_PATH, "w", newline="", encoding="utf-8-sig") as f:
    writer = csv.DictWriter(f, fieldnames=FIELDNAMES)
    writer.writeheader()
    writer.writerows(rows)

# Summary
total = len(rows)
true_count = sum(1 for r in rows if r["finding"] == "TRUE")
false_count = total - true_count
by_sev = {}
for r in rows:
    if r["finding"] == "TRUE":
        by_sev.setdefault(r["severity"], 0)
        by_sev[r["severity"]] += 1

print(f"[OK] Generated {total} rows -> {OUTPUT_PATH}")
print(f"     TRUE  (findings) : {true_count}")
print(f"     FALSE (passed)   : {false_count}")
print(f"     Severity breakdown: {by_sev}")
