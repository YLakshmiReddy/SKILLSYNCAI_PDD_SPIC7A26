"use client";

import React, { useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { apiClient } from "@/lib/api";
import { supabase } from "@/lib/supabase";

export default function SignInScreen() {
  const router = useRouter();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [rememberMe, setRememberMe] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const isSignInEnabled = email.length > 0 && password.length > 0;

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

  const handleSignIn = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    
    try {
      const target = e.currentTarget as HTMLFormElement;
      
      // Use querySelector - 100% safe across all mobile and desktop browsers
      const emailInput = target.querySelector('input[name="email"]') as HTMLInputElement;
      const passwordInput = target.querySelector('input[name="password"]') as HTMLInputElement;
      
      const emailValue = email || emailInput?.value || "";
      const passwordValue = password || passwordInput?.value || "";

      if (!emailValue || !passwordValue) {
        setError("Please enter both email and password.");
        return;
      }

      if (isLoading) return;
      setIsLoading(true);

      const data = await apiClient.post<any>("/login", { email: emailValue, password: passwordValue });
      
      // Save session details
      localStorage.setItem("userToken", data.token);
      localStorage.setItem("userData", JSON.stringify(data.user));

      router.push("/dashboard");
    } catch (err: any) {
      const errMsg = err.message || "Invalid email or password. Please try again.";
      setError(errMsg);
      alert("Sign-In Error: " + errMsg);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <main className="flex min-h-screen w-full flex-col items-center bg-white font-sans overflow-x-hidden">
      {/* Container to limit width and center content */}
      <div className="w-full max-w-lg px-6 py-12 md:py-16">
        
        {/* Back Arrow */}
        <button
          onClick={() => router.back()}
          className="p-2 -ml-2 hover:bg-slate-100 rounded-full transition-colors mb-6"
        >
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#0F172A" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
        </button>

        {/* Logo and Branding */}
        <div className="flex items-center justify-center mb-8">
          <div className="w-9 h-9 bg-[#1D4ED8] rounded-lg flex items-center justify-center shadow-sm">
             <div className="w-5 h-5 bg-white rounded-[2px] rotate-45 flex items-center justify-center">
                <div className="w-2.5 h-2.5 bg-[#1D4ED8] rounded-[1px] -rotate-45" />
             </div>
          </div>
          <span className="ml-3 text-xl font-bold text-[#0F172A]">SkillSync Ai</span>
        </div>

        {/* Welcome Section */}
        <div className="text-center mb-12">
          <h1 className="text-3xl font-bold text-[#0F172A] mb-2">Welcome Back</h1>
          <p className="text-[#64748B]">Please enter your details to sign in.</p>
        </div>

        <form onSubmit={handleSignIn} className="space-y-6">
          {/* Email Field */}
          <div className="flex flex-col">
            <label className="text-sm font-bold text-[#0F172A] mb-2">Email</label>
            <input
              id="email"
              type="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Enter your email"
              className="w-full h-12 px-4 rounded-xl border border-[#E2E8F0] focus:border-[#3B82F6] outline-none text-black text-base transition-colors"
              required
            />
          </div>

          {/* Password Field */}
          <div className="flex flex-col">
            <div className="flex justify-between items-center mb-2">
              <label className="text-sm font-bold text-[#0F172A]">Password</label>
              <Link href="/auth/forgot-password" className="text-sm font-bold text-[#3B82F6] hover:underline">
                Forgot password?
              </Link>
            </div>
            <div className="relative">
              <input
                id="password"
                type={passwordVisible ? "text" : "password"}
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Enter your password"
                className="w-full h-12 px-4 pr-12 rounded-xl border border-[#E2E8F0] focus:border-[#3B82F6] outline-none text-black text-base transition-colors"
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

          {/* Remember Me */}
          <div
            className="flex items-center cursor-pointer select-none"
            onClick={() => setRememberMe(!rememberMe)}
          >
            <div className={`w-5 h-5 rounded border flex items-center justify-center transition-colors ${rememberMe ? 'bg-[#3B82F6] border-[#3B82F6]' : 'bg-white border-[#E2E8F0]'}`}>
              {rememberMe && (
                <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="white" strokeWidth="4" strokeLinecap="round" strokeLinejoin="round">
                  <polyline points="20 6 9 17 4 12"></polyline>
                </svg>
              )}
            </div>
            <span className="ml-2 text-sm text-[#64748B]">Remember me for 30 days</span>
          </div>

          {/* Sign In Button */}
          <button
            id="login-button"
            type="submit"
            disabled={isLoading}
            className={`w-full h-14 rounded-xl font-bold text-white transition-all shadow-lg flex items-center justify-center ${!isLoading ? 'bg-[#1E88E5] hover:bg-[#1976D2] shadow-blue-100 cursor-pointer' : 'bg-[#B0BEC5] cursor-not-allowed'}`}
          >
            {isLoading ? (
              <svg className="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
            ) : (
              "Sign In"
            )}
          </button>

          {error && (
            <div className="text-center text-sm font-semibold text-red-500 mt-4 transition-all">
              {error}
            </div>
          )}
        </form>

        {/* Divider */}
        <div className="flex items-center my-10">
          <div className="flex-1 h-px bg-[#E2E8F0]"></div>
          <span className="px-4 text-[10px] font-bold text-[#94A3B8] tracking-widest uppercase">OR CONTINUE WITH</span>
          <div className="flex-1 h-px bg-[#E2E8F0]"></div>
        </div>

        {/* Social Sign In */}
        <div className="flex">
          <button
            type="button"
            onClick={() => handleOAuth("google")}
            className="flex items-center justify-center w-full h-14 border border-[#E2E8F0] rounded-xl hover:bg-slate-50 active:scale-95 transition-all cursor-pointer">
            <svg width="20" height="20" viewBox="0 0 24 24">
              <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/>
              <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/>
              <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l3.66-2.84z" fill="#FBBC05"/>
              <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/>
            </svg>
            <span className="ml-3 text-sm font-bold text-[#0F172A]">Continue with Google</span>
          </button>
        </div>

        {/* Footer Link */}
        <div className="mt-12 text-center text-sm">
          <span className="text-[#64748B]">Don't have an account? </span>
          <Link href="/auth/signup" className="font-bold text-[#3B82F6] hover:underline">
            Sign up for free
          </Link>
        </div>
      </div>
    </main>
  );
}
