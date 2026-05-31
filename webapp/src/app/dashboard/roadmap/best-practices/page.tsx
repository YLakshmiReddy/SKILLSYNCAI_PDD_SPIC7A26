"use client";

import React from "react";
import { useRouter } from "next/navigation";

export default function BestPracticesScreen() {
  const router = useRouter();
  const primaryBlue = "#1D4ED8";

  return (
    <div className="min-h-screen w-full bg-[#F1F5F9] font-sans pb-24 md:pb-10 overflow-x-hidden">
      {/* Header */}
      <div className="w-full h-16 bg-white border-b border-slate-200 flex items-center px-6 sticky top-0 z-20">
        <button onClick={() => router.back()} className="p-2 -ml-2 hover:bg-slate-100 rounded-full transition-colors mr-2">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#0F172A" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <line x1="19" y1="12" x2="5" y2="12"></line>
            <polyline points="12 19 5 12 12 5"></polyline>
          </svg>
        </button>
        <span className="text-lg font-bold text-[#1A1C1E]">Best Practices Hub</span>
      </div>

      <div className="max-w-3xl mx-auto px-6 py-12">
        <div className="mb-12">
          <h1 className="text-4xl md:text-5xl font-black text-[#1D4ED8] mb-4 leading-tight">
            Master the<br />Digital Canyon
          </h1>
          <p className="text-[#64748B] text-base md:text-lg leading-relaxed max-w-2xl">
            Professional guides on clean code, system design, and AI ethics to ensure your skills meet professional industry standards.
          </p>
        </div>

        <div className="grid grid-cols-1 gap-8">
          <BestPracticeCard
            title="Programming Languages"
            color={primaryBlue}
            points={[
              "Use the right tool for the job.",
              "Master the fundamentals before moving to libraries.",
              "Focus on readability and maintainability.",
              "Stay updated with latest language features.",
              "Write unit tests for your code.",
              "Follow language-specific style guides (PEP8, Google Style).",
              "Understand memory management and performance trade-offs."
            ]}
            tags={["JAVA", "PYTHON", "C++", "JAVASCRIPT", "GO", "RUST"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><polyline points="16 18 22 12 16 6"></polyline><polyline points="8 6 2 12 8 18"></polyline></svg>}
          />

          <BestPracticeCard
            title="Frameworks & Ecosystems"
            color={primaryBlue}
            points={[
              "Understand the underlying framework concepts.",
              "Choose frameworks with strong community support.",
              "Avoid over-engineering with too many libraries.",
              "Keep dependencies updated.",
              "Use official documentation as primary source.",
              "Leverage framework-specific CLI tools and scaffolding.",
              "Master the dependency injection and lifecycle management."
            ]}
            tags={["SPRING", "REACT", "DJANGO", "KUBERNETES", "TENSORFLOW"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><rect x="3" y="3" width="7" height="7"></rect><rect x="14" y="3" width="7" height="7"></rect><rect x="14" y="14" width="7" height="7"></rect><rect x="3" y="14" width="7" height="7"></rect></svg>}
          />

          <BestPracticeCard
            title="Frontend & UI Design"
            color={primaryBlue}
            points={[
              "Design with accessibility in mind (WCAG).",
              "Ensure responsive design across all devices.",
              "Optimize assets for fast loading.",
              "Use consistent design patterns.",
              "Prioritize user experience (UX).",
              "Stay updated with design trends.",
              "Master CSS and styling frameworks.",
              "Implement state management effectively (Redux, Zustand).",
              "Conduct usability testing early and often."
            ]}
            tags={["HTML5", "CSS3", "FIGMA", "RESPONSIVE"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg>}
          />

          <BestPracticeCard
            title="Backend & Infrastructure"
            color={primaryBlue}
            points={[
              "Build scalable and resilient systems.",
              "Use microservices architecture for complexity.",
              "Implement robust logging and monitoring.",
              "Ensure high availability and fault tolerance.",
              "Optimize database performance.",
              "Implement caching strategies (Redis, Memcached).",
              "Secure APIs with proper authentication and rate limiting."
            ]}
            tags={["SQL", "DOCKER", "AWS", "REST", "GRPC"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><rect x="2" y="2" width="20" height="8" rx="2" ry="2"></rect><rect x="2" y="14" width="20" height="8" rx="2" ry="2"></rect><line x1="6" y1="6" x2="6.01" y2="6"></line><line x1="6" y1="18" x2="6.01" y2="18"></line></svg>}
          />

          <BestPracticeCard
            title="DevOps, Security & Reliability"
            color={primaryBlue}
            points={[
              "Automate everything from build to deployment.",
              "Implement CI/CD pipelines.",
              "Prioritize security from the start.",
              "Use infrastructure as code (IaC).",
              "Monitor and respond to incidents.",
              "Perform regular security audits and penetration tests.",
              "Implement Secrets Management (Vault, Secrets Manager)."
            ]}
            tags={["LINUX", "KUBERNETES", "TERRAFORM", "SECURITY"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path></svg>}
          />

          <BestPracticeCard
            title="AI, Machine Learning & Data Science"
            color={primaryBlue}
            points={[
              "Understand data quality and pre-processing.",
              "Choose the right algorithms for the problem.",
              "Monitor model performance and bias.",
              "Use version control for data and models.",
              "Stay updated with AI research.",
              "Explainability and transparency in AI models (XAI).",
              "Implement robust data validation and cleaning pipelines."
            ]}
            tags={["SCIPY", "PYTORCH", "NLP", "LLM"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M12 5a3 3 0 1 0-5.997.125 4 4 0 0 0-2.526 5.77 4 4 0 0 0 .556 6.588A4 4 0 1 0 12 18Z"></path><path d="M12 5a3 3 0 1 1 5.997.125 4 4 0 0 1 2.526 5.77 4 4 0 0 1-.556 6.588A4 4 0 1 1 12 18Z"></path></svg>}
          />

          <BestPracticeCard
            title="Database Systems"
            color={primaryBlue}
            points={[
              "Choose the right database (SQL vs NoSQL).",
              "Indexing for performance.",
              "Database security and backup.",
              "Normalization vs Denormalization.",
              "Distributed databases and consistency.",
              "Monitor database health and slow queries.",
              "Implement database migration tools (Flyway, Liquibase)."
            ]}
            tags={["POSTGRES", "MONGODB", "REDIS", "CASSANDRA"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><ellipse cx="12" cy="5" rx="9" ry="3"></ellipse><path d="M21 12c0 1.66-4 3-9 3s-9-1.34-9-3"></path><path d="M3 5v14c0 1.66 4 3 9 3s9-1.34 9-3V5"></path></svg>}
          />

          <BestPracticeCard
            title="Software Architecture"
            color={primaryBlue}
            points={[
              "Design for change and extensibility.",
              "SOLID and DRY principles.",
              "Design patterns for common problems.",
              "Distributed systems design.",
              "Cloud native architecture.",
              "Use architectural decision records (ADRs).",
              "Focus on domain-driven design (DDD)."
            ]}
            tags={["MICROSERVICES", "EVENT-DRIVEN", "CLEAN-CODE"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><rect x="2" y="3" width="20" height="14" rx="2" ry="2"></rect><line x1="8" y1="21" x2="16" y2="21"></line><line x1="12" y1="17" x2="12" y2="21"></line></svg>}
          />

          <BestPracticeCard
            title="Soft Skills & Career"
            color={primaryBlue}
            points={[
              "Communication and collaboration.",
              "Time management and productivity.",
              "Continuous learning and growth.",
              "Networking and mentoring.",
              "Emotional intelligence and empathy.",
              "Conflict resolution and negotiation.",
              "Building a personal brand and portfolio."
            ]}
            tags={["AGILE", "LEADERSHIP", "PUBLIC-SPEAKING"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>}
          />

          <BestPracticeCard
            title="Testing & Quality Assurance"
            color={primaryBlue}
            points={[
              "Implement the testing pyramid (Unit, Integration, E2E).",
              "Practice Test-Driven Development (TDD).",
              "Use automated testing frameworks.",
              "Focus on test coverage and quality metrics.",
              "Perform manual exploratory testing.",
              "Implement mutation testing.",
              "Use linters and static analysis tools."
            ]}
            tags={["JUNIT", "SELENIUM", "JEST", "CYPRESS"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline points="22 4 12 14.01 9 11"></polyline></svg>}
          />

          <BestPracticeCard
            title="Cloud Computing & Platforms"
            color={primaryBlue}
            points={[
              "Understand cloud service models (IaaS, PaaS, SaaS).",
              "Leverage serverless architectures (Lambda, Cloud Functions).",
              "Optimize cloud costs and resource usage.",
              "Implement multi-cloud or hybrid cloud strategies.",
              "Master cloud provider specific tools and services.",
              "Design for regional availability and failover.",
              "Use Managed Services to reduce operational overhead."
            ]}
            tags={["AWS", "AZURE", "GCP", "SERVERLESS"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="M18 10h-1.26A8 8 0 1 0 9 20h9a5 5 0 0 0 0-10z"></path></svg>}
          />

          <BestPracticeCard
            title="Mobile Development"
            color={primaryBlue}
            points={[
              "Optimize app performance and battery life.",
              "Ensure seamless offline experience.",
              "Follow platform-specific design guidelines (Material, HIG).",
              "Master native vs cross-platform development trade-offs.",
              "Implement robust crash reporting and analytics.",
              "Design for various screen sizes and orientations.",
              "Secure user data and app communications."
            ]}
            tags={["KOTLIN", "SWIFT", "FLUTTER", "REACT-NATIVE"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><rect x="5" y="2" width="14" height="20" rx="2" ry="2"></rect><line x1="12" y1="18" x2="12.01" y2="18"></line></svg>}
          />

          <BestPracticeCard
            title="API Design & Management"
            color={primaryBlue}
            points={[
              "Follow RESTful principles for web APIs.",
              "Use GraphQL for efficient data fetching.",
              "Provide comprehensive API documentation (Swagger/OpenAPI).",
              "Implement API versioning and backward compatibility.",
              "Monitor API usage and performance.",
              "Secure APIs with OAuth2 and JWT.",
              "Leverage API Gateways for management and security."
            ]}
            tags={["REST", "GRAPHQL", "SWAGGER", "POSTMAN"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><line x1="4" y1="9" x2="20" y2="9"></line><line x1="4" y1="15" x2="20" y2="15"></line><line x1="10" y1="3" x2="8" y2="21"></line><line x1="16" y1="3" x2="14" y2="21"></line></svg>}
          />

          <BestPracticeCard
            title="Agile & Project Management"
            color={primaryBlue}
            points={[
              "Embrace Agile values and principles.",
              "Participate in Scrum or Kanban ceremonies.",
              "Break down work into manageable tasks.",
              "Prioritize product backlogs effectively.",
              "Focus on continuous delivery and feedback.",
              "Use project management tools (Jira, Trello, Linear).",
              "Foster a culture of transparency and accountability."
            ]}
            tags={["SCRUM", "KANBAN", "JIRA", "PRODUCTIVITY"]}
            icon={<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><polyline points="23 6 13.5 15.5 8.5 10.5 1 18"></polyline><polyline points="17 6 23 6 23 12"></polyline></svg>}
          />
        </div>
      </div>
    </div>
  );
}

function BestPracticeCard({ title, icon, color, points, tags }: {
  title: string,
  icon: React.ReactNode,
  color: string,
  points: string[],
  tags: string[]
}) {
  return (
    <div className="bg-white rounded-2xl p-6 md:p-8 shadow-sm border border-slate-100 transition-all hover:shadow-md">
      <div className="flex items-center mb-6">
        <div className="w-10 h-10 rounded-lg bg-blue-50 flex items-center justify-center shrink-0" style={{ color: color }}>
          {icon}
        </div>
        <h3 className="ml-4 text-lg md:text-xl font-bold text-[#0F172A]">{title}</h3>
      </div>
      
      <ul className="space-y-4 mb-8">
        {points.map((point, idx) => (
          <li key={idx} className="flex items-start">
            <span className="text-blue-600 font-bold mr-3 mt-0.5">•</span>
            <p className="text-sm md:text-base text-[#64748B] text-pretty leading-relaxed">{point}</p>
          </li>
        ))}
      </ul>

      <div className="pt-6 border-t border-slate-50">
        <p className="text-[11px] font-black tracking-widest text-blue-600 uppercase">
          {tags.join(" | ")}
        </p>
      </div>
    </div>
  );
}
