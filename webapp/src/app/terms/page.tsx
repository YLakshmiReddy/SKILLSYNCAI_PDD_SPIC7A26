"use client";

import React from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";

export default function TermsOfServiceScreen() {
  const router = useRouter();

  return (
    <main className="flex min-h-screen w-full flex-col items-center bg-[#F8FAFC] font-sans overflow-x-hidden">
      {/* Top Header */}
      <div className="w-full h-16 bg-white border-b border-slate-200 flex items-center px-4 sticky top-0 z-20">
        <button
          onClick={() => router.back()}
          className="p-2 hover:bg-slate-100 rounded-full transition-colors"
        >
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#0F172A" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
        </button>
        <h1 className="ml-2 text-lg font-bold text-[#0F172A]">Terms of Service</h1>
      </div>

      <div className="w-full max-w-3xl flex flex-col items-center">
        {/* Compliance Banner */}
        <div className="w-full bg-[#F1F5F9] px-6 py-4 flex items-center justify-between">
          <div>
            <p className="text-[10px] font-bold text-[#475569] leading-tight tracking-wider">SKILLSYNC AI LEGAL</p>
            <p className="text-[10px] font-bold text-[#475569] leading-tight tracking-wider">COMPLIANCE</p>
          </div>
          <div className="bg-[#DBEAFE] px-2 py-1 rounded text-[11px] font-bold text-[#2563EB]">
            Version 2.4
          </div>
        </div>

        {/* Horizontal Blue Line */}
        <div className="w-full h-0.5 bg-[#3B82F6]"></div>

        {/* Content Container */}
        <div className="w-full px-6 py-10 pb-[300px] md:pb-[350px]">
          <h2 className="text-3xl font-bold text-[#0F172A] mb-2">SkillSync AI Terms of Service</h2>
          <p className="text-sm text-[#64748B] mb-12">Last updated: October 24, 2023</p>

          <div className="space-y-10">
            <TermSection
              title="1. User Agreement"
              content="By accessing or using SkillSync AI, you agree to be bound by these Terms of Service and all applicable laws and regulations. If you do not agree with any of these terms, you are prohibited from using or accessing this site. The materials contained in this website are protected by applicable copyright and trademark law. We grant you a personal, non-exclusive, non-transferable, limited privilege to enter and use the service."
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#3B82F6" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>}
            />

            <TermSection
              title="2. Data Privacy"
              content="Your privacy is important to us. SkillSync AI's Privacy Policy is incorporated into these Terms by reference. Please review our Privacy Policy to understand how we collect, use, and share information about you. We implement industry-standard security measures to protect your data, but no method of transmission over the Internet is 100% secure. You are responsible for maintaining the confidentiality of your account credentials."
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#3B82F6" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path></svg>}
            />

            <TermSection
              title="3. Limitations of Liability"
              content="In no event shall SkillSync AI or its suppliers be liable for any damages (including, without limitation, damages for loss of data or profit, or due to business interruption) arising out of the use or inability to use the materials on SkillSync AI's website. SkillSync AI does not warrant that any of the materials on its website are accurate, complete, or current. We may make changes to the materials contained on its website at any time without notice."
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#3B82F6" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"></path><line x1="12" y1="9" x2="12" y2="13"></line><line x1="12" y1="17" x2="12.01" y2="17"></line></svg>}
            />

            <TermSection
              title="4. Changes to Terms"
              content="SkillSync AI may revise these terms of service for its website at any time without notice. By using this website you are agreeing to be bound by the then current version of these terms of service. We encourage users to frequently check this page for any changes. Your continued use of the service after any modification to these Terms will constitute your acceptance of such modification."
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#3B82F6" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>}
            />
          </div>
        </div>
      </div>

      {/* Sticky Footer */}
      <div className="fixed bottom-0 w-full bg-slate-50/80 backdrop-blur-md border-t border-slate-200 p-4 md:p-6 z-30 flex justify-center">
        <div className="w-full max-w-lg bg-[#F1F5F9] rounded-2xl p-4 md:p-6 shadow-sm border border-slate-200">
           <div className="text-center mb-4">
              <p className="font-bold text-[#0F172A]">Ready to proceed?</p>
              <p className="text-xs text-[#64748B] mt-1">By continuing, you confirm you've read the agreement.</p>
           </div>
           <div className="flex gap-3">
              <Link href="/" className="flex-1">
                <button className="w-full h-12 bg-white border border-slate-300 text-[#475569] rounded-xl text-sm font-semibold hover:bg-slate-50 transition-colors">
                  Decline
                </button>
              </Link>
              <Link href="/auth/signup" className="flex-[1.2]">
                <button className="w-full h-12 bg-[#3B82F6] text-white rounded-xl text-sm font-semibold hover:bg-[#2563EB] transition-colors shadow-lg shadow-blue-100">
                  Accept & Continue
                </button>
              </Link>
           </div>
        </div>
      </div>
    </main>
  );
}

function TermSection({ title, content, icon }: { title: string, content: string, icon: React.ReactNode }) {
  return (
    <div className="flex flex-col">
      <div className="flex items-center mb-4">
        <div className="w-8 h-8 flex items-center justify-center">
          {icon}
        </div>
        <h3 className="ml-3 text-lg font-bold text-[#0F172A]">{title}</h3>
      </div>
      <p className="text-sm text-[#475569] leading-relaxed pl-11">
        {content}
      </p>
    </div>
  );
}
