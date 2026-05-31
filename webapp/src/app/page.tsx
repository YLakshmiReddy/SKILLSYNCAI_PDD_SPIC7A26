"use client";

import React, { useState, useEffect } from "react";
import Link from "next/link";
import { Button } from "@/components/ui/Button";

export default function WelcomeScreen() {
  const [showSplash, setShowSplash] = useState(false);
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    // Check if splash has already been shown in this session
    const hasShownSplash = sessionStorage.getItem("hasShownSplash");

    if (!hasShownSplash) {
      setShowSplash(true);
      sessionStorage.setItem("hasShownSplash", "true");
    } else {
      setShowSplash(false);
      return;
    }

    // Progress bar animation
    const interval = setInterval(() => {
      setProgress((prev) => {
        if (prev >= 100) {
          clearInterval(interval);
          return 100;
        }
        return prev + 1;
      });
    }, 30); // 3 seconds total

    // Complete splash after 3.5 seconds
    const timer = setTimeout(() => {
      setShowSplash(false);
    }, 3500);

    return () => {
      clearInterval(interval);
      clearTimeout(timer);
    };
  }, []);

  if (showSplash) {
    return (
      <main className="flex h-[100dvh] w-full items-center justify-center bg-[#DBEAFE] relative overflow-hidden font-sans overscroll-none touch-none">
        {/* Simplified Waves effect using CSS gradients */}
        <div className="absolute inset-0 opacity-40"
             style={{
               backgroundImage: 'radial-gradient(circle at 20% 30%, #3B82F6 0%, transparent 40%), radial-gradient(circle at 80% 70%, #1D4ED8 0%, transparent 40%)',
               filter: 'blur(80px)'
             }}
        />
        
        <div className="z-10 flex flex-col items-center justify-center">
          {/* Logo Box */}
          <div className="relative w-24 h-24 bg-[#1D4ED8] rounded-2xl flex items-center justify-center shadow-lg">
            <div className="w-12 h-12 bg-white rounded-md flex items-center justify-center rotate-45">
               <div className="w-6 h-6 bg-[#1D4ED8] rounded-sm -rotate-45" />
            </div>

            {/* Checkmark Circle */}
            <div className="absolute -bottom-2 -right-2 w-8 h-8 bg-white rounded-lg flex items-center justify-center shadow-md">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#1D4ED8" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
                <polyline points="20 6 9 17 4 12"></polyline>
              </svg>
            </div>
          </div>

          <div className="mt-8 flex flex-col items-center">
            <div className="flex text-4xl font-bold">
              <span className="text-[#0F172A]">SkillSync</span>
              <span className="text-[#1D4ED8] ml-2">Ai</span>
            </div>
            <p className="mt-2 text-[10px] font-bold tracking-[0.2em] text-[#1E40AF]/80 uppercase">
              Professional Growth Engine
            </p>
          </div>
        </div>

        {/* Bottom Loading Section */}
        <div className="absolute bottom-20 flex flex-col items-center w-full px-6">
          <p className="text-sm font-medium text-[#1E293B] mb-4">
            Optimizing your career path...
          </p>
          <div className="w-full max-w-[280px] h-[6px] bg-white/50 rounded-full overflow-hidden">
            <div
              className="h-full bg-[#1D4ED8] transition-all duration-300 ease-out"
              style={{ width: `${progress}%` }}
            />
          </div>
        </div>
      </main>
    );
  }

  return (
    <main className="flex min-h-screen w-full flex-col items-center bg-[#F1F5F9] font-sans overflow-x-hidden">
      {/* Desktop Wrapper - Limits width on large screens to keep it elegant */}
      <div className="w-full max-w-6xl flex flex-col items-center min-h-screen">

        {/* Top Logo Section */}
        <div className="w-full px-6 pt-12 md:pt-16 flex items-center justify-center">
          <div className="w-9 h-9 bg-[#1D4ED8] rounded-lg flex items-center justify-center shadow-sm">
             <div className="w-4 h-4 bg-white rounded-[2px] rotate-45 flex items-center justify-center">
                <div className="w-2 h-2 bg-[#1D4ED8] rounded-[1px] -rotate-45" />
             </div>
          </div>
          <div className="ml-3 flex text-xl font-bold">
            <span className="text-[#0F172A]">SkillSync</span>
            <span className="text-[#1D4ED8] ml-1">Ai</span>
          </div>
        </div>

        {/* Responsive Content Container: Side-by-side on desktop, stacked on mobile */}
        <div className="flex-1 flex flex-col md:flex-row items-center justify-center w-full px-6 md:gap-20 mt-8 md:mt-0">

          {/* Left Side: Illustration & Headline */}
          <div className="flex flex-col items-center md:items-start text-center md:text-left max-w-md">
            {/* Central Icon Box */}
            <div className="w-24 h-24 bg-white rounded-3xl flex items-center justify-center shadow-md mb-8">
               <svg width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="#1D4ED8" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                 <circle cx="12" cy="12" r="3"></circle>
                 <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path>
               </svg>
            </div>

            <h1 className="text-4xl md:text-5xl lg:text-6xl font-extrabold text-[#0F172A] leading-tight mb-6">
              Elevate Your{"\n"}Potential
            </h1>

            <p className="text-[#64748B] text-lg md:text-xl leading-relaxed max-w-sm">
              Your AI-powered skill matching companion.
            </p>
          </div>

          {/* Right Side: Action Card (Desktop) / Bottom Sheet (Mobile) */}
          <div className="w-full max-w-md mt-12 md:mt-0">
            <div className="bg-white rounded-[32px] md:rounded-[40px] shadow-2xl md:shadow-xl p-8 md:p-12 flex flex-col items-center">
              <div className="text-[11px] font-bold text-[#3B82F6] tracking-[0.2em] uppercase mb-10">
                GET STARTED
              </div>

              <div className="w-full space-y-5">
                <Link href="/auth/signin">
                  <button className="w-full h-14 bg-[#1E88E5] text-white rounded-2xl font-bold flex items-center justify-center hover:bg-[#1976D2] transition-all hover:scale-[1.02] shadow-lg shadow-blue-200">
                    <svg className="mr-3" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                      <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4"></path>
                      <polyline points="10 17 15 12 10 7"></polyline>
                      <line x1="15" y1="12" x2="3" y2="12"></line>
                    </svg>
                    Sign In
                  </button>
                </Link>

                <Link href="/auth/signup">
                  <button className="w-full h-14 bg-[#E3F2FD] text-[#1E88E5] rounded-2xl font-bold flex items-center justify-center hover:bg-[#D1E9FF] transition-all hover:scale-[1.02]">
                    <svg className="mr-3" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                      <path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                      <circle cx="8.5" cy="7" r="4"></circle>
                      <line x1="20" y1="8" x2="20" y2="14"></line>
                      <line x1="23" y1="11" x2="17" y2="11"></line>
                    </svg>
                    Create Account
                  </button>
                </Link>
              </div>

              {/* Security Icons */}
              <div className="flex items-center justify-center space-x-8 mt-12 opacity-60">
                 <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#94A3B8" strokeWidth="2"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path><path d="m9 12 2 2 4-4"></path></svg>
                 <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#94A3B8" strokeWidth="2"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path></svg>
                 <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#94A3B8" strokeWidth="2"><rect x="4" y="4" width="16" height="16" rx="2"></rect><rect x="9" y="9" width="6" height="6"></rect><line x1="9" y1="1" x2="9" y2="4"></line><line x1="15" y1="1" x2="15" y2="4"></line><line x1="9" y1="20" x2="9" y2="23"></line><line x1="15" y1="20" x2="15" y2="23"></line><line x1="20" y1="9" x2="23" y2="9"></line><line x1="20" y1="15" x2="23" y2="15"></line><line x1="1" y1="9" x2="4" y2="9"></line><line x1="1" y1="15" x2="4" y2="15"></line></svg>
              </div>

              {/* Terms & Privacy */}
              <div className="mt-12 text-[12px] text-[#64748B] text-center leading-relaxed max-w-[240px]">
                By continuing, you agree to our{" "}
                <Link href="/terms" className="text-[#3B82F6] font-semibold hover:underline">Terms</Link>
                {" & "}
                <Link href="/privacy" className="text-[#3B82F6] font-semibold hover:underline">Privacy Policy</Link>
              </div>
            </div>
          </div>
        </div>

        {/* Bottom spacer for mobile spacing */}
        <div className="h-10 md:hidden" />
      </div>
    </main>
  );
}
