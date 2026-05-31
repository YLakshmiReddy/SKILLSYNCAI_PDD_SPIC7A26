"use client";

import React from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";

export default function PrivacyPolicyScreen() {
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
        <h1 className="ml-2 text-lg font-bold text-[#0F172A]">Privacy Policy</h1>
      </div>

      <div className="w-full max-w-3xl flex flex-col items-center">
        {/* Documentation Banner */}
        <div className="w-full bg-[#F1F5F9] px-6 py-4 flex items-center justify-between">
          <div>
            <p className="text-[10px] font-bold text-[#3B82F6] leading-tight tracking-wider uppercase">SKILLSYNC AI LEGAL</p>
            <p className="text-[10px] font-bold text-[#3B82F6] leading-tight tracking-wider uppercase">DOCUMENTATION</p>
          </div>
          <div className="bg-[#DBEAFE] px-3 py-1 rounded-lg text-[11px] font-bold text-[#2563EB]">
            Version 2.4
          </div>
        </div>

        {/* Horizontal Blue Line */}
        <div className="w-full h-0.5 bg-[#3B82F6]"></div>

        {/* Content Container */}
        <div className="w-full px-6 py-10 pb-[280px]">
          <h2 className="text-3xl font-bold text-[#0F172A] mb-2">Privacy Policy</h2>
          <p className="text-sm text-[#64748B] mb-8">Last updated: October 2023</p>

          <p className="text-sm text-[#475569] leading-relaxed mb-12">
            Welcome to SkillSync AI. Your privacy is critically important to us. This policy explains how we handle your personal data across our platform, including our website, mobile application, and related AI-driven recruitment services. We are committed to protecting your personal information and your right to privacy.
          </p>

          <div className="space-y-12">
            <PrivacySection
              title="1. Information Collection"
              content="We collect personal information that you voluntarily provide to us when you register on the Services, express an interest in obtaining information about us or our products, or otherwise when you contact us."
              icon={<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#3B82F6" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>}
              bullets={[
                "Personal Identifiers: Name, email address, phone number, and professional social media profiles.",
                "Professional Data: Employment history, education, skills, certifications, and resume documents.",
                "Technical Data: IP addresses, browser types, device information, and usage patterns collected via cookies and similar technologies."
              ]}
            />

            <PrivacySection
              title="2. Use of Data"
              content="SkillSync AI uses the collected data for various purposes to enhance your experience and provide our core services:"
              icon={<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#3B82F6" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>}
              bullets={[
                "To facilitate account creation and the logon process.",
                "To match your skills and profile with relevant job opportunities using our AI algorithms.",
                "To provide personalized career recommendations and skill gap analysis.",
                "To send administrative information, such as security alerts or product updates.",
                "To improve our AI models and platform performance through anonymized aggregate data."
              ]}
            />

            <PrivacySection
              title="3. Data Protection"
              content="We implement a variety of security measures to maintain the safety of your personal information. We use state-of-the-art encryption (AES-256) for data at rest and TLS for data in transit."
              icon={<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#3B82F6" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path></svg>}
            >
              <div className="mt-4 bg-[#F1F5F9] rounded-xl p-4 border border-slate-100">
                <p className="text-sm font-bold text-[#0F172A] mb-2">Security Commitment</p>
                <p className="text-xs text-[#475569] leading-relaxed">
                  While we strive to use commercially acceptable means to protect your personal information, no method of transmission over the internet is 100% secure. We conduct regular security audits and vulnerability assessments to ensure the highest level of protection.
                </p>
              </div>
            </PrivacySection>

            <PrivacySection
              title="4. Third-Party Services"
              content="We may share your information with third-party vendors, service providers, contractors, or agents who perform services for us or on our behalf and require access to such information to do that work."
              icon={<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#3B82F6" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg>}
              bullets={[
                "Cloud hosting providers (AWS/Google Cloud).",
                "Analytics tools (Google Analytics/Mixpanel).",
                "Payment processors (Stripe).",
                "Email delivery services (SendGrid)."
              ]}
            >
              <p className="mt-6 text-sm font-semibold text-[#0F172A]">
                We do not sell your personal data to third parties for marketing purposes.
              </p>
            </PrivacySection>
          </div>
        </div>
      </div>

      {/* Floating Footer */}
      <div className="fixed bottom-0 w-full bg-white/80 backdrop-blur-md border-t border-slate-200 p-4 md:p-6 z-30 flex flex-col items-center">
        <div className="w-full max-w-lg bg-[#F1F5F9] rounded-2xl p-4 md:p-6 shadow-sm border border-slate-200 mb-4">
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

        <div className="flex flex-col items-center">
          <p className="text-xs text-[#64748B]">Have questions about our privacy practices?</p>
          <Link href="/contact-privacy" className="mt-2 px-6 py-2 bg-[#1D4ED8] text-white text-xs font-bold rounded-lg hover:bg-[#1E40AF] transition-colors">
            Contact Privacy Team
          </Link>
        </div>
      </div>
    </main>
  );
}

function PrivacySection({ title, content, icon, bullets, children }: {
  title: string,
  content: string,
  icon: React.ReactNode,
  bullets?: string[],
  children?: React.ReactNode
}) {
  return (
    <div className="flex flex-col">
      <div className="flex items-center mb-4">
        <div className="w-8 h-8 flex items-center justify-center bg-[#DBEAFE] rounded-lg">
          {icon}
        </div>
        <h3 className="ml-3 text-lg font-bold text-[#0F172A]">{title}</h3>
      </div>
      <p className="text-sm text-[#475569] leading-relaxed mb-4">
        {content}
      </p>
      {bullets && (
        <ul className="space-y-3 pl-2">
          {bullets.map((bullet, idx) => (
            <li key={idx} className="flex items-start">
              <span className="text-[#3B82F6] font-bold mr-3">•</span>
              <p className="text-[13px] text-[#64748B] leading-relaxed">{bullet}</p>
            </li>
          ))}
        </ul>
      )}
      {children}
    </div>
  );
}
