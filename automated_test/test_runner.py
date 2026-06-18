import os
import sys
import json
import time
import requests
from datetime import datetime

# Configure standard output to use utf-8
sys.stdout.reconfigure(encoding='utf-8')

# Load input config
try:
    with open(os.path.join(os.path.dirname(__file__), "input.json"), "r") as f:
        config = json.load(f)
except Exception as e:
    print(f"Error loading input.json: {e}")
    sys.exit(1)

BASE_URL = config.get("baseUrl", "").rstrip("/")
DUMMY_TOKEN = config.get("user", "")

# Load discovered endpoints
try:
    with open(os.path.join(os.path.dirname(__file__), "endpoints.json"), "r") as f:
        endpoints = json.load(f)
except Exception as e:
    print(f"Error loading endpoints.json. Please run discover.py first: {e}")
    sys.exit(1)

reports = []
findings_count = 0

def add_report(endpoint, method, role, status, expected_status, finding, severity, response_time_ms, category, note):
    global findings_count
    if finding:
        findings_count += 1
    reports.append({
        "endpoint": endpoint,
        "method": method,
        "role": role,
        "status": status,
        "expected_status": expected_status,
        "finding": finding,
        "severity": severity,
        "response_time_ms": response_time_ms,
        "test_category": category,
        "note": note,
        "timestamp": datetime.utcnow().isoformat() + "Z"
    })

print("=" * 60)
print("             STARTING DAST SECURITY SCAN")
print("=" * 60)
print(f"Target Base URL: {BASE_URL}")
print(f"Discovered Endpoints to Test: {len(endpoints)}")
print("-" * 60)

# --- Category 0 & 1: AuthN Check (No Auth / Auth Bypass) ---
# Check if endpoints require authentication. Since the FastAPI app does not enforce JWT check on sensitive endpoints like /analyze, we verify this behavior.
for ep in endpoints:
    path = ep["path"]
    method = ep["method"]
    url = f"{BASE_URL}{path}"
    
    # We only test GET / HEAD and safe POSTs. For /analyze, we can send an empty file or see how it responds.
    if path == "/analyze":
        # Test if it rejects with 422 Unprocessable Entity (because file is missing) or works without any authentication header.
        # Since it is public, it should return 422 (validation error for file) rather than 401 Unauthorized.
        start_time = time.time()
        try:
            res = requests.post(url, timeout=10)
            status = res.status_code
        except Exception as e:
            status = 0
        elapsed = int((time.time() - start_time) * 1000)
        
        # /analyze should theoretically require authentication because it uploads user data to Supabase.
        # But it returns 422/500/etc. instead of 401. This is an AuthN Enforcement check.
        if status != 401:
            add_report(
                endpoint=path,
                method=method,
                role="anonymous",
                status=status,
                expected_status=401,
                finding=True,
                severity="Medium",
                response_time_ms=elapsed,
                category="AuthN Enforcement",
                note="The endpoint accepts requests without authentication headers. Sensitive operations like resume analysis should require valid JWT authentication."
            )
        else:
            add_report(
                endpoint=path,
                method=method,
                role="anonymous",
                status=status,
                expected_status=401,
                finding=False,
                severity="Info",
                response_time_ms=elapsed,
                category="AuthN Enforcement",
                note="Endpoint correctly rejected unauthorized access."
            )
            
    elif path == "/" and method == "GET":
        start_time = time.time()
        try:
            res = requests.get(url, timeout=10)
            status = res.status_code
        except Exception as e:
            status = 0
        elapsed = int((time.time() - start_time) * 1000)
        
        # Homepage is expected to be public
        add_report(
            endpoint=path,
            method=method,
            role="anonymous",
            status=status,
            expected_status=200,
            finding=False,
            severity="Info",
            response_time_ms=elapsed,
            category="AuthN Enforcement",
            note="Public home route responded successfully."
        )

# --- Category 6: SQL Injection Probing ---
# Test /login endpoint for SQLi detection payloads in input params
sqli_payloads = [
    "' OR '1'='1",
    "admin' --",
    "' UNION SELECT NULL--"
]
for payload in sqli_payloads:
    url = f"{BASE_URL}/login"
    start_time = time.time()
    try:
        # Send payload in body
        res = requests.post(url, json={"email": payload, "password": "password"}, timeout=10)
        status = res.status_code
        body = res.text
    except Exception as e:
        status = 0
        body = str(e)
    elapsed = int((time.time() - start_time) * 1000)
    
    # Check if server throws 500 Internal Server Error (usually indicative of SQL error in raw query)
    if status == 500 or "SQL" in body or "mysql" in body.lower() or "database" in body.lower():
        add_report(
            endpoint="/login",
            method="POST",
            role="anonymous",
            status=status,
            expected_status=400,
            finding=True,
            severity="High",
            response_time_ms=elapsed,
            category="SQL Injection",
            note=f"Input payload '{payload}' caused potential SQL anomaly or database error response: {status}"
        )
    else:
        add_report(
            endpoint="/login",
            method="POST",
            role="anonymous",
            status=status,
            expected_status=400,
            finding=False,
            severity="Info",
            response_time_ms=elapsed,
            category="SQL Injection",
            note=f"Payload '{payload}' was correctly handled or rejected."
        )

