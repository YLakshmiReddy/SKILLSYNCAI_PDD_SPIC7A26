"use client";

import React from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";

export default function SkillBasedRoadmapsScreen() {
  const router = useRouter();

  const pathPrimary = "#1A237E";

  const skills = [
    { name: "AI Agents", slug: "ai-agents" },
    { name: "AI Red Teaming", slug: "ai-red-teaming" },
    { name: "API Design", slug: "api-design" },
    { name: "Angular", slug: "angular" },
    { name: "ASP.NET Core", slug: "asp-net-core" },
    { name: "AWS", slug: "aws" },
    { name: "Azure", slug: "azure" },
    { name: "C++", slug: "cpp" },
    { name: "Cloudflare", slug: "cloudflare" },
    { name: "Code Review", slug: "code-review" },
    { name: "Computer Science", slug: "computer-science" },
    { name: "CSS", slug: "css" },
    { name: "Data Structures and Algorithms", slug: "data-structures-algorithms" },
    { name: "Design System", slug: "design-system-architecture" },
    { name: "Docker", slug: "docker-developer" },
    { name: "Flutter", slug: "master-the-flutter-ecosystem" },
    { name: "GCP", slug: "cloud-architect" },
    { name: "Git", slug: "git-github" },
    { name: "Go", slug: "go-developer" },
    { name: "GraphQL", slug: "graphql-developer" },
    { name: "HTML", slug: "html-developer" },
    { name: "Java", slug: "development" },
    { name: "JavaScript", slug: "javascript" },
    { name: "Kotlin", slug: "master-modern-development" },
    { name: "Kubernetes", slug: "kubernetes" },
    { name: "Linux", slug: "linux-developer" },
    { name: "MongoDB", slug: "mongodb-developer" },
    { name: "Next.js", slug: "mastering-the-modern-web" },
    { name: "Node.js", slug: "mastering-the-server-side" },
    { name: "NoSQL", slug: "nosql-systems" },
    { name: "PHP", slug: "php" },
    { name: "Prompt Engineering", slug: "prompt-engineering" },
    { name: "Python", slug: "python-developer" },
    { name: "React", slug: "react" },
    { name: "React Native", slug: "react-native" },
    { name: "Redis", slug: "redis" },
    { name: "Rust", slug: "rust" },
    { name: "Software Design and Architecture", slug: "software-design-architecture" },
    { name: "Spring Boot", slug: "spring-boot" },
    { name: "SQL", slug: "master-the-language-of-data" },
    { name: "System Design", slug: "system-design" },
    { name: "Terraform", slug: "terraform" },
    { name: "TypeScript", slug: "typescript" },
    { name: "Vue.js", slug: "vue-js-developer" }
  ].sort((a, b) => a.name.localeCompare(b.name));

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
        <span className="text-[11px] font-bold text-[#94A3B8] tracking-[0.2em] uppercase">Skill Based Roadmaps</span>
      </div>

      <div className="max-w-4xl mx-auto px-6 py-12">
        <div className="mb-12">
          <h1 className="text-4xl md:text-5xl font-black text-[#1A237E] mb-4">All Skills</h1>
          <p className="text-[#475569] text-base md:text-lg leading-relaxed max-w-2xl">
            Explore deep-dive learning paths for individual technologies and master them at your own pace.
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {skills.map((skill, idx) => (
            <Link key={idx} href={`/dashboard/roadmap/${skill.slug}`} className="group">
              <div className="bg-white rounded-2xl p-4 flex items-center justify-between shadow-sm border border-slate-100 hover:shadow-md hover:border-indigo-100 transition-all">
                <div className="flex items-center">
                  <div className="w-10 h-10 rounded-xl bg-[#E8EAF6] flex items-center justify-center mr-4 text-[#1A237E] group-hover:bg-[#1A237E] group-hover:text-white transition-all">
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                      <polyline points="16 18 22 12 16 6"></polyline>
                      <polyline points="8 6 2 12 8 18"></polyline>
                    </svg>
                  </div>
                  <span className="text-base font-bold text-[#0F172A] group-hover:text-[#1A237E] transition-colors">{skill.name}</span>
                </div>
                <div className="text-slate-300 group-hover:text-[#1A237E] group-hover:translate-x-1 transition-all">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                    <polyline points="9 18 15 12 9 6"></polyline>
                  </svg>
                </div>
              </div>
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
}
