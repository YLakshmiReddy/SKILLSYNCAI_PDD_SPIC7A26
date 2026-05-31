"use client";

import React from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";

export default function RoleBasedRoadmapsScreen() {
  const router = useRouter();

  const pathPrimary = "#1A237E";

  const mainRoles = [
    { title: "Frontend", href: "/dashboard/roadmap/frontend", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><polyline points="16 18 22 12 16 6"/><polyline points="8 6 2 12 8 18"/></svg> },
    { title: "Backend", href: "/dashboard/roadmap/backend", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><ellipse cx="12" cy="5" rx="9" ry="3"/><path d="M21 12c0 1.66-4 3-9 3s-9-1.34-9-3"/><path d="M3 5v14c0 1.66 4 3 9 3s9-1.34 9-3V5"/></svg> },
    { title: "Full Stack", href: "/dashboard/roadmap/full-stack", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><polygon points="12 2 2 7 12 12 22 7 12 2"/><polyline points="2 17 12 22 22 17"/><polyline points="2 12 12 17 22 12"/></svg> },
    { title: "AI Engineer", href: "/dashboard/roadmap/ai-engineer", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M12 5a3 3 0 1 0-5.997.125 4 4 0 0 0-2.526 5.77 4 4 0 0 0 .556 6.588A4 4 0 1 0 12 18Z"/><path d="M12 5a3 3 0 1 1 5.997.125 4 4 0 0 1 2.526 5.77 4 4 0 0 1-.556 6.588A4 4 0 1 1 12 18Z"/><path d="M15 13a4.5 4.5 0 0 1-3-4 4.5 4.5 0 0 1-3 4"/><path d="M17.599 6.5a3 3 0 0 0 .399-1.375"/></svg> },
    { title: "DevOps", href: "/dashboard/roadmap/devops", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M4 12a8 8 0 0 1 16 0A8 8 0 0 1 4 12Z"/><path d="M4 12h16"/><path d="M12 4v16"/></svg> },
    { title: "UX Design", href: "/dashboard/roadmap/ux-design", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="13.5" cy="6.5" r=".5"/><circle cx="17.5" cy="10.5" r=".5"/><circle cx="8.5" cy="7.5" r=".5"/><circle cx="6.5" cy="12.5" r=".5"/><path d="M12 2C6.5 2 2 6.5 2 12s4.5 10 10 10c.926 0 1.648-.746 1.648-1.688 0-.437-.18-.835-.437-1.125-.29-.289-.438-.652-.438-1.125a1.64 1.64 0 0 1 1.668-1.668h1.996c3.051 0 5.555-2.503 5.555-5.554C21.965 6.012 17.461 2 12 2z"/></svg> },
    { title: "Cyber Security", href: "/dashboard/roadmap/cyber-security", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/></svg> },
    { title: "Game Developer", href: "/dashboard/roadmap/game-developer", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><rect x="2" y="6" width="20" height="12" rx="2" ry="2"/><line x1="6" y1="12" x2="10" y2="12"/><line x1="8" y1="10" x2="8" y2="14"/><line x1="15" y1="13" x2="15.01" y2="13"/><line x1="18" y1="11" x2="18.01" y2="11"/></svg> },
    { title: "Server Side Game Dev", href: "/dashboard/roadmap/server-side-game-dev", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><rect x="2" y="2" width="20" height="8" rx="2" ry="2"/><rect x="2" y="14" width="20" height="8" rx="2" ry="2"/><line x1="6" y1="6" x2="6.01" y2="6"/><line x1="6" y1="18" x2="6.01" y2="18"/></svg> },
    { title: "MLOps", href: "/dashboard/roadmap/mlops", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/></svg> },
    { title: "Product Manager", href: "/dashboard/roadmap/product-manager", icon: <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><rect x="3" y="3" width="18" height="18" rx="2" ry="2"/><line x1="9" y1="3" x2="9" y2="21"/><line x1="15" y1="3" x2="15" y2="21"/></svg> },
  ];

  const specializedRoles = [
    { title: "DevSecOps", href: "/dashboard/roadmap/devsecops" },
    { title: "Data Analysis", href: "/dashboard/roadmap/dataanalyst" },
    { title: "BI Analyst", href: "/dashboard/roadmap/bi-analyst" },
    { title: "Developer Relations", href: "/dashboard/roadmap/developer-relations" },
    { title: "Data Science", href: "/dashboard/roadmap/ai-data-scientist-path" },
    { title: "Data Engineer", href: "/dashboard/roadmap/dataengineer" },
    { title: "Android", href: "/dashboard/roadmap/android-developer" },
    { title: "Machine Learning", href: "/dashboard/roadmap/machinelearning" },
    { title: "PostgreSQL", href: "/dashboard/roadmap/postgresql" },
    { title: "iOS", href: "/dashboard/roadmap/skillsync-ai-ios-developer" },
    { title: "Blockchain", href: "/dashboard/roadmap/blockchain" },
    { title: "QA", href: "/dashboard/roadmap/qa-engineer-evolutionary-path" },
    { title: "Software Architect", href: "/dashboard/roadmap/software-architect" },
    { title: "Technical Writer", href: "/dashboard/roadmap/technical-writer" }
  ];

  return (
    <div className="min-h-screen w-full bg-gradient-to-b from-[#F3F5FF] to-white font-sans pb-24 md:pb-10 overflow-x-hidden">
      {/* Header */}
      <div className="w-full h-16 bg-white/90 backdrop-blur-sm border-b border-slate-200 flex items-center px-4 sticky top-0 z-20">
        <button onClick={() => router.back()} className="p-2 -ml-2 hover:bg-slate-100 rounded-full transition-colors mr-2">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke={pathPrimary} strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
        </button>
        <span className="text-[11px] font-bold text-[#94A3B8] tracking-[0.2em] uppercase">Role Based Roadmaps</span>
      </div>

      <div className="max-w-4xl mx-auto px-6 py-10">
        {/* Hero Section */}
        <div className="w-full bg-white/60 rounded-[32px] p-8 mb-12 shadow-sm border border-white/50 backdrop-blur-sm">
          <h1 className="text-4xl md:text-5xl font-black text-[#1A237E] mb-4 leading-[1.1]">
            Choose<br />your Path.
          </h1>
          <p className="text-[#475569] text-base md:text-lg leading-relaxed max-w-2xl">
            Expert-curated career roadmaps driven by AI. Select a role to begin your journey through the digital canyon of knowledge.
          </p>
        </div>

        {/* Main Roles Grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 mb-16">
          {mainRoles.map((role, idx) => (
            <Link key={idx} href={role.href} className="group">
              <div className="bg-white rounded-2xl p-6 h-full shadow-sm border border-slate-100 hover:shadow-lg hover:border-indigo-200 transition-all hover:-translate-y-1">
                <div className="w-10 h-10 rounded-lg bg-[#E8EAF6] flex items-center justify-center mb-6 text-[#1A237E]">
                  <div className="w-5 h-5">{role.icon}</div>
                </div>
                <h3 className="text-lg font-bold text-[#0F172A] group-hover:text-[#1A237E] transition-colors">{role.title}</h3>
              </div>
            </Link>
          ))}
        </div>

        {/* Specialized Section */}
        <div className="pt-4">
           <h2 className="text-2xl font-bold text-[#1A237E] mb-8 leading-tight">Specialized &<br />Leadership Roles</h2>

           <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
              {specializedRoles.map((role, idx) => (
                <Link key={idx} href={role.href} className="group">
                  <div className="bg-white/70 rounded-xl p-5 h-[100px] flex flex-col justify-between shadow-sm border border-white/40 hover:bg-white hover:border-indigo-100 transition-all">
                    <div className="text-[#1A237E]/20">
                      <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                        <rect x="3" y="3" width="7" height="7"></rect>
                        <rect x="14" y="3" width="7" height="7"></rect>
                        <rect x="14" y="14" width="7" height="7"></rect>
                        <rect x="3" y="14" width="7" height="7"></rect>
                      </svg>
                    </div>
                    <h4 className="text-[13px] font-bold text-[#1A237E] leading-tight">{role.title}</h4>
                  </div>
                </Link>
              ))}
           </div>
        </div>
      </div>
    </div>
  );
}
