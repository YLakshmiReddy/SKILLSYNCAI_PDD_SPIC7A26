"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { supabase } from "@/lib/supabase";

export default function AuthCallback() {
  const router = useRouter();

  useEffect(() => {
    // Supabase puts the session tokens in the URL hash after OAuth redirect.
    // getSession() picks them up automatically.
    supabase.auth.getSession().then(({ data: { session } }) => {
      if (session) {
        // Save token + user info to localStorage (same format as email/password login)
        localStorage.setItem("userToken", session.access_token);
        localStorage.setItem(
          "userData",
          JSON.stringify({
            id: session.user.id,
            email: session.user.email,
            full_name:
              session.user.user_metadata?.full_name ||
              session.user.user_metadata?.name ||
              session.user.email,
          })
        );
        router.replace("/dashboard");
      } else {
        // No session found — send back to sign in
        alert("Session callback error: No active session found.");
        router.replace("/auth/signin");
      }
    }).catch((err: any) => {
      alert("Session Callback Exception: " + (err.message || err));
      router.replace("/auth/signin");
    });
  }, [router]);

  return (
    <div className="flex h-screen items-center justify-center bg-[#F8FAFC]">
      <div className="flex flex-col items-center gap-4">
        <svg
          className="animate-spin h-10 w-10 text-[#1D4ED8]"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
        >
          <circle
            className="opacity-25"
            cx="12"
            cy="12"
            r="10"
            stroke="currentColor"
            strokeWidth="4"
          ></circle>
          <path
            className="opacity-75"
            fill="currentColor"
            d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
          ></path>
        </svg>
        <p className="text-[#64748B] font-medium text-sm">Signing you in…</p>
      </div>
    </div>
  );
}
