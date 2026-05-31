"use client";

import React from "react";
import Link from "next/link";

export default function DashboardHome() {
  const primaryBlue = "#1D4ED8";
  const [matchScore, setMatchScore] = React.useState(0);
  const [userRole, setUserRole] = React.useState("Software Engineer");
  const [skillsFound, setSkillsFound] = React.useState<string[]>(["Python", "UX Design", "Project Management"]);
  const [skillsLacking, setSkillsLacking] = React.useState<string[]>(["Cloud Architecture", "React", "Agile Methodologies"]);

  React.useEffect(() => {
    const score = localStorage.getItem("matchScore");
    if (score) setMatchScore(parseInt(score));

    const data = localStorage.getItem("userData");
    if (data) {
      const user = JSON.parse(data);
      if (user.role) setUserRole(user.role);
    }

    const storedAnalysis = localStorage.getItem("analysisResult");
    if (storedAnalysis) {
      try {
        const result = JSON.parse(storedAnalysis);
        if (result.pros) {
          const prosList = result.pros.split('.').map((s: string) => s.trim()).filter(Boolean).slice(0, 3);
          if (prosList.length > 0) setSkillsFound(prosList);
        }
        if (result.cons) {
          const consList = result.cons.split(/[,.]/).map((s: string) => s.trim()).filter(Boolean).slice(0, 3);
          if (consList.length > 0) setSkillsLacking(consList);
        }
      } catch (e) {
        console.error(e);
      }
    }
  }, []);

  const scoreStatus = React.useMemo(() => {
    if (matchScore >= 80) return "EXCELLENT";
    if (matchScore >= 60) return "GOOD";
    if (matchScore >= 40) return "AVERAGE";
    if (matchScore > 0) return "BEGINNER";
    return "NO DATA";
  }, [matchScore]);

  return (
    <div className="min-h-screen w-full bg-gradient-to-b from-[#E3F2FD] to-white font-sans overflow-x-hidden pb-20 md:pb-10">
      {/* Desktop/Mobile Header */}
      <div className="w-full h-20 bg-white/90 backdrop-blur-sm border-b border-slate-200 flex items-center justify-center sticky top-0 z-20">
        <div className="flex items-center">
          <div className="w-9 h-9 bg-[#1D4ED8] rounded-lg flex items-center justify-center shadow-sm">
             <div className="w-5 h-5 bg-white rounded-[2px] rotate-45 flex items-center justify-center">
                <div className="w-2.5 h-2.5 bg-[#1D4ED8] rounded-[1px] -rotate-45" />
             </div>
          </div>
          <span className="ml-3 text-xl font-bold text-[#1D4ED8] tracking-tight">SkillSync Ai</span>
        </div>
      </div>

      <div className="max-w-4xl mx-auto px-6 py-10 space-y-8">

        {/* Resume Match Score Card */}
        <div className="w-full bg-white rounded-[32px] p-8 flex flex-col items-center shadow-xl border border-slate-100 relative overflow-hidden">
          <div className="absolute top-[-20%] right-[-10%] w-64 h-64 bg-blue-500/5 rounded-full blur-3xl pointer-events-none" />
          <div className="absolute bottom-[-20%] left-[-10%] w-64 h-64 bg-blue-500/5 rounded-full blur-3xl pointer-events-none" />
          
          <h2 className="text-[11px] font-bold text-[#94A3B8] tracking-[0.15em] uppercase mb-8">RESUME MATCH SCORE</h2>

          <div className="relative w-48 h-48 flex items-center justify-center">
             {/* Progress Circle */}
             <svg className="w-full h-full transform -rotate-90" viewBox="0 0 100 100">
               <circle cx="50" cy="50" r="42" stroke="#E2E8F0" strokeWidth="10" fill="none" />
               <circle
                 cx="50" cy="50" r="42"
                 stroke={matchScore > 0 ? primaryBlue : "#CBD5E1"}
                 strokeWidth="10"
                 fill="none"
                 strokeDasharray="263.8"
                 strokeDashoffset={263.8 - (263.8 * matchScore) / 100}
                 strokeLinecap="round"
                 className="transition-all duration-1000 ease-out"
               />
             </svg>
             <div className="absolute flex flex-col items-center">
               <span className="text-5xl font-black text-[#0F172A]">{matchScore}%</span>
               <span className={`text-xs font-bold mt-1 ${matchScore > 0 ? 'text-[#1D4ED8]' : 'text-slate-400'}`}>{scoreStatus}</span>
             </div>
          </div>

          <p className="mt-8 text-[#64748B] text-center text-sm leading-relaxed max-w-sm">
            {matchScore > 0
              ? `Your skills strongly align with current ${userRole} market demands.`
              : "Upload your resume to see how well your skills match the industry requirements."}
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* Top 3 Skills Found */}
          <div className="bg-white rounded-2xl p-6 shadow-sm border border-slate-100">
            <div className="flex items-center mb-6">
              <div className="w-10 h-10 rounded-full bg-[#DCFCE7] flex items-center justify-center mr-3">
                <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#16A34A" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                  <polyline points="20 6 9 17 4 12"></polyline>
                </svg>
              </div>
              <h3 className="text-lg font-bold text-[#0F172A]">Top 3 Skills Found</h3>
            </div>

            <div className="space-y-1">
              {skillsFound.map((skill, index) => (
                <React.Fragment key={skill}>
                  <SkillItem name={skill} level={index === 0 ? "Expert" : index === 1 ? "Expert" : "Advanced"} color={index === 2 ? primaryBlue : "#16A34A"} />
                  {index < skillsFound.length - 1 && <div className="h-px bg-[#F1F5F9] w-full" />}
                </React.Fragment>
              ))}
            </div>
          </div>

          {/* Top 3 Skills Lacking */}
          <div className="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 group cursor-pointer hover:border-blue-200 transition-colors">
            <div className="flex items-center mb-6">
              <div className="w-10 h-10 rounded-lg bg-[#FEF3C7] flex items-center justify-center mr-3">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#D97706" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                  <path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"></path>
                  <line x1="12" y1="9" x2="12" y2="13"></line>
                  <line x1="12" y1="17" x2="12.01" y2="17"></line>
                </svg>
              </div>
              <h3 className="text-lg font-bold text-[#0F172A]">Top 3 Skills Lacking</h3>
            </div>

            <div className="space-y-1">
              {skillsLacking.map((skill, index) => (
                <React.Fragment key={skill}>
                  <LackingSkillItem name={skill} />
                  {index < skillsLacking.length - 1 && <div className="h-px bg-[#F1F5F9] w-full" />}
                </React.Fragment>
              ))}
            </div>
          </div>
        </div>

        {/* Upload Action */}
        <div className="pt-4 pb-8 flex flex-col items-center">
          <Link href="/dashboard/upload-resume" className="w-full">
            <button className="w-full h-14 bg-[#1D4ED8] text-white rounded-xl font-bold flex items-center justify-center hover:bg-[#1E40AF] transition-all shadow-lg shadow-blue-100">
              <svg className="mr-3" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
                <polyline points="17 8 12 3 7 8"></polyline>
                <line x1="12" y1="3" x2="12" y2="15"></line>
              </svg>
              Upload New Resume
            </button>
          </Link>
          <p className="mt-4 text-[12px] text-[#94A3B8] font-medium">PDF, DOCX up to 10MB supported</p>
        </div>

      </div>
    </div>
  );
}

function SkillItem({ name, level, color }: { name: string, level: string, color: string }) {
  return (
    <div className="flex justify-between items-center py-3">
      <span className="text-sm font-semibold text-[#334155]">{name}</span>
      <div
        className="px-3 py-1 rounded-md text-[11px] font-bold"
        style={{ backgroundColor: `${color}1A`, color: color }}
      >
        {level}
      </div>
    </div>
  );
}

function LackingSkillItem({ name }: { name: string }) {
  return (
    <div className="flex justify-between items-center py-3">
      <span className="text-sm font-semibold text-[#334155]">{name}</span>
      <Link href="/dashboard/roadmap" className="flex items-center text-[12px] font-bold text-[#3B82F6] hover:underline">
        LEARN
        <svg className="ml-1" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
          <line x1="5" y1="12" x2="19" y2="12"></line>
          <polyline points="12 5 19 12 12 19"></polyline>
        </svg>
      </Link>
    </div>
  );
}
