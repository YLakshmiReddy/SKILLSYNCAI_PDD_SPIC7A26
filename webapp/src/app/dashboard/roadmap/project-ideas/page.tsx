"use client";

import React from "react";
import { useRouter } from "next/navigation";

export default function ProjectIdeasScreen() {
  const router = useRouter();
  const primaryBlue = "#1D4ED8";

  return (
    <div className="min-h-screen w-full bg-white font-sans pb-24 md:pb-10 overflow-x-hidden">
      {/* Header */}
      <div className="w-full h-16 bg-white border-b border-slate-200 flex items-center px-6 sticky top-0 z-20">
        <button onClick={() => router.back()} className="p-2 -ml-2 hover:bg-slate-100 rounded-full transition-colors mr-2">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#0F172A" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
        </button>
        <div className="flex items-center ml-auto">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke={primaryBlue} strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon>
          </svg>
          <span className="ml-2 text-sm font-bold text-slate-500 text-nowrap">SkillSync AI</span>
        </div>
      </div>

      <div className="max-w-3xl mx-auto px-6 py-10">
        <div className="mb-12">
          <h1 className="text-4xl md:text-5xl font-black text-[#0F172A] mb-4">Project Ideas</h1>
          <p className="text-[#64748B] text-base md:text-lg leading-relaxed max-w-2xl">
            Apply your knowledge with AI-suggested projects that challenge your current skill level and build your portfolio.
          </p>
        </div>

        {/* Easy Projects */}
        <section className="mb-16">
          <CategoryHeader title="EASY PROJECTS" count="10 PROJECTS" color={primaryBlue} />
          <div className="h-px bg-slate-100 w-full mb-6" />
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-x-8">
            <SimpleProjectItem name="Portfolio Website" tag="Personal Portfolio" color={primaryBlue} />
            <SimpleProjectItem name="Todo List App" tag="Task Management" color={primaryBlue} />
            <SimpleProjectItem name="Weather Dashboard" tag="API Integration" color={primaryBlue} />
            <SimpleProjectItem name="Unit Converter" tag="Basic Logic" color={primaryBlue} />
            <SimpleProjectItem name="Simple Blog" tag="Content Management" color={primaryBlue} />
            <SimpleProjectItem name="Recipe Book" tag="Data Display" color={primaryBlue} />
            <SimpleProjectItem name="Calculator App" tag="Arithmetic Logic" color={primaryBlue} />
            <SimpleProjectItem name="Notes App" tag="Data Storage" color={primaryBlue} />
            <SimpleProjectItem name="Tribute Page" tag="Static HTML/CSS" color={primaryBlue} />
            <SimpleProjectItem name="Random Quote Machine" tag="API Fetching" color={primaryBlue} />
          </div>
        </section>

        {/* Medium Projects */}
        <section className="mb-16">
          <CategoryHeader title="MEDIUM PROJECTS" count="12 PROJECTS" color={primaryBlue} />
          <div className="h-px bg-slate-100 w-full mb-6" />
          <div className="grid grid-cols-1 md:grid-cols-2 gap-x-8 gap-y-4">
            <MediumProjectItem title="AI Content Generator" skills={["OpenAI API", "Prompt Eng.", "Next.js"]} color={primaryBlue} />
            <MediumProjectItem title="Crypto Price Tracker" skills={["CoinGecko API", "Charts", "Real-time"]} color={primaryBlue} />
            <MediumProjectItem title="E-commerce Store Front" skills={["React", "Stripe SDK", "Auth"]} color={primaryBlue} />
            <MediumProjectItem title="Movie Database App" skills={["TMDB API", "Filtering", "Pagination"]} color={primaryBlue} />
            <MediumProjectItem title="Expense Tracker" skills={["LocalStorage", "Charts", "Redux"]} color={primaryBlue} />
            <MediumProjectItem title="Job Board UI" skills={["Tailwind CSS", "Search", "Forms"]} color={primaryBlue} />
            <MediumProjectItem title="Fitness Tracker" skills={["Data Persistence", "Goals", "Icons"]} color={primaryBlue} />
            <MediumProjectItem title="Quiz App" skills={["Logic", "Timer", "Results UI"]} color={primaryBlue} />
            <MediumProjectItem title="Markdown Previewer" skills={["Text Parsing", "UI Sync", "React"]} color={primaryBlue} />
            <MediumProjectItem title="Pomodoro Timer" skills={["Timing Logic", "Notifications", "Settings"]} color={primaryBlue} />
            <MediumProjectItem title="Chat Application UI" skills={["Layouts", "Animations", "State"]} color={primaryBlue} />
            <MediumProjectItem title="URL Shortener" skills={["API", "Forms", "Clipboard"]} color={primaryBlue} />
          </div>
        </section>

        {/* Difficult Projects */}
        <section className="mb-12">
          <CategoryHeader title="DIFFICULT PROJECTS" count="8 PROJECTS" color={primaryBlue} />
          <div className="h-px bg-slate-100 w-full mb-8" />
          <div className="grid grid-cols-1 gap-6">
            <DifficultProjectCard 
              title="AI Engineer / Autonomous Agents"
              description="Build a multi-agent system that can solve complex tasks using LLMs and tool-calling capabilities."
              tasks={["Agentic Workflow design", "LLM tool integration", "Auto-debugging layer"]}
              color={primaryBlue}
            />
            <DifficultProjectCard 
              title="SaaS Platform Dashboard"
              description="A complete multi-tenant dashboard with subscription management, analytics, and team collaboration."
              tasks={["Multi-tenancy setup", "Billing integration", "Analytics pipeline"]}
              color={primaryBlue}
            />
            <DifficultProjectCard 
              title="Blockchain Wallet"
              description="Secure cryptocurrency wallet with transaction signing, private key management, and explorer integration."
              tasks={["Key encryption", "Transaction logic", "Network sync"]}
              color={primaryBlue}
            />
            <DifficultProjectCard
              title="Real-time Analytics Engine"
              description="Process millions of events per second and visualize insights in real-time with sub-second latency."
              tasks={["Data ingestion", "Stream processing", "Real-time dashboard"]}
              color={primaryBlue}
            />
            <DifficultProjectCard
              title="Recommendation Engine"
              description="Build a collaborative filtering engine that suggests items based on user behavior and similarity."
              tasks={["Data collection", "Algorithm implementation", "API delivery"]}
              color={primaryBlue}
            />
            <DifficultProjectCard
              title="IoT Management Suite"
              description="Manage thousands of connected devices, monitor telemetry, and trigger automated responses."
              tasks={["Device protocols", "Edge computing", "Alert system"]}
              color={primaryBlue}
            />
            <DifficultProjectCard
              title="Search Engine Indexer"
              description="Build a distributed crawler and indexer that can process and rank web pages for search queries."
              tasks={["Web crawling", "Indexing logic", "Ranking algorithm"]}
              color={primaryBlue}
            />
            <DifficultProjectCard
              title="Voice Controlled Assistant"
              description="Develop a system that interprets voice commands to control smart home devices or execute scripts."
              tasks={["Speech recognition", "NLP intent parsing", "Hardware/API integration"]}
              color={primaryBlue}
            />
          </div>
        </section>
      </div>
    </div>
  );
}

