"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";

export default function HelpSupportScreen() {
  const router = useRouter();
  const [message, setMessage] = useState("");
  const [sent, setSent] = useState(false);

  const primaryPurple = "#673AB7";

  const handleSend = () => {
    if (message.trim()) {
      setSent(true);
      setTimeout(() => {
        setSent(false);
        setMessage("");
      }, 3000);
    }
  };

  return (
    <div className="min-h-screen w-full bg-white font-sans pb-24 md:pb-10">
      {/* Header */}
      <div className="w-full h-16 bg-white border-b border-slate-200 flex items-center px-4 sticky top-0 z-20">
        <button
          onClick={() => router.back()}
          className="p-2 hover:bg-slate-100 rounded-full transition-colors mr-2"
        >
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#0F172A" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
        </button>
        <h1 className="text-lg font-bold text-[#1A1C1E]">Help & Support</h1>
      </div>

      <div className="max-w-2xl mx-auto px-6 py-10">
        
        {/* Illustration Section */}
        <div className="w-full h-44 bg-[#673AB7]/5 rounded-3xl flex flex-col items-center justify-center mb-10 border border-[#673AB7]/10">
           <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke={primaryPurple} strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
              <path d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 1 1-7.6-14 8.38 8.38 0 0 1 3.8.9L21 1.5z"></path>
              <path d="M11 12h2a2 2 0 1 0-2-2v2z"></path>
              <line x1="12" y1="16" x2="12.01" y2="16"></line>
           </svg>
           <h2 className="mt-4 text-xl font-bold text-[#1A1C1E]">How can we help you?</h2>
        </div>

        <div className="mb-10">
          <h3 className="text-[11px] font-bold text-[#212121] tracking-[0.15em] uppercase mb-4">CONTACT SUPPORT</h3>
          
          <div className="relative">
            <textarea
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              placeholder="Describe your issue or query here..."
              className="w-full h-52 p-5 bg-white border border-[#757575]/30 rounded-xl text-[#1A1C1E] text-base focus:border-[#673AB7] outline-none transition-all resize-none shadow-sm"
            />
          </div>
        </div>

        <button
          onClick={handleSend}
          disabled={sent || !message.trim()}
          className={`w-full h-14 rounded-xl font-bold text-base transition-all flex items-center justify-center shadow-lg ${sent ? 'bg-green-500 text-white' : 'bg-[#673AB7] hover:bg-[#5E35B1] text-white shadow-indigo-100'}`}
        >
          {sent ? (
            <>
              <svg className="mr-2" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round"><polyline points="20 6 9 17 4 12"></polyline></svg>
              Message Sent
            </>
          ) : (
            "Send Message"
          )}
        </button>

        {sent && (
          <p className="mt-4 text-center text-sm font-medium text-green-600 animate-in fade-in slide-in-from-top-2">
            We've received your inquiry and will get back to you soon.
          </p>
        )}
      </div>
    </div>
  );
}