# --- Category 7: Rate Limiting Verification ---
# Burst 15 requests to see if server has rate limits
url = f"{BASE_URL}/"
rate_limited = False
start_time = time.time()
statuses = []
for i in range(15):
    try:
        res = requests.get(url, timeout=10)
        statuses.append(res.status_code)
        if res.status_code == 429:
            rate_limited = True
    except Exception:
        statuses.append(0)
    time.sleep(0.05)
elapsed = int((time.time() - start_time) * 1000)

if not rate_limited:
    add_report(
        endpoint="/",
        method="GET",
        role="anonymous",
        status=statuses[-1] if statuses else 0,
        expected_status=429,
        finding=True,
        severity="Low",
        response_time_ms=elapsed,
        category="Rate Limiting",
        note="Server did not enforce rate limits during rapid burst of requests. Rate limiting is recommended to prevent DoS attacks."
    )
else:
    add_report(
        endpoint="/",
        method="GET",
        role="anonymous",
        status=429,
        expected_status=429,
        finding=False,
        severity="Info",
        response_time_ms=elapsed,
        category="Rate Limiting",
        note="Server correctly returned 429 Too Many Requests."
    )

# --- Category 8: Scan Codebase for Committed Secrets ---
# Scan python files and config files in local dir
secrets_found = []
import re
keyword_patterns = [
    r"(?i)api_key\s*=\s*['\"][a-zA-Z0-9_\-]{16,}['\"]",
    r"(?i)secret\s*=\s*['\"][a-zA-Z0-9_\-]{16,}['\"]",
    r"(?i)password\s*=\s*['\"][a-zA-Z0-9_\-]{8,}['\"]"
]

def scan_files(directory):
    for root, dirs, files in os.walk(directory):
        if any(skip in root for skip in [".git", ".venv", "node_modules", "__pycache__"]):
            continue
        for file in files:
            if file.endswith((".py", ".ts", ".tsx", ".js", ".json")):
                filepath = os.path.join(root, file)
                try:
                    with open(filepath, "r", encoding="utf-8") as f:
                        content = f.read()
                    for pattern in keyword_patterns:
                        matches = re.findall(pattern, content)
                        if matches:
                            # Avoid matching false positives
                            for match in matches:
                                if "os.environ" not in match and "process.env" not in match:
                                    secrets_found.append((file, match[:50]))
                except Exception:
                    pass

scan_files(os.path.join(os.path.dirname(__file__), ".."))

if secrets_found:
    add_report(
        endpoint="Codebase",
        method="Static Scan",
        role="developer",
        status=200,
        expected_status=200,
        finding=True,
        severity="High",
        response_time_ms=0,
        category="Hardcoded Credentials",
        note=f"Found potential hardcoded secret patterns: {', '.join([f'{f} ({m})' for f, m in secrets_found[:3]])}"
    )
else:
    add_report(
        endpoint="Codebase",
        method="Static Scan",
        role="developer",
        status=200,
        expected_status=200,
        finding=False,
        severity="Info",
        response_time_ms=0,
        category="Hardcoded Credentials",
        note="No committed credentials or secret patterns detected in codebase."
    )

# Write report to report.json
report_path = os.path.join(os.path.dirname(__file__), "report.json")
with open(report_path, "w") as f:
    json.dump(reports, f, indent=2)

# Print summary
print("\n" + "=" * 60)
print("                       SCAN SUMMARY")
print("=" * 60)
print(f"Total Tests Executed: {len(reports)}")
print(f"Findings Detected   : {findings_count}")
print("-" * 60)

findings_grouped = {}
for r in reports:
    if r["finding"]:
        severity = r["severity"]
        findings_grouped[severity] = findings_grouped.get(severity, 0) + 1

for sev, count in findings_grouped.items():
    print(f"  ✗ [{sev}] Findings: {count}")

print("-" * 60)
print("Details of Findings:")
for r in reports:
    if r["finding"]:
        print(f"\n  ⚠ [{r['severity']}] {r['test_category']} on endpoint: {r['method']} {r['endpoint']}")
        print(f"    Note: {r['note']}")

print("\n✓ DAST scanning execution complete! Report written to report.json")
print("=" * 60)
