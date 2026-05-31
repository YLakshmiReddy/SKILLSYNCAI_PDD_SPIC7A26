"use client";

import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

export default function ForgotPasswordScreen() {
  const router = useRouter();
  const [email, setEmail] = useState("");
  const [showSuccessPopup, setShowSuccessPopup] = useState(false);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (email) {
      setShowSuccessPopup(true);
    }
  };

  useEffect(() => {
    if (showSuccessPopup) {
      const timer = setTimeout(() => {
        setShowSuccessPopup(false);
        router.push("/auth/signin");
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [showSuccessPopup, router]);

  return (
    <main className="flex min-h-screen w-full flex-col items-center bg-[#FEF2E8] font-sans p-6 md:p-12 overflow-x-hidden">

      {/* White Card Container */}
      <div className="w-full max-w-[540px] bg-white rounded-[32px] shadow-lg flex flex-col p-8 md:p-10 my-auto">

        {/* Top Navigation */}
        <button
          onClick={() => router.back()}
          className="flex items-center text-[#0F172A] hover:bg-slate-50 w-fit px-2 py-1 rounded-lg transition-colors mb-6"
        >
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
          <span className="ml-2 font-medium">Back</span>
        </button>

        {/* Branding */}
        <div className="flex items-center justify-center mb-10">
           <div className="w-10 h-10 bg-[#1D4ED8] rounded-xl flex items-center justify-center">
              <div className="w-5 h-5 bg-white rounded-[2px] rotate-45 flex items-center justify-center">
                 <div className="w-2.5 h-2.5 bg-[#1D4ED8] rounded-[1px] -rotate-45" />
              </div>
           </div>
           <h2 className="ml-3 text-2xl font-bold text-[#0F172A]">SkillSync AI</h2>
        </div>

        {/* Illustration Placeholder (Orange Theme) */}
        <div className="w-full h-44 bg-[#FFEDD5] rounded-2xl mb-10 relative overflow-hidden flex items-center justify-center">
           <div className="absolute inset-0 bg-gradient-to-br from-[#FB923C]/20 to-transparent" />
           <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="#F97316" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
              <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4"></path>
              <polyline points="10 17 15 12 10 7"></polyline>
              <line x1="15" y1="12" x2="3" y2="12"></line>
           </svg>
        </div>

        <div className="text-center mb-10">
           <h1 className="text-3xl font-bold text-[#0F172A] mb-4">Forgot Password?</h1>
           <p className="text-sm text-[#64748B] leading-relaxed px-4">
             Don't worry, it happens. Enter your email address below and we'll send you a secure link to reset your password.
           </p>
        </div>

        <form onSubmit={handleSubmit} className="space-y-8">
           <div className="flex flex-col">
              <label className="text-sm font-bold text-[#0F172A] mb-2">Email Address</label>
              <div className="relative">
                 <div className="absolute left-4 top-1/2 -translate-y-1/2 text-[#94A3B8]">
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                       <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path>
                       <polyline points="22,6 12,13 2,6"></polyline>
                    </svg>
                 </div>
                 <input
                   type="email"
                   value={email}
                   onChange={(e) => setEmail(e.target.value)}
                   placeholder="you@example.com"
                   className="w-full h-14 pl-12 pr-4 rounded-xl border border-[#E2E8F0] focus:border-[#F97316] outline-none text-black text-base transition-colors"
                   required
                 />
              </div>
           </div>

           <button
             type="submit"
             className="w-full h-14 bg-[#F97316] text-white rounded-xl font-bold text-base flex items-center justify-center hover:bg-[#EA580C] transition-colors shadow-lg shadow-orange-100"
           >
             Send Reset Link
             <svg className="ml-3" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <line x1="22" y1="2" x2="11" y2="13"></line>
                <polygon points="22 2 15 22 11 13 2 9 22 2"></polygon>
             </svg>
           </button>
        </form>

      </div>

      {/* Success Popup */}
      {showSuccessPopup && (
        <div className="fixed inset-0 z-50 flex items-center justify-center px-6 bg-black/20 backdrop-blur-sm">
          <div className="w-full max-w-[280px] bg-white rounded-2xl p-8 shadow-2xl flex flex-col items-center animate-in zoom-in-95 duration-200">
            <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mb-4">
               <svg width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="#22C55E" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
                  <polyline points="20 6 9 17 4 12"></polyline>
               </svg>
            </div>
            <p className="text-lg font-bold text-[#0F172A] text-center">Sent successfully</p>
          </div>
        </div>
      )}
    </main>
  );
}
