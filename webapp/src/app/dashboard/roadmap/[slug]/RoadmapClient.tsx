"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import roadmapsData from "@/data/roadmaps.json";

export default function RoadmapClient({ slug }: { slug: string }) {
  const router = useRouter();
  const [roadmap, setRoadmap] = useState<any>(null);

  useEffect(() => {
    const data = roadmapsData.find(r => r.slug === slug);
    if (data) {
      setRoadmap(data);
    }
  }, [slug]);

  if (!roadmap) {
    return (
      <div className="flex items-center justify-center min-h-[50vh] text-slate-400">
        <div className="animate-pulse flex flex-col items-center">
          <svg className="w-12 h-12 mb-4 text-blue-500/50" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
            <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
            <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
          </svg>
          <p className="font-bold tracking-tight">Loading Roadmap...</p>
        </div>
      </div>
    );
  }

  const isCpp = slug === "cpp";
  const primaryColor = isCpp ? "#004482" : "#1A237E";
  const bgColor = isCpp ? "bg-[#F8F9FF]" : "bg-[#F1F4FF]";

  return (
    <div className={`min-h-screen w-full ${bgColor} font-sans pb-24 md:pb-10 overflow-x-hidden`}>
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
        <h1 className="text-lg font-bold text-[#1A1C1E]">{roadmap.title}</h1>
      </div>

      <div className="max-w-3xl mx-auto px-6 py-10">
        {/* Title and Intro */}
        <div className="mb-10">
          <h1 className="text-4xl md:text-5xl font-black mb-4 leading-tight" style={{ color: primaryColor }}>
            {roadmap.subtitle || roadmap.title}
          </h1>
          {roadmap.description && (
            <p className="text-[#64748B] text-base md:text-lg leading-relaxed max-w-2xl">
              {roadmap.description}
            </p>
          )}
        </div>

        {/* Roadmap Sections */}
        <div className="space-y-6">
          {roadmap.sections && roadmap.sections.length > 0 ? (
            roadmap.sections.map((section: any, idx: number) => (
              <RoadmapSectionCard key={idx} slug={slug} section={section} primaryColor={primaryColor} />
            ))
          ) : (
            <div className="bg-white rounded-2xl p-10 flex flex-col items-center justify-center border border-slate-100 shadow-sm">
               <svg width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="#CBD5E1" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                  <path d="M12 2v20M2 12h20M4.93 4.93l14.14 14.14M4.93 19.07L19.07 4.93"></path>
               </svg>
               <p className="mt-4 text-slate-400 font-bold tracking-tight">Curriculum is being updated...</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

function RoadmapSectionCard({ slug, section, primaryColor }: { slug: string, section: any, primaryColor: string }) {
  const phaseMatch = section.title.match(/^(\d+)\.\s*(.*)/);
  const phaseNumber = phaseMatch ? phaseMatch[1] : null;
  const displayTitle = phaseMatch ? phaseMatch[2] : section.title;

  return (
    <div className="bg-white rounded-[24px] overflow-hidden shadow-sm border border-slate-100 p-6 md:p-8">
      <div className="flex items-center mb-6">
        <div className="w-10 h-10 rounded-xl bg-slate-50 flex items-center justify-center mr-4 shrink-0" style={{ color: primaryColor }}>
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <circle cx="12" cy="12" r="10" />
            <polyline points="12 6 12 12 16 14" />
          </svg>
        </div>
        <div>
          {phaseNumber && (
            <p className="text-[10px] font-bold text-slate-400 tracking-widest uppercase mb-0.5">PHASE {phaseNumber}</p>
          )}
          <h3 className="text-lg font-bold uppercase tracking-wider leading-tight" style={{ color: primaryColor }}>
            {displayTitle}
          </h3>
        </div>
      </div>
      
      <div className="space-y-6">
        {section.subsections ? (
          section.subsections.map((sub: any, subIdx: number) => (
              <div key={subIdx}>
                <h4 className="text-sm font-bold text-slate-800 mb-3 px-1">{sub.title}</h4>
                <div className="space-y-1">
                  {sub.items.map((item: any, itemIdx: number) => {
                    const itemTitle = typeof item === 'object' ? item.title : item;
                    return (
                      <RoadmapItem
                        key={itemIdx}
                        item={item}
                        storageKey={`roadmap_${slug}_${section.title}_${sub.title}_${itemTitle}`}
                        primaryColor={primaryColor}
                      />
                    );
                  })}
                </div>
              </div>
          ))
        ) : section.items ? (
          <div className="space-y-1">
            {section.items.map((item: any, itemIdx: number) => {
              const itemTitle = typeof item === 'object' ? item.title : item;
              return (
                <RoadmapItem
                  key={itemIdx}
                  item={item}
                  storageKey={`roadmap_${slug}_${section.title}_${itemTitle}`}
                  primaryColor={primaryColor}
                />
              );
            })}
          </div>
        ) : (
          <p className="text-slate-400 text-sm p-4 italic">Specific topics coming soon.</p>
        )}
      </div>
    </div>
  );
}

function RoadmapItem({ item, storageKey, primaryColor }: { item: any, storageKey: string, primaryColor: string }) {
  const [checked, setChecked] = useState(false);
  const [mounted, setMounted] = useState(false);

  const isDetailed = typeof item === 'object' && item !== null;
  const displayTitle = isDetailed ? item.title : item;
  const displayDesc = isDetailed ? item.description : null;

  useEffect(() => {
    const saved = localStorage.getItem(storageKey);
    if (saved === 'true') {
      setChecked(true);
    }
    setMounted(true);
  }, [storageKey]);

  const handleCheck = () => {
    const newVal = !checked;
    setChecked(newVal);
    localStorage.setItem(storageKey, String(newVal));
  };

  if (!mounted) return <div className="h-10 w-full bg-slate-50 animate-pulse rounded-lg" />;

  return (
    <div
      className={`flex items-start p-3 rounded-xl hover:bg-slate-50 transition-colors group cursor-pointer ${isDetailed ? 'mb-2 border border-slate-50 bg-slate-50/30' : ''}`}
      onClick={handleCheck}
    >
      <div className={`mt-0.5 w-5 h-5 rounded-md border-2 flex items-center justify-center transition-all shrink-0 ${checked ? 'border-blue-600 bg-blue-600' : 'border-slate-200'}`} style={{ backgroundColor: checked ? primaryColor : '', borderColor: checked ? primaryColor : '' }}>
        {checked && (
          <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="white" strokeWidth="4" strokeLinecap="round" strokeLinejoin="round">
            <polyline points="20 6 9 17 4 12"></polyline>
          </svg>
        )}
      </div>
      <div className="ml-3 flex flex-col">
        <span className={`text-sm font-bold transition-all ${checked ? 'text-slate-400 line-through' : 'text-[#334155]'}`}>
          {displayTitle}
        </span>
        {displayDesc && (
          <span className={`text-[11px] mt-0.5 leading-relaxed transition-all ${checked ? 'text-slate-300' : 'text-slate-500'}`}>
            {displayDesc}
          </span>
        )}
      </div>
    </div>
  );
}
