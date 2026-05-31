"use client";

import React, { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

export default function AnalysisResultsScreen() {
  const router = useRouter();
  const [matchScore, setMatchScore] = useState(0);
  const [result, setResult] = useState<any>(null);

  const primaryBlue = "#1E88E5";

  useEffect(() => {
    const score = localStorage.getItem("matchScore");
    if (score) setMatchScore(parseInt(score));

    const data = localStorage.getItem("analysisResult");
    if (data) setResult(JSON.parse(data));
  }, []);

  const compatibilityText = React.useMemo(() => {
    if (matchScore >= 80) return "HIGH COMPATIBILITY";
    if (matchScore >= 60) return "GOOD COMPATIBILITY";
    if (matchScore >= 40) return "MODERATE COMPATIBILITY";
    return "LOW COMPATIBILITY";
  }, [matchScore]);

  return (
    <div className="min-h-screen w-full bg-gradient-to-b from-[#F0F7FF] to-white font-sans pb-24 md:pb-10">
      {/* Top Bar */}
      <div className="w-full flex items-center justify-between p-6">
        <button onClick={() => router.back()} className="w-10 h-10 flex items-center justify-center bg-white rounded-full shadow-sm hover:bg-slate-50 transition-colors">
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
        </button>
        <div className="flex items-center">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke={primaryBlue} strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <polyline points="16 18 22 12 16 6"></polyline>
            <polyline points="8 6 2 12 8 18"></polyline>
          </svg>
          <span className="ml-2 text-sm font-black text-[#1A1C1E] tracking-tight">SkillSync AI</span>
        </div>
      </div>

      <div className="px-6 mb-8">
        <h1 className="text-3xl font-black text-[#1A1C1E]">Analysis Results</h1>
      </div>

      <div className="max-w-3xl mx-auto px-6 space-y-10">

        {/* SCORE SECTION */}
        <div className="flex flex-col items-center">
          <div className="relative w-48 h-48 flex items-center justify-center">
            <svg className="w-full h-full transform -rotate-90" viewBox="0 0 100 100">
              <circle cx="50" cy="50" r="42" stroke="#E1EBF7" strokeWidth="10" fill="none" />
              <circle
                cx="50" cy="50" r="42"
                stroke={primaryBlue}
                strokeWidth="10"
                fill="none"
                strokeDasharray="263.8"
                strokeDashoffset={263.8 - (263.8 * matchScore) / 100}
                strokeLinecap="round"
                className="transition-all duration-1000 ease-out"
              />
            </svg>
            <div className="absolute flex flex-col items-center">
              <span className="text-5xl font-black text-[#1A1C1E]">{matchScore}%</span>
              <span className="text-[10px] font-bold text-[#1E88E5] tracking-widest mt-1">MATCH SCORE</span>
            </div>
          </div>

          <div className="mt-8 bg-[#E3F2FD] px-6 py-2 rounded-full">
            <span className="text-xs font-bold text-[#1E88E5]">{compatibilityText}</span>
          </div>
        </div>

        {/* SKILLS SECTION */}
        <section>
          <h2 className="text-xl font-bold text-[#1A1C1E] mb-6">Skills Deep Dive</h2>

          <div className="mb-4">
             <span className="text-sm font-bold text-[#1E88E5] border-b-2 border-[#1E88E5] pb-1">Strengths</span>
          </div>

          <div className="space-y-3">
            {result?.pros?.split('.').filter((s: string) => s.trim()).map((strength: string, i: number) => (
              <div key={i} className="bg-white rounded-xl p-4 border border-slate-100 shadow-sm flex items-center">
                <div className="w-10 h-10 rounded-lg bg-blue-50 flex items-center justify-center mr-4 text-[#1E88E5]">
                   <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polyline points="16 18 22 12 16 6"/><polyline points="8 6 2 12 8 18"/></svg>
                </div>
                <p className="text-sm font-bold text-[#1A1C1E] flex-1">{strength.trim()}</p>
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#4CAF50" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round"><polyline points="20 6 9 17 4 12"/></svg>
              </div>
            ))}
          </div>
        </section>

        {/* SKILL GAPS */}
        <section>
           <h3 className="text-[11px] font-bold text-slate-400 tracking-widest uppercase mb-4">SKILL GAPS TO ADDRESS</h3>
           <div className="flex flex-wrap gap-3">
             {result?.cons?.split(',').map((gap: string, i: number) => (
               <div key={i} className="bg-slate-100 px-4 py-2 rounded-lg flex items-center">
                 <svg className="mr-2" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#94A3B8" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round"><line x1="12" y1="5" x2="12" y2="19"></line><line x1="5" y1="12" x2="19" y2="12"></line></svg>
                 <span className="text-xs font-bold text-slate-600">{gap.trim()}</span>
               </div>
             ))}
           </div>
        </section>

        {/* AI RECOMMENDATION */}
        <div className="bg-[#1E88E5] rounded-3xl p-8 text-white shadow-xl">
           <div className="flex items-center gap-2 mb-4">
             <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon></svg>
             <span className="font-bold">AI Recommendation</span>
           </div>
           <p className="text-sm leading-relaxed opacity-90 mb-8">
             You're a strong candidate for this role! To secure the position, focus on demonstrating your expertise in {result?.suggested_path || 'the field'}. We recommend following our curated learning paths to round out your profile.
           </p>
           <button onClick={() => router.push('/dashboard/roadmap')} className="w-full h-14 bg-white text-[#1E88E5] rounded-xl font-bold hover:bg-slate-50 transition-colors shadow-lg">
             View Learning Path
           </button>
        </div>

      </div>
    </div>
  );
}
