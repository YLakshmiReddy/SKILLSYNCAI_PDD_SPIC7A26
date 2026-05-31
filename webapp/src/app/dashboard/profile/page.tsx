"use client";

import React from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";

export default function ProfileScreen() {
  const router = useRouter();
  const [user, setUser] = React.useState<{ full_name?: string; email?: string } | null>(null);

  React.useEffect(() => {
    const data = localStorage.getItem("userData");
    if (data) {
      try {
        setUser(JSON.parse(data));
      } catch (e) {
        console.error(e);
      }
    }
  }, []);
  
  const primaryPurple = "#673AB7";
  const skillChipBg = "#E8EAF6";
  const skillChipText = "#5C6BC0";
  const skillChipBgAlt = "#FFF3E0";
  const skillChipTextAlt = "#FF7043";

  return (
    <div className="min-h-screen w-full bg-white font-sans pb-24 md:pb-10">
      
      {/* Header Profile Section */}
      <div className="w-full bg-gradient-to-b from-[#FFE0CC] to-white pt-12 pb-10">
        <div className="max-w-4xl mx-auto px-6 flex flex-col items-center">
          {/* Avatar */}
          <div className="w-32 h-32 rounded-full bg-slate-100 border-4 border-white shadow-lg overflow-hidden flex items-center justify-center relative group cursor-pointer">
            <svg width="60" height="60" viewBox="0 0 24 24" fill="none" stroke="#94A3B8" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
              <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
              <circle cx="12" cy="7" r="4"></circle>
            </svg>
            <div className="absolute inset-0 bg-black/20 opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="white" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                <path d="M12 20h9"></path>
                <path d="M16.5 3.5a2.121 2.121 0 0 1 3 3L7 19l-4 1 1-4L16.5 3.5z"></path>
              </svg>
            </div>
          </div>

          <h1 className="mt-6 text-3xl font-bold text-[#1A1C1E]">{user?.full_name || "Alex Rivera"}</h1>
          <p className="mt-1 text-base font-bold text-[#673AB7]">{user?.email || "alex.rivera@example.com"}</p>
        </div>
      </div>

      <div className="max-w-3xl mx-auto px-6">
        {/* Skill Summary Section */}
        <div className="mb-10">
          <h3 className="text-[11px] font-bold text-[#424242] tracking-[0.15em] uppercase mb-4">SKILL SUMMARY</h3>
          <div className="flex flex-wrap gap-2">
            <SkillChip text="Python" bgColor={skillChipBg} textColor={skillChipText} />
            <SkillChip text="SQL" bgColor={skillChipBg} textColor={skillChipText} />
            <SkillChip text="Tableau" bgColor={skillChipBg} textColor={skillChipText} />
            <SkillChip text="AI Models" bgColor={skillChipBgAlt} textColor={skillChipTextAlt} />
          </div>
        </div>

        <div className="h-px bg-slate-100 w-full mb-8" />

        {/* Menu Items */}
        <div className="space-y-2 mb-10">
          <ProfileMenuItem
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>}
            title="Account Information"
            iconColor="#7E57C2"
            href="/dashboard/profile/account"
          />
          <ProfileMenuItem
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline></svg>}
            title="Resume Management"
            iconColor="#5C6BC0"
            href="/dashboard/profile/resume"
          />
          <ProfileMenuItem
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg>}
            title="App Settings"
            iconColor="#7986CB"
            href="/dashboard/profile/settings"
          />
          <ProfileMenuItem
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><path d="M9.09 9a3 3 0 0 1 5.83 1c0 2-3 3-3 3"></path><line x1="12" y1="17" x2="12.01" y2="17"></line></svg>}
            title="Help & Support"
            iconColor="#9FA8DA"
            href="/dashboard/profile/help"
          />
        </div>

        {/* Sign Out Button */}
        <button
          onClick={() => {
            localStorage.removeItem("userToken");
            localStorage.removeItem("userData");
            router.push("/");
          }}
          className="w-full h-14 bg-[#FFEBEE] hover:bg-[#FFCDD2] text-[#E53935] rounded-xl font-bold flex items-center justify-center transition-colors mb-12"
        >
          <svg className="mr-3" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
            <polyline points="16 17 21 12 16 7"></polyline>
            <line x1="21" y1="12" x2="9" y2="12"></line>
          </svg>
          Sign Out
        </button>

        {/* Footer Branding */}
        <div className="flex flex-col items-center pb-12">
           <div className="flex items-center">
              <span className="text-xl font-extrabold text-[#424242]">SkillSync</span>
              <span className="text-xl font-extrabold text-[#BF360C] ml-1">Ai</span>
           </div>
           <p className="mt-1 text-xs text-[#94A3B8] font-medium tracking-wide">Version 2.4.0</p>
        </div>
      </div>
    </div>
  );
}

function SkillChip({ text, bgColor, textColor }: { text: string, bgColor: string, textColor: string }) {
  return (
    <div
      className="px-4 py-1.5 rounded-full text-xs font-bold shadow-sm"
      style={{ backgroundColor: bgColor, color: textColor }}
    >
      {text}
    </div>
  );
}

function ProfileMenuItem({ icon, title, iconColor, href }: {
  icon: React.ReactNode,
  title: string,
  iconColor: string,
  href: string
}) {
  return (
    <Link href={href} className="flex items-center p-4 hover:bg-slate-50 rounded-xl transition-colors group">
      <div
        className="w-10 h-10 rounded-lg flex items-center justify-center shrink-0"
        style={{ backgroundColor: `${iconColor}1A`, color: iconColor }}
      >
        {icon}
      </div>
      <span className="ml-4 flex-1 text-base font-bold text-[#212121] group-hover:text-indigo-600 transition-colors">{title}</span>
      <svg className="text-slate-300 group-hover:text-indigo-600 group-hover:translate-x-1 transition-all" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
        <polyline points="9 18 15 12 9 6"></polyline>
      </svg>
    </Link>
  );
}
