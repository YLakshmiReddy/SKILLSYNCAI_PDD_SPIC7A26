"use client";

import React, { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

// --- Types ---
interface AnalysisResult {
  filename: string;
  suggested_path: string;
  match_details: Record<string, number>;
  pros: string;
  cons: string;
  db_status?: string;
}

// --- Helpers ---
function getScoreFromDetails(details: Record<string, number>, topPath: string): number {
  const totalKeywordsInPath = 30; // Approximate normalization base
  const matched = details[topPath] ?? 0;
  return Math.min(Math.round((matched / totalKeywordsInPath) * 100), 99);
}

function getAllDomainScores(details: Record<string, number>) {
  return Object.entries(details)
    .sort(([, a], [, b]) => b - a)
    .slice(0, 5)
    .map(([domain, score]) => ({
      domain,
      score: Math.round(score),
      count: Math.round(score),
    }));
}

const DOMAIN_ICONS: Record<string, React.ReactNode> = {
  default: (
    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polygon points="13 2 3 14 12 14 11 22 21 10 12 10 13 2"></polygon></svg>
  ),
};

function getDomainIcon(domain: string): React.ReactNode {
  return DOMAIN_ICONS[domain] || DOMAIN_ICONS.default;
}

// --- Loading Skeleton ---
function LoadingSkeleton() {
  return (
    <div className="min-h-screen w-full bg-[#F7F9FC] font-sans pb-24 md:pb-10 animate-pulse">
      <div className="w-full h-16 bg-white border-b border-slate-200 flex items-center px-6 sticky top-0 z-20">
        <div className="h-5 w-32 bg-slate-200 rounded-lg" />
      </div>
      <div className="max-w-3xl mx-auto px-6 py-8 space-y-6">
        <div className="h-8 w-48 bg-slate-200 rounded-lg mx-auto" />
        <div className="grid grid-cols-2 gap-4">
          <div className="h-60 bg-white rounded-2xl shadow-sm" />
          <div className="h-60 bg-blue-100 rounded-2xl shadow-sm" />
        </div>
        <div className="h-40 bg-blue-200 rounded-3xl" />
        <div className="space-y-4">
          <div className="h-16 bg-white rounded-xl shadow-sm" />
          <div className="h-16 bg-white rounded-xl shadow-sm" />
          <div className="h-16 bg-white rounded-xl shadow-sm" />
        </div>
      </div>
    </div>
  );
}

// --- No Data State ---
function NoDataState({ router }: { router: ReturnType<typeof useRouter> }) {
  return (
    <div className="min-h-screen w-full bg-[#F7F9FC] font-sans flex flex-col items-center justify-center px-6 text-center">
      <div className="w-20 h-20 rounded-full bg-blue-100 flex items-center justify-center mb-6">
        <svg width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="#1E88E5" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M14.5 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V7.5L14.5 2z"/><polyline points="14 2 14 8 20 8"/></svg>
      </div>
      <h2 className="text-2xl font-bold text-[#1A1C1E] mb-3">No Analysis Found</h2>
      <p className="text-[#757575] mb-8 max-w-sm">Upload your resume first to see your personalized AI-powered skill analysis here.</p>
      <button
        onClick={() => router.push("/dashboard/upload-resume")}
        className="bg-[#1E88E5] text-white font-bold px-8 py-4 rounded-2xl shadow-lg shadow-blue-500/25 hover:bg-[#1976D2] transition-colors"
      >
        Upload Resume
      </button>
    </div>
  );
}

// --- Main Component ---
export default function AnalysisScreen() {
  const router = useRouter();
  const [result, setResult] = useState<AnalysisResult | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    try {
      const stored = localStorage.getItem("analysisResult");
      if (stored) {
        setResult(JSON.parse(stored));
      }
    } catch {
      // If parse fails, treat as no data
    } finally {
      setIsLoading(false);
    }
  }, []);

  if (isLoading) return <LoadingSkeleton />;
  if (!result) return <NoDataState router={router} />;

  const brandBlue = "#1E88E5";
  const brandGreen = "#4CAF50";

  const currentScore = parseInt(localStorage.getItem("matchScore") || "0");
  const previousScore = parseInt(localStorage.getItem("prevMatchScore") || "0");
  const improvement = currentScore - previousScore;
  const improvementText = improvement >= 0 ? `+${improvement}% Increase` : `${improvement}% Decrease`;

  const domainScores = getAllDomainScores(result.match_details);
  const topSkill = result.suggested_path;
  const totalDomains = Object.keys(result.match_details).length;

  return (
    <div className="min-h-screen w-full bg-[#F7F9FC] font-sans pb-24 md:pb-10">
      {/* Header */}
      <div className="w-full h-16 bg-white border-b border-slate-200 flex items-center px-6 sticky top-0 z-20 gap-4">
        <button onClick={() => router.back()} className="p-2 rounded-full hover:bg-slate-100 transition-colors">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="m15 18-6-6 6-6"/></svg>
        </button>
        <h1 className="text-lg font-bold text-[#0F172A]">AI Analysis</h1>
        <div className="ml-auto">
          <span className="text-xs text-slate-400 truncate max-w-[140px] block">{result.filename}</span>
        </div>
      </div>

      <div className="max-w-3xl mx-auto px-6 py-8">
        <div className="text-center mb-8">
          <h2 className="text-2xl font-bold text-[#1A1C1E]">Compare Score</h2>
          <p className="text-sm text-[#757575] mt-1">Track your progress over time</p>
        </div>

        {/* TOP SCORE CARDS */}
        <div className="grid grid-cols-2 gap-4 md:gap-8 mb-8">
          <ScoreOverviewCard
            label="Previous Score"
            score={previousScore}
            footer="Baseline Assessment"
            isCurrent={false}
            brandBlue={brandBlue}
          />
          <ScoreOverviewCard
            label="Current Score"
            score={currentScore}
            footer={improvement >= 0 ? "IMPROVED" : "DECLINED"}
            isCurrent={true}
            brandBlue={brandBlue}
            brandGreen={brandGreen}
          />
        </div>

        {/* SUGGESTED PATH BANNER */}
        <div className="w-full bg-[#1E88E5] rounded-3xl p-8 text-white shadow-xl mb-10 relative overflow-hidden group cursor-pointer" onClick={() => router.push("/dashboard/analysis/results")}>
          <div className="flex justify-between items-center mb-6">
            <div>
              <p className="text-white/80 text-sm font-medium mb-1">Overall Improvement</p>
              <h3 className="text-2xl md:text-4xl font-bold leading-tight">{improvementText}</h3>
            </div>
            <div className="w-12 h-12 rounded-full bg-white/20 flex items-center justify-center shrink-0">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <polyline points="23 6 13.5 15.5 8.5 10.5 1 18"></polyline>
                <polyline points="17 6 23 6 23 12"></polyline>
              </svg>
            </div>
          </div>
          
          <div className="h-px w-full bg-white/20 mb-6" />
          
          <div className="flex gap-6">
            <div className="flex-1">
              <p className="text-[10px] font-bold text-white/70 tracking-widest mb-1 uppercase">Top Domain</p>
              <p className="text-lg font-bold">{topSkill}</p>
            </div>
            <div className="flex-1">
              <p className="text-[10px] font-bold text-white/70 tracking-widest mb-1 uppercase">Velocity</p>
              <p className="text-lg font-bold">Above Average</p>
            </div>
          </div>
        </div>

        {/* AREAS TO IMPROVE */}
        <div className="w-full bg-amber-50 border border-amber-200 rounded-3xl p-6 mb-10">
          <div className="flex items-center gap-3 mb-3">
            <div className="w-8 h-8 rounded-full bg-amber-100 flex items-center justify-center">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="#F59E0B" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>
            </div>
            <p className="font-bold text-amber-800 text-sm tracking-wide uppercase">Area to Improve</p>
          </div>
          <p className="text-sm text-amber-700 leading-relaxed">{result.cons}</p>
        </div>

        {/* BREAKDOWN SECTION */}
        <div className="mb-6">
          <h3 className="text-sm font-bold text-[#757575] tracking-widest uppercase">Breakdown by Domain</h3>
        </div>

        <div className="space-y-4">
          {domainScores.map(({ domain, score, count }) => (
            <CategoryRow
              key={domain}
              title={domain}
              score={score}
              count={count}
              iconBg="#E3F2FD"
              brandBlue={brandBlue}
              brandGreen={brandGreen}
              icon={getDomainIcon(domain)}
              isTop={domain === topSkill}
            />
          ))}
        </div>

        {/* Re-analyze CTA */}
        <div className="mt-10">
          <button
            onClick={() => router.push("/dashboard/upload-resume")}
            className="w-full py-4 rounded-2xl border-2 border-[#1E88E5] text-[#1E88E5] font-bold text-base hover:bg-[#1E88E5] hover:text-white transition-all duration-200"
          >
            Analyze Another Resume
          </button>
        </div>
      </div>
    </div>
  );
}

