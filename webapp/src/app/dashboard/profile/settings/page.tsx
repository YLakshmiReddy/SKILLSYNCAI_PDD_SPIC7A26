"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";
import Link from "next/link";

export default function AppSettingsScreen() {
  const router = useRouter();
  const [notifications, setNotifications] = useState(true);
  const [emailUpdates, setEmailUpdates] = useState(true);

  const primaryPurple = "#673AB7";

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
        <h1 className="text-lg font-bold text-[#1A1C1E]">App Settings</h1>
      </div>

      <div className="max-w-2xl mx-auto px-6 py-10">
        
        {/* GENERAL Section */}
        <section className="mb-10">
          <h2 className="text-[11px] font-bold text-[#94A3B8] tracking-[0.15em] uppercase mb-6">GENERAL</h2>
          <SettingsSwitchItem
            title="Push Notifications"
            description="Receive alerts about roadmap updates"
            checked={notifications}
            onToggle={() => setNotifications(!notifications)}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path><path d="M13.73 21a2 2 0 0 1-3.46 0"></path></svg>}
            iconColor={primaryPurple}
          />
        </section>

        {/* ACCOUNT Section */}
        <section className="mb-10">
          <h2 className="text-[11px] font-bold text-[#94A3B8] tracking-[0.15em] uppercase mb-6">ACCOUNT & PRIVACY</h2>
          <div className="space-y-4">
            <SettingsSwitchItem 
              title="Email Updates"
              description="Stay informed about new features"
              checked={emailUpdates}
              onToggle={() => setEmailUpdates(!emailUpdates)}
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path><polyline points="22,6 12,13 2,6"></polyline></svg>}
              iconColor={primaryPurple}
            />

            <SettingsClickItem 
              title="Change Password"
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect><path d="M7 11V7a5 5 0 0 1 10 0v4"></path></svg>}
              iconColor={primaryPurple}
              href="/dashboard/profile/change-password"
            />

            <SettingsClickItem 
              title="Language"
              value="English (US)"
              showArrow={false}
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="2" y1="12" x2="22" y2="12"></line><path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"></path></svg>}
              iconColor={primaryPurple}
              href="#"
            />
          </div>
        </section>

        {/* ABOUT Section */}
        <section className="mb-12">
          <h2 className="text-[11px] font-bold text-[#94A3B8] tracking-[0.15em] uppercase mb-6">ABOUT</h2>
          <div className="space-y-4">
            <SettingsClickItem 
              title="Terms of Service"
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>}
              iconColor={primaryPurple}
              href="/terms"
            />
            <SettingsClickItem
              title="Privacy Policy"
              icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path></svg>}
              iconColor={primaryPurple}
              href="/privacy"
            />
          </div>
        </section>

        <button
          onClick={() => router.back()}
          className="w-full h-14 bg-[#673AB7] hover:bg-[#5E35B1] text-white font-bold rounded-xl shadow-lg transition-all"
        >
          Save Settings
        </button>
      </div>
    </div>
  );
}

function SettingsSwitchItem({ title, description, checked, onToggle, icon, iconColor }: any) {
  return (
    <div className="flex items-center py-3">
      <div className="w-10 h-10 rounded-lg flex items-center justify-center shrink-0" style={{ backgroundColor: `${iconColor}1A`, color: iconColor }}>
        {icon}
      </div>
      <div className="ml-4 flex-1">
        <p className="text-base font-bold text-[#212121]">{title}</p>
        <p className="text-xs text-[#94A3B8] font-medium">{description}</p>
      </div>
      <button 
        onClick={onToggle}
        className={`w-12 h-6 rounded-full transition-colors relative ${checked ? 'bg-[#673AB7]' : 'bg-slate-200'}`}
      >
        <div className={`absolute top-1 w-4 h-4 bg-white rounded-full transition-all ${checked ? 'left-7' : 'left-1'}`} />
      </button>
    </div>
  );
}

function SettingsClickItem({ title, value, icon, iconColor, href, showArrow = true }: any) {
  return (
    <Link href={href} className="flex items-center py-3 hover:bg-slate-50 rounded-xl transition-colors group">
      <div className="w-10 h-10 rounded-lg flex items-center justify-center shrink-0" style={{ backgroundColor: `${iconColor}1A`, color: iconColor }}>
        {icon}
      </div>
      <span className="ml-4 flex-1 text-base font-bold text-[#212121] group-hover:text-indigo-600 transition-colors">{title}</span>
      {value && <span className="mr-3 text-sm font-medium text-slate-400 uppercase tracking-tight">{value}</span>}
      {showArrow && (
        <svg className="text-slate-300 group-hover:text-indigo-600 group-hover:translate-x-1 transition-all" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
          <polyline points="9 18 15 12 9 6"></polyline>
        </svg>
      )}
    </Link>
  );
}
