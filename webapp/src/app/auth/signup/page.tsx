"use client";

import React, { useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { apiClient } from "@/lib/api";
import { supabase } from "@/lib/supabase";

export default function SignUpScreen() {
  const router = useRouter();
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleSignUp = async (e: React.FormEvent) => {
    e.preventDefault();
    if (isLoading) return;

    // Read directly from DOM elements as a fallback to handle browser autofill
    const target = e.currentTarget as HTMLFormElement;
    const fullNameInput = target.querySelector('input[name="fullName"]') as HTMLInputElement;
    const emailInput = target.querySelector('input[name="email"]') as HTMLInputElement;
    const passwordInput = target.querySelector('input[name="password"]') as HTMLInputElement;
    const confirmPasswordInput = target.querySelector('input[name="confirmPassword"]') as HTMLInputElement;

    const fullNameValue = fullName || fullNameInput?.value || "";
    const emailValue = email || emailInput?.value || "";
    const passwordValue = password || passwordInput?.value || "";
    const confirmPasswordValue = confirmPassword || confirmPasswordInput?.value || "";

    if (!fullNameValue || !emailValue || !passwordValue || !confirmPasswordValue) {
      setError("Please fill in all fields.");
      return;
    }

    if (passwordValue !== confirmPasswordValue) {
      setError("Passwords do not match.");
      return;
    }
    if (passwordValue.length < 8) {
      setError("Password must be at least 8 characters.");
      return;
    }

    setIsLoading(true);
    setError(null);

    try {
      // 1. Register Account
      await apiClient.post<any>("/register", {
        full_name: fullNameValue,
        email: emailValue,
        password: passwordValue,
      });

      // 2. Perform Automatic Login
      const loginData = await apiClient.post<any>("/login", {
        email: emailValue,
        password: passwordValue,
      });

      // 3. Save session
      localStorage.setItem("userToken", loginData.token);
      localStorage.setItem("userData", JSON.stringify(loginData.user));

      router.push("/dashboard");
    } catch (err: any) {
      const errMsg = err.message || "Failed to create account. Please try again.";
      setError(errMsg);
      alert("Sign-Up Error: " + errMsg);
    } finally {
      setIsLoading(false);
    }
  };

  const handleOAuth = async (provider: "google") => {
    setError(null);
    try {
      const isProduction = process.env.NODE_ENV === "production";
      const redirectTo = isProduction
        ? `${window.location.origin}/SKILLSYNCAI_PDD_SPIC7A26/auth/callback`
        : `${window.location.origin}/auth/callback`;
      const { error } = await supabase.auth.signInWithOAuth({
        provider,
        options: {
          redirectTo,
        },
      });
      if (error) {
        setError(error.message);
        alert("Google Auth Error: " + error.message);
      }
    } catch (err: any) {
      const errMsg = err.message || "OAuth redirect failed. Please try again.";
      setError(errMsg);
      alert("Google Auth Exception: " + errMsg);
    }
  };

  return (
    <main className="flex min-h-screen w-full flex-col items-center bg-[#F6F7F8] font-sans overflow-x-hidden py-8 px-4 md:py-12">

      {/* Main Card Container */}
      <div className="w-full max-w-[480px] bg-white rounded-2xl shadow-[0_25px_50px_-12px_rgba(0,0,0,0.25)] overflow-hidden">
        
        {/* Header with Back Button */}
        <div className="w-full h-[72px] flex items-center px-4 relative">
          <button
            onClick={() => router.back()}
            className="w-12 h-12 flex items-center justify-center hover:bg-slate-50 rounded-full transition-colors relative z-10"
          >
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="#0F172A" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
              <line x1="19" y1="12" x2="5" y2="12"></line>
              <polyline points="12 19 5 12 12 5"></polyline>
            </svg>
          </button>
          <h1 className="flex-1 text-center text-lg font-bold text-[#0F172A]">Create Account</h1>
          <div className="w-12 h-12"></div> {/* Spacer to center the title */}
        </div>

        {/* Gradient Hero Section */}
        <div className="w-full h-[200px] bg-gradient-to-r from-[#FF9A9E] via-[#FECFEF] to-[#FEADA6] relative flex items-center justify-center">
           {/* Floating Icon Overlay */}
           <div className="w-[54px] h-[51px] bg-white/90 rounded-xl shadow-lg flex items-center justify-center">
              <svg width="30" height="27" viewBox="0 0 24 24" fill="none" stroke="#137FEC" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                <path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                <circle cx="8.5" cy="7" r="4"></circle>
                <polyline points="17 11 19 13 23 9"></polyline>
              </svg>
           </div>
        </div>

        {/* Form Content */}
        <div className="px-6 py-8 md:px-8">
          <div className="text-center mb-8">
            <h2 className="text-2xl md:text-3xl font-bold text-[#0F172A] mb-2">Join SkillSync Ai</h2>
            <p className="text-[#475569]">Sync your skills with the future</p>
          </div>

          <form onSubmit={handleSignUp} className="space-y-5">
            <div className="flex flex-col">
              <label className="text-sm font-semibold text-[#0F172A] px-1 mb-1.5">Full Name</label>
              <input
                type="text" 
                name="fullName"
                value={fullName}
                onChange={(e) => setFullName(e.target.value)}
                placeholder="Enter your full name"
                className="w-full h-12 px-4 rounded-lg bg-[#F8FAFC] border border-[#E2E8F0] focus:border-[#137FEC] focus:bg-white outline-none text-black text-base transition-all"
                required
              />
            </div>

            <div className="flex flex-col">
              <label className="text-sm font-semibold text-[#0F172A] px-1 mb-1.5">Email Address</label>
              <input
                type="email" 
                name="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="example@email.com"
                className="w-full h-12 px-4 rounded-lg bg-[#F8FAFC] border border-[#E2E8F0] focus:border-[#137FEC] focus:bg-white outline-none text-black text-base transition-all"
                required
              />
            </div>

            <div className="flex flex-col">
              <label className="text-sm font-semibold text-[#0F172A] px-1 mb-1.5">Password</label>
              <div className="relative">
                <input
                  type={passwordVisible ? "text" : "password"}
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  placeholder="Min. 8 characters"
                  className="w-full h-12 px-4 pr-12 rounded-lg bg-[#F8FAFC] border border-[#E2E8F0] focus:border-[#137FEC] focus:bg-white outline-none text-black text-base transition-all"
                  required
                />
                <button
                  type="button"
                  onClick={() => setPasswordVisible(!passwordVisible)}
                  className="absolute right-4 top-1/2 -translate-y-1/2 text-[#94A3B8]"
                >
                  {passwordVisible ? (
                    <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>
                  ) : (
                    <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"></path><line x1="1" y1="1" x2="23" y2="23"></line></svg>
                  )}
                </button>
              </div>
            </div>

            <div className="flex flex-col">
              <label className="text-sm font-semibold text-[#0F172A] px-1 mb-1.5">Confirm Password</label>
              <input
                type="password" 
                name="confirmPassword"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                placeholder="Re-type password"
                className="w-full h-12 px-4 rounded-lg bg-[#F8FAFC] border border-[#E2E8F0] focus:border-[#137FEC] focus:bg-white outline-none text-black text-base transition-all"
                required
              />
            </div>

            <button
              type="submit"
              disabled={isLoading}
              className={`w-full h-[52px] rounded-lg font-bold text-base transition-all shadow-[0_10px_20px_-5px_rgba(19,127,236,0.2)] flex items-center justify-center text-white ${isLoading ? 'bg-[#B0BEC5] cursor-not-allowed' : 'bg-[#1E88E5] hover:bg-[#1976D2] cursor-pointer'}`}
            >
              {isLoading ? (
                <svg className="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                  <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                  <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                </svg>
              ) : (
                "Sign Up"
              )}
            </button>

            {error && (
              <div className="text-center text-sm font-semibold text-red-500 mt-4 transition-all">
                {error}
              </div>
            )}
          </form>

          {/* Divider */}
          <div className="flex items-center my-8">
            <div className="flex-1 h-px bg-[#E2E8F0]"></div>
            <span className="px-4 text-sm font-medium text-[#94A3B8]">Or continue with</span>
            <div className="flex-1 h-px bg-[#E2E8F0]"></div>
          </div>

          {/* Social Sign Up */}
          <div className="flex">
            <button
              type="button"
              onClick={() => handleOAuth("google")}
              className="flex items-center justify-center w-full h-[46px] border border-[#E2E8F0] rounded-lg hover:bg-slate-50 active:scale-95 transition-all text-[#334155] cursor-pointer">
              <svg width="20" height="20" viewBox="0 0 24 24">
                <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/>
                <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/>
                <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l3.66-2.84z" fill="#FBBC05"/>
                <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/>
              </svg>
              <span className="ml-2 text-sm font-medium">Continue with Google</span>
            </button>
          </div>

          {/* Footer Link */}
          <div className="mt-10 text-center text-sm">
            <span className="text-[#64748B]">Already have an account? </span>
            <Link href="/auth/signin" className="font-bold text-[#137FEC] hover:underline">
              Log In
            </Link>
          </div>
        </div>
      </div>
    </main>
  );
}
