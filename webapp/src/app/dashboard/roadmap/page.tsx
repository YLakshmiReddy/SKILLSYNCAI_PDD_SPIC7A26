"use client";

import React from "react";
import Link from "next/link";

export default function RoadmapScreen() {
  const primaryPurple = "#5C6BC0";

  return (
    <div className="min-h-screen w-full bg-gradient-to-b from-[#F3F3FF] to-white font-sans pb-24 md:pb-10">
      {/* Header */}
      <div className="w-full h-16 bg-white/80 backdrop-blur-sm border-b border-slate-200 flex items-center px-6 sticky top-0 z-20">
        <div className="flex items-center">
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke={primaryPurple} strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon>
          </svg>
          <span className="ml-2 text-base font-bold text-[#1A1C1E]">SkillSync AI</span>
        </div>
      </div>

      <div className="max-w-3xl mx-auto px-6 py-12">
        <div className="mb-12">
          <h1 className="text-4xl md:text-5xl font-black text-[#1A1C1E] mb-4">Roadmap</h1>
          <p className="text-[#757575] text-base md:text-lg leading-relaxed max-w-2xl">
            Forge your path through the digital canyon. Track your progression and discover specialized learning trajectories.
          </p>
        </div>

        <div className="mb-8">
          <h2 className="text-lg font-bold text-[#1A1C1E] uppercase tracking-wide">Roadmap Categories</h2>
        </div>
        
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <RoadmapCategoryCard
            icon={
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                <circle cx="8.5" cy="7" r="4"></circle>
                <polyline points="17 11 19 13 23 9"></polyline>
              </svg>
            }
            title="Role Based Roadmaps"
            description="Structured learning paths curated for specific job titles like AI Engineer, Data Architect, or UI/UX Lead."
            href="/dashboard/roadmap/role-based"
            primaryPurple={primaryPurple}
          />
          
          <RoadmapCategoryCard
            icon={
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <circle cx="12" cy="12" r="3"></circle>
                <path d="M12 5a3 3 0 1 0-5.997.125 4 4 0 0 0-2.526 5.77 4 4 0 0 0 .556 6.588A4 4 0 1 0 12 18Z"></path>
                <path d="M12 5a3 3 0 1 1 5.997.125 4 4 0 0 1 2.526 5.77 4 4 0 0 1-.556 6.588A4 4 0 1 1 12 18Z"></path>
              </svg>
            }
            title="Skill Based Roadmaps"
            description="Deep dive into specific technologies or soft skills. Master React, Python, or Project Management at your own pace."
            href="/dashboard/roadmap/skill-based"
            primaryPurple={primaryPurple}
          />
          
          <RoadmapCategoryCard
            icon={
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <circle cx="12" cy="12" r="10"></circle>
                <path d="M9.09 9a3 3 0 0 1 5.83 1c0 2-3 3-3 3"></path>
                <line x1="12" y1="17" x2="12.01" y2="17"></line>
              </svg>
            }
            title="Project Ideas"
            description="Apply your knowledge with AI-suggested projects that challenge your current skill level and build your portfolio."
            href="/dashboard/roadmap/project-ideas"
            primaryPurple={primaryPurple}
          />
          
          <RoadmapCategoryCard
            icon={
              <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path>
                <path d="m9 12 2 2 4-4"></path>
              </svg>
            }
            title="Best Practices"
            description="Curated guides on clean code, system design, and AI ethics to ensure your skills meet professional industry standards."
            href="/dashboard/roadmap/best-practices"
            primaryPurple={primaryPurple}
          />
        </div>
      </div>
    </div>
  );
}

function RoadmapCategoryCard({ icon, title, description, href, primaryPurple }: {
  icon: React.ReactNode,
  title: string,
  description: string,
  href: string,
  primaryPurple: string
}) {
  return (
    <Link href={href} className="group">
      <div className="bg-white rounded-2xl p-6 h-full flex flex-col shadow-sm border border-slate-100 hover:border-indigo-200 hover:shadow-md transition-all">
        <div className="w-10 h-10 rounded-lg bg-[#E8EAF6] flex items-center justify-center mb-6" style={{ color: primaryPurple }}>
          {icon}
        </div>
        <h3 className="text-xl font-bold text-[#1A1C1E] mb-3 group-hover:text-[#5C6BC0] transition-colors">{title}</h3>
        <p className="text-[#757575] text-sm leading-relaxed flex-1">
          {description}
        </p>
      </div>
    </Link>
  );
}
