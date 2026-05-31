"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";

export default function ResumeManagementScreen() {
  const router = useRouter();
  
  const templates = [
    { id: 1, name: "Tech Innovator", role: "Software Engineer", color: "#E3F2FD" },
    { id: 2, name: "Design Flow", role: "UI/UX Designer", color: "#F3E5F5" },
    { id: 3, name: "Data Guru", role: "Data Scientist", color: "#E8F5E9" },
    { id: 4, name: "Product Roadmap", role: "Product Manager", color: "#FFF3E0" },
    { id: 5, name: "Scale Master", role: "DevOps Engineer", color: "#E0F2F1" },
    { id: 6, name: "Business Core", role: "Business Analyst", color: "#EFEBE9" },
    { id: 7, name: "Pixel Perfect", role: "Frontend Developer", color: "#F1F8E9" },
    { id: 8, name: "Logic Builder", role: "Backend Developer", color: "#FFFDE7" },
    { id: 9, name: "Intelligence", role: "ML Engineer", color: "#E1F5FE" },
    { id: 10, name: "Safe Guard", role: "Security Specialist", color: "#FFEBEE" },
    { id: 11, name: "Mobile Native", role: "Android Developer", color: "#F0F4C3" },
    { id: 12, name: "Swift Elegant", role: "iOS Developer", color: "#E1BEE7" },
    { id: 13, name: "Cloud Scale", role: "Cloud Architect", color: "#B2EBF2" },
    { id: 14, name: "Quality First", role: "QA Engineer", color: "#DCEDC8" },
    { id: 15, name: "Stack Master", role: "Full Stack Developer", color: "#FFCCBC" },
    { id: 16, name: "Data Vault", role: "Database Admin", color: "#D1C4E9" },
    { id: 17, name: "Project Lead", role: "Project Manager", color: "#B3E5FC" },
    { id: 18, name: "Shell Script", role: "System Administrator", color: "#C8E6C9" },
    { id: 19, name: "Level Up", role: "Game Developer", color: "#F8BBD0" },
    { id: 20, name: "Connected", role: "Network Engineer", color: "#FFFFE0" },
    { id: 21, name: "Shield Pro", role: "Cyber Security Analyst", color: "#ECEFF1" },
    { id: 22, name: "Draft Master", role: "Technical Writer", color: "#F5F5F5" },
    { id: 23, name: "People First", role: "HR Manager", color: "#FCE4EC" },
    { id: 24, name: "Growth Hack", role: "Marketing Specialist", color: "#E0F7FA" }
  ];

  const [selectedTemplate, setSelectedTemplate] = useState<any>(null);

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
        <h1 className="text-lg font-bold text-[#1A1C1E]">Resume Management</h1>
      </div>

      <div className="max-w-4xl mx-auto px-6 py-8">
        <h2 className="text-[11px] font-bold text-[#616161] tracking-[0.15em] uppercase mb-6">SELECT A TEMPLATE TO PREVIEW</h2>

        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {templates.map((template) => (
            <div
              key={template.id}
              onClick={() => setSelectedTemplate(template)}
              className="bg-white rounded-2xl border border-slate-100 shadow-sm overflow-hidden flex flex-col h-[280px] cursor-pointer hover:shadow-md transition-all group"
            >
              <div
                className="flex-1 flex items-center justify-center"
                style={{ backgroundColor: template.color }}
              >
                <svg width="60" height="60" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" className="text-slate-400 group-hover:scale-110 transition-transform">
                  <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                  <polyline points="14 2 14 8 20 8"></polyline>
                </svg>
              </div>
              <div className="p-4 bg-white border-t border-slate-50">
                <h3 className="text-sm font-bold text-[#1A1C1E] truncate">{template.name}</h3>
                <p className="text-[11px] font-medium text-slate-400 mb-4">{template.role}</p>
                <button className="w-full h-10 bg-[#673AB7] text-white rounded-lg text-[11px] font-bold flex items-center justify-center hover:bg-[#5E35B1] transition-colors">
                  <svg className="mr-2" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
                    <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
                    <polyline points="7 10 12 15 17 10"></polyline>
                    <line x1="12" y1="15" x2="12" y2="3"></line>
                  </svg>
                  Download
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* Preview Modal */}
      {selectedTemplate && (
        <div className="fixed inset-0 z-50 bg-black/40 backdrop-blur-sm flex items-center justify-center p-4">
          <div className="w-full max-w-2xl bg-white rounded-3xl overflow-hidden shadow-2xl flex flex-col max-h-[90vh] animate-in zoom-in-95 duration-200">
            <div className="p-6 border-b border-slate-100 flex items-center justify-between">
               <div>
                 <h2 className="text-xl font-bold text-[#1A1C1E]">{selectedTemplate.name}</h2>
                 <p className="text-xs text-slate-400">Full Preview (Scroll to view)</p>
               </div>
               <button onClick={() => setSelectedTemplate(null)} className="p-2 hover:bg-slate-50 rounded-full transition-colors">
                 <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#0F172A" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
               </button>
            </div>

            <div className="flex-1 overflow-y-auto bg-slate-50 p-6">
               <div className="w-full bg-white shadow-sm rounded-lg overflow-hidden border border-slate-200">
                  <div className="h-32 flex flex-col items-center justify-center" style={{ backgroundColor: selectedTemplate.color }}>
                     <h3 className="text-2xl font-black uppercase text-black">{selectedTemplate.name}</h3>
                     <p className="text-sm font-bold text-slate-700 uppercase">{selectedTemplate.role}</p>
                  </div>
                  <div className="p-8 space-y-8">
                     <section>
                       <h4 className="text-[13px] font-bold text-[#673AB7] tracking-widest border-b border-slate-100 pb-2 mb-4">PROFESSIONAL SUMMARY</h4>
                       <p className="text-sm text-slate-500 leading-relaxed">A clean and professional template designed specifically for {selectedTemplate.role} roles in the modern job market.</p>
                     </section>
                     <section>
                       <h4 className="text-[13px] font-bold text-[#673AB7] tracking-widest border-b border-slate-100 pb-2 mb-4">WORK EXPERIENCE</h4>
                       <div className="space-y-4">
                          <div>
                            <div className="flex justify-between font-bold text-sm text-slate-800">
                               <span>Senior {selectedTemplate.role}</span>
                               <span className="text-xs text-slate-400">2021 - Present</span>
                            </div>
                            <p className="text-xs text-slate-500">Global Tech Solutions</p>
                          </div>
                          <div>
                            <div className="flex justify-between font-bold text-sm text-slate-800">
                               <span>Junior {selectedTemplate.role}</span>
                               <span className="text-xs text-slate-400">2018 - 2020</span>
                            </div>
                            <p className="text-xs text-slate-500">Startup Inc.</p>
                          </div>
                       </div>
                     </section>
                  </div>
               </div>
            </div>

            <div className="p-6 border-t border-slate-100 flex gap-4">
               <button onClick={() => setSelectedTemplate(null)} className="flex-1 h-12 border border-slate-200 rounded-xl font-bold text-slate-600 hover:bg-slate-50 transition-colors">Close</button>
               <button className="flex-1 h-12 bg-[#673AB7] text-white rounded-xl font-bold flex items-center justify-center hover:bg-[#5E35B1] transition-colors">
                 <svg className="mr-2" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                    <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
                    <polyline points="7 10 12 15 17 10"></polyline>
                    <line x1="12" y1="15" x2="12" y2="3"></line>
                 </svg>
                 Download
               </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
