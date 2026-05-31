"use client";

import { useEffect } from "react";

export default function GlobalErrorListener() {
  useEffect(() => {
    const handleError = (event: ErrorEvent) => {
      alert(`Runtime Error: ${event.message}\nAt: ${event.filename}:${event.lineno}\nStack: ${event.error?.stack || "No stack"}`);
    };

    const handleRejection = (event: PromiseRejectionEvent) => {
      const reason = event.reason;
      alert(`Promise Rejection: ${reason?.message || reason}\nStack: ${reason?.stack || "No stack"}`);
    };

    window.addEventListener("error", handleError);
    window.addEventListener("unhandledrejection", handleRejection);

    console.log("Global error listeners registered.");

    return () => {
      window.removeEventListener("error", handleError);
      window.removeEventListener("unhandledrejection", handleRejection);
    };
  }, []);

  return null;
}