function CategoryHeader({ title, count, color }: { title: string, count: string, color: string }) {
  return (
    <div className="flex justify-between items-end pb-2">
      <h2 className="text-xl font-black text-[#0F172A] uppercase tracking-tight">{title}</h2>
      <span className="text-[12px] font-bold" style={{ color: color }}>{count}</span>
    </div>
  );
}

function SimpleProjectItem({ name, tag, color }: { name: string, tag: string, color: string }) {
  return (
    <div className="flex items-center py-3 group cursor-pointer">
      <div className="w-8 h-8 rounded-lg bg-slate-100 flex items-center justify-center shrink-0 group-hover:bg-blue-50 transition-colors">
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke={color} strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
          <polyline points="4 17 10 11 4 5"></polyline>
          <line x1="12" y1="19" x2="20" y2="19"></line>
        </svg>
      </div>
      <div className="ml-4">
        <h4 className="text-base font-bold text-[#0F172A] group-hover:text-blue-600 transition-colors">{name}</h4>
        <p className="text-[12px] text-slate-400 font-medium">{tag}</p>
      </div>
    </div>
  );
}

function MediumProjectItem({ title, skills, color }: { title: string, skills: string[], color: string }) {
  return (
    <div className="bg-slate-50/50 p-4 rounded-xl border border-slate-100 group hover:border-blue-100 hover:bg-white transition-all">
      <h4 className="text-base font-bold mb-3" style={{ color: color }}>{title}</h4>
      <div className="flex flex-wrap gap-2">
        {skills.map((skill, idx) => (
          <div key={idx} className="px-3 py-1 rounded-lg border border-slate-200 text-[10px] font-bold text-slate-600 bg-white shadow-sm">
            {skill}
          </div>
        ))}
      </div>
    </div>
  );
}

function DifficultProjectCard({ title, description, tasks, color }: { title: string, description: string, tasks: string[], color: string }) {
  return (
    <div className="bg-white rounded-2xl p-6 border border-slate-200 shadow-sm hover:shadow-md transition-all">
      <div className="w-9 h-9 rounded-full bg-blue-50 flex items-center justify-center mb-5">
        <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke={color} strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
          <path d="M4.5 16.5c-1.5 1.26-2 5-2 5s3.74-.5 5-2c.71-.84.7-2.13-.09-2.91a2.18 2.18 0 0 0-2.91-.09z"></path>
          <path d="m12 15-3-3a22 22 0 0 1 2-3.95A12.88 22 0 0 1 22 2c0 2.72-.78 7.5-6 11a22.35 22.35 0 0 1-4 2z"></path>
          <path d="M9 12H4s.5-1 1-4c2 0 3 .5 3 .5"></path>
          <path d="M15 3s1 .5 4 1c0 2-.5 3-.5 3"></path>
          <line x1="11.5" y1="15.5" x2="15.5" y2="11.5"></line>
        </svg>
      </div>
      <h3 className="text-xl font-black text-[#0F172A] mb-3 leading-tight">{title}</h3>
      <p className="text-sm text-slate-500 leading-relaxed mb-6">{description}</p>

      <div className="space-y-2">
        {tasks.map((task, idx) => (
          <div key={idx} className="bg-slate-50 border border-slate-100 rounded-lg p-3 flex items-center">
            <span className="text-[13px] font-bold text-slate-700">{task}</span>
          </div>
        ))}
      </div>
    </div>
  );
}