// --- Sub-Components ---

function ScoreOverviewCard({ label, score, footer, isCurrent, brandBlue, brandGreen, isCount }: {
  label: string;
  score: number;
  footer: string;
  isCurrent: boolean;
  brandBlue: string;
  brandGreen?: string;
  isCount?: boolean;
}) {
  return (
    <div className="bg-white rounded-2xl p-6 flex flex-col items-center justify-between h-[240px] shadow-sm border border-slate-100">
      <span className={`text-sm font-semibold text-center ${isCurrent ? "text-[#1E88E5]" : "text-[#757575]"}`}>{label}</span>
      
      {isCount ? (
        <div className="flex flex-col items-center">
          <span className="text-5xl font-black text-[#1A1C1E]">{score}</span>
          <span className="text-sm text-slate-400 mt-1">detected</span>
        </div>
      ) : (
        <div className="relative w-28 h-28 flex items-center justify-center">
          <svg className="w-full h-full transform -rotate-90" viewBox="0 0 100 100">
            <circle cx="50" cy="50" r="42" stroke="#ECEFF1" strokeWidth="10" fill="none" />
            <circle 
              cx="50" cy="50" r="42"
              stroke={isCurrent ? brandBlue : "#CFD8DC"}
              strokeWidth="10"
              fill="none" 
              strokeDasharray="263.8"
              strokeDashoffset={263.8 - (263.8 * score) / 100}
              strokeLinecap="round" 
            />
          </svg>
          <span className="absolute text-2xl font-black text-[#1A1C1E]">{score}%</span>
        </div>
      )}
      
      {isCurrent ? (
        <div className="bg-[#E8F5E9] px-3 py-1 rounded-lg">
          <span className="text-[12px] font-bold text-[#4CAF50]">{footer}</span>
        </div>
      ) : (
        <span className="text-[12px] font-medium text-slate-400">{footer}</span>
      )}
    </div>
  );
}

