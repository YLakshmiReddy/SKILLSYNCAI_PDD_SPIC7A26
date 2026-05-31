"use client";

import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

export default function ContactPrivacyScreen() {
  const router = useRouter();
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [showSuccessPopup, setShowSuccessPopup] = useState(false);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (fullName && email && message) {
      setShowSuccessPopup(true);
    }
  };

  useEffect(() => {
    if (showSuccessPopup) {
      const timer = setTimeout(() => {
        setShowSuccessPopup(false);
        router.push("/privacy");
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [showSuccessPopup, router]);

  return (
    <main className="flex min-h-screen w-full flex-col items-center bg-[#F8FAFC] font-sans overflow-x-hidden">
      {/* Header */}
      <div className="w-full h-16 bg-white border-b border-slate-200 flex items-center px-4 sticky top-0 z-30">
        <button
          onClick={() => router.back()}
          className="p-2 hover:bg-slate-100 rounded-full transition-colors"
        >
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#0F172A" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
        </button>
        <h1 className="ml-2 text-lg font-bold text-[#0F172A]">Contact Privacy Team</h1>
      </div>

      <div className="w-full max-w-2xl flex flex-col">
        {/* Hero Section */}
        <div className="w-full h-44 bg-gradient-to-b from-[#DBEAFE] to-[#F8FAFC] p-8 flex flex-col justify-end">
          <div className="bg-[#3B82F6]/10 px-2 py-1 rounded w-fit mb-2">
            <p className="text-[10px] font-bold text-[#2563EB] tracking-wider">SECURITY FIRST</p>
          </div>
          <h2 className="text-3xl font-bold text-[#0F172A]">Privacy is our priority</h2>
        </div>

        <div className="px-6 py-4">
          <p className="text-sm text-[#64748B] leading-relaxed mb-8">
            SkillSync AI is committed to protecting your data. If you have questions regarding our privacy practices or wish to exercise your rights, please reach out to our dedicated team.
          </p>

          {/* Email Card */}
          <div className="w-full bg-[#DBEAFE]/30 rounded-xl p-4 border border-[#DBEAFE] flex items-center mb-10">
            <div className="w-10 h-10 bg-[#3B82F6] rounded-lg flex items-center justify-center">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="white" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                <circle cx="12" cy="12" r="4"></circle>
                <path d="M16 8v5a3 3 0 0 0 6 0v-1a10 10 0 1 0-3.92 7.94"></path>
              </svg>
            </div>
            <div className="ml-4">
              <p className="text-[10px] font-bold text-[#64748B] tracking-wider uppercase">DIRECT EMAIL</p>
              <p className="text-base font-bold text-[#2563EB]">privacy@skillsync.ai</p>
            </div>
          </div>

          {/* Form */}
          <form onSubmit={handleSubmit} className="space-y-6 pb-20">
            <div className="flex flex-col">
              <label className="text-sm font-bold text-[#0F172A] mb-2">Full Name</label>
              <input
                type="text"
                value={fullName}
                onChange={(e) => setFullName(e.target.value)}
                placeholder="John Doe"
                className="w-full h-12 px-4 rounded-xl border border-slate-200 focus:border-[#3B82F6] outline-none text-black text-base transition-colors"
                required
              />
            </div>

            <div className="flex flex-col">
              <label className="text-sm font-bold text-[#0F172A] mb-2">Email Address</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="john@example.com"
                className="w-full h-12 px-4 rounded-xl border border-slate-200 focus:border-[#3B82F6] outline-none text-black text-base transition-colors"
                required
              />
            </div>

            <div className="flex flex-col">
              <label className="text-sm font-bold text-[#0F172A] mb-2">Message / Inquiry</label>
              <textarea
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                placeholder="How can our privacy team help you today?"
                className="w-full h-40 p-4 rounded-xl border border-slate-200 focus:border-[#3B82F6] outline-none text-black text-base transition-colors resize-none"
                required
              />
            </div>

            <button
              type="submit"
              className="w-full h-14 bg-[#1D4ED8] text-white rounded-xl font-bold flex items-center justify-center hover:bg-[#1E40AF] transition-colors"
            >
              <svg className="mr-3" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <line x1="22" y1="2" x2="11" y2="13"></line>
                <polygon points="22 2 15 22 11 13 2 9 22 2"></polygon>
              </svg>
              Send Message
            </button>
          </form>
        </div>
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
