"use client";

import Link from "next/link";
import { usePathname, useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const router = useRouter();
  const primaryBlue = "#1D4ED8";
  const [isAuthed, setIsAuthed] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("userToken");
    if (!token) {
      router.replace("/auth/signin");
    } else {
      setIsAuthed(true);
    }
  }, [router]);

  if (!isAuthed) {
    return (
      <div className="flex h-screen items-center justify-center bg-[#F8FAFC]">
        <div className="flex flex-col items-center gap-3">
          <svg className="animate-spin h-8 w-8 text-[#1D4ED8]" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
            <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
            <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
          </svg>
          <p className="text-sm text-[#64748B] font-medium">Checking authentication…</p>
        </div>
      </div>
    );
  }

  const navItems = [
    { href: "/dashboard", label: "HOME", icon: HomeIcon },
    { href: "/dashboard/analysis", label: "ANALYSIS", icon: AnalysisIcon },
    { href: "/dashboard/roadmap", label: "ROADMAP", icon: RoadmapIcon },
    { href: "/dashboard/profile", label: "PROFILE", icon: ProfileIcon },
  ];

  return (
    <div className="flex h-screen bg-[#F8FAFC] overflow-hidden text-[#0F172A]">
      {/* Desktop Sidebar */}
      <aside className="hidden md:flex w-64 flex-col border-r border-slate-200 bg-white z-20">
        <div className="h-20 flex items-center px-6 border-b border-slate-100">
          <div className="w-8 h-8 rounded-lg bg-[#1D4ED8] flex items-center justify-center mr-3 shadow-sm">
             <div className="w-4 h-4 bg-white rounded-[2px] rotate-45 flex items-center justify-center">
                <div className="w-2 h-2 bg-[#1D4ED8] rounded-[1px] -rotate-45" />
             </div>
          </div>
          <span className="font-bold text-xl tracking-tight text-[#0F172A]">SkillSync<span className="text-[#1D4ED8]">Ai</span></span>
        </div>
        <nav className="flex-1 px-4 py-8 space-y-2">
          {navItems.map((item) => {
            const isActive = pathname === item.href;
            return (
              <Link
                key={item.href}
                href={item.href}
                className={`flex items-center px-4 py-3 rounded-xl transition-all ${isActive ? 'bg-blue-50 text-[#1D4ED8] font-bold' : 'text-[#64748B] hover:bg-slate-50 hover:text-[#0F172A] font-medium'}`}
              >
                <item.icon className={`w-5 h-5 mr-4 ${isActive ? 'text-[#1D4ED8]' : ''}`} />
                {item.label}
              </Link>
            );
          })}
        </nav>
      </aside>

      {/* Main Content Area */}
      <main className="flex-1 overflow-y-auto relative bg-[#F8FAFC]">
        {children}
      </main>

      {/* Mobile Bottom Navigation */}
      <nav className="md:hidden fixed bottom-0 w-full h-20 bg-white border-t border-slate-100 flex justify-around items-center px-2 z-50 shadow-[0_-4px_20px_-10px_rgba(0,0,0,0.1)]">
          {navItems.map((item) => {
            const isActive = pathname === item.href;
            return (
              <Link
                key={item.href}
                href={item.href}
                className="flex flex-col items-center justify-center w-full h-full relative"
              >
                {isActive && (
                  <div className="absolute top-0 w-12 h-1 bg-[#1D4ED8] rounded-b-full" />
                )}
                <div className={`p-2 rounded-xl transition-colors ${isActive ? 'text-[#1D4ED8]' : 'text-[#64748B]'}`}>
                  <item.icon className="w-6 h-6" />
                </div>
                <span className={`text-[10px] font-bold tracking-wider ${isActive ? 'text-[#1D4ED8]' : 'text-[#64748B]'}`}>{item.label}</span>
              </Link>
            );
          })}
      </nav>
    </div>
  );
}

function HomeIcon({ className }: { className?: string }) {
  return <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round" className={className}><path d="m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/><polyline points="9 22 9 12 15 12 15 22"/></svg>;
}

function AnalysisIcon({ className }: { className?: string }) {
  return <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round" className={className}><path d="m12 3-1.912 5.813a2 2 0 0 1-1.275 1.275L3 12l5.813 1.912a2 2 0 0 1 1.275 1.275L12 21l1.912-5.813a2 2 0 0 1 1.275-1.275L21 12l-5.813-1.912a2 2 0 0 1-1.275-1.275L12 3Z"/><path d="M5 3v4"/><path d="M3 5h4"/><path d="M19 17v4"/><path d="M17 19h4"/></svg>;
}

function RoadmapIcon({ className }: { className?: string }) {
  return <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round" className={className}><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/></svg>;
}

function ProfileIcon({ className }: { className?: string }) {
  return <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round" className={className}><path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>;
}
