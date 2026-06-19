import asyncio
import time
import json
import csv
import os
import sys
# pyrefly: ignore [missing-import]
import aiohttp

# Configure output to use utf-8
sys.stdout.reconfigure(encoding='utf-8')

# Load target URL from input.json
try:
    with open(os.path.join(os.path.dirname(__file__), "input.json"), "r") as f:
        config = json.load(f)
except Exception as e:
    print(f"Error loading input.json: {e}")
    sys.exit(1)

BASE_URL = config.get("baseUrl", "").rstrip("/")
if not BASE_URL:
    print("Base URL not found in input.json")
    sys.exit(1)

TARGET_URL = f"{BASE_URL}/"
CONCURRENCY = 100
DURATION_SECONDS = 60

results = []

async def worker(session, worker_id, start_time, duration):
    """Simulates a single virtual user making consecutive requests."""
    request_count = 0
    while time.time() - start_time < duration:
        req_start = time.time()
        try:
            async with session.get(TARGET_URL, timeout=10) as response:
                status = response.status
                await response.read()
        except Exception:
            status = 0
        req_end = time.time()
        elapsed_ms = int((req_end - req_start) * 1000)
        
        results.append({
            "worker_id": worker_id,
            "timestamp": time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(req_start)),
            "status": status,
            "response_time_ms": elapsed_ms
        })
        request_count += 1
        # Sleep for a tiny amount of time to be friendly to connection pools
        await asyncio.sleep(0.01)

async def main():
    print("=" * 60)
    print("            STARTING BASELINE / LOAD TESTING")
    print("=" * 60)
    print(f"Target URL:         {TARGET_URL}")
    print(f"Concurrent Users:   {CONCURRENCY}")
    print(f"Duration:           {DURATION_SECONDS} seconds")
    print("-" * 60)
    print("Simulating load... Please wait...")

    start_time = time.time()
    
    # Set connector pool limit
    conn = aiohttp.TCPConnector(limit=CONCURRENCY)
    async with aiohttp.ClientSession(connector=conn) as session:
        tasks = []
        for i in range(CONCURRENCY):
            tasks.append(worker(session, i, start_time, DURATION_SECONDS))
        await asyncio.gather(*tasks)

    end_time = time.time()
    total_duration = end_time - start_time
    total_requests = len(results)
    
    if total_requests == 0:
        print("No requests were completed.")
        sys.exit(1)

    # Calculate metrics
    response_times = [r["response_time_ms"] for r in results]
    min_time = min(response_times)
    max_time = max(response_times)
    avg_time = sum(response_times) / total_requests
    rps = total_requests / total_duration

    success_requests = sum(1 for r in results if 200 <= r["status"] < 300)
    failed_requests = total_requests - success_requests

    print("\n" + "=" * 60)
    print("                       TEST RESULTS")
    print("=" * 60)
    print(f"Total Requests Sent: {total_requests}")
    print(f"Successful Requests: {success_requests} ({(success_requests/total_requests)*100:.1f}%)")
    print(f"Failed Requests:     {failed_requests} ({(failed_requests/total_requests)*100:.1f}%)")
    print(f"Actual Duration:     {total_duration:.2f} seconds")
    print(f"Requests Per Second: {rps:.2f} RPS")
    print("-" * 60)
    print("Response Times:")
    print(f"  Average:           {avg_time:.2f} ms")
    print(f"  Min:               {min_time} ms")
    print(f"  Max:               {max_time} ms")
    print("=" * 60)

    # Write summary sheet
    summary_path = os.path.join(os.path.dirname(__file__), "load_test_summary.csv")
    with open(summary_path, "w", newline="", encoding="utf-8-sig") as f:
        writer = csv.writer(f)
        writer.writerow(["Metric", "Value"])
        writer.writerow(["Target URL", TARGET_URL])
        writer.writerow(["Concurrent Virtual Users", CONCURRENCY])
        writer.writerow(["Configured Duration (s)", DURATION_SECONDS])
        writer.writerow(["Actual Duration (s)", round(total_duration, 2)])
        writer.writerow(["Total Requests Sent", total_requests])
        writer.writerow(["Successful Requests", success_requests])
        writer.writerow(["Failed Requests", failed_requests])
        writer.writerow(["Requests Per Second (RPS)", round(rps, 2)])
        writer.writerow(["Average Response Time (ms)", round(avg_time, 2)])
        writer.writerow(["Minimum Response Time (ms)", min_time])
        writer.writerow(["Maximum Response Time (ms)", max_time])

    # Write detailed request sheet
    details_path = os.path.join(os.path.dirname(__file__), "load_test_details.csv")
    with open(details_path, "w", newline="", encoding="utf-8-sig") as f:
        writer = csv.writer(f)
        writer.writerow(["Request ID", "Virtual User ID", "Timestamp", "HTTP Status", "Response Time (ms)"])
        for idx, r in enumerate(results, 1):
            writer.writerow([idx, r["worker_id"], r["timestamp"], r["status"], r["response_time_ms"]])

    print(f"\n✓ Load test complete! Reports written to:")
    print(f"  - {summary_path}")
    print(f"  - {details_path}")

if __name__ == "__main__":
    asyncio.run(main())
