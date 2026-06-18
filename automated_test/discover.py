import sys
import os
import json

# Add backend directory to python path
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'backend'))

try:
    from main import app
    print("FastAPI app imported successfully.")
except ImportError as e:
    print(f"Error importing app: {e}")
    sys.exit(1)

# Discover routes
discovered_endpoints = []
for route in app.routes:
    # Check if route has methods
    if hasattr(route, "methods"):
        path = route.path
        # Skip health/actuator/metrics endpoints
        if any(skip in path for skip in ["/health", "/actuator", "/metrics"]):
            continue
        for method in route.methods:
            # In our main.py, all endpoints are public or self-contained.
            access_rule = "public"
            discovered_endpoints.append({
                "path": path,
                "method": method,
                "access_rule": access_rule
            })

# Save to endpoints.json
output_path = os.path.join(os.path.dirname(__file__), "endpoints.json")
with open(output_path, "w") as f:
    json.dump(discovered_endpoints, f, indent=2)

print(f"\nDiscovered {len(discovered_endpoints)} endpoints:")
for ep in discovered_endpoints:
    print(f"  - {ep['method']} {ep['path']} (Access: {ep['access_rule']})")