function CategoryRow({ title, score, count, iconBg, icon, brandBlue, isTop }: {
  title: string;
  score: number;
  count: number;
  iconBg: string;
  icon: React.ReactNode;
  brandBlue: string;
  brandGreen: string;
  isTop: boolean;
}) {
  return (
    <div className={`bg-white rounded-xl p-4 flex items-center shadow-sm border transition-all ${isTop ? "border-blue-200 ring-1 ring-blue-100" : "border-slate-100"}`}>
      <div className="w-10 h-10 rounded-lg flex items-center justify-center shrink-0" style={{ backgroundColor: iconBg, color: brandBlue }}>
        {icon}
      </div>
      <div className="ml-4 flex-1 min-w-0">
        <div className="flex items-center gap-2 mb-1">
          <h4 className="font-semibold text-[#1A1C1E] truncate">{title}</h4>
          {isTop && <span className="text-[10px] font-bold bg-blue-100 text-blue-600 px-2 py-0.5 rounded-full whitespace-nowrap">TOP</span>}
        </div>
        <div className="w-full h-1.5 bg-slate-100 rounded-full overflow-hidden">
          <div
            className="h-full rounded-full transition-all duration-700"
            style={{ width: `${score}%`, backgroundColor: isTop ? brandBlue : "#94A3B8" }}
          />
        </div>
      </div>
      <div className="flex flex-col items-end ml-4 shrink-0">
        <span className="text-sm font-bold text-[#1A1C1E]">{score}%</span>
        <span className="text-xs text-slate-400">Match</span>
      </div>
    </div>
  );
}
