"use client";

import { useState, useRef } from "react";
import { useRouter } from "next/navigation";
import { Button } from "@/components/ui/Button";
import { apiClient } from "@/lib/api";

export default function UploadResumeScreen() {
  const router = useRouter();
  const [file, setFile] = useState<File | null>(null);
  const [isDragging, setIsDragging] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files[0]) {
      setFile(e.target.files[0]);
      setError(null);
    }
  };

  const handleDrop = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    setIsDragging(false);
    if (e.dataTransfer.files && e.dataTransfer.files[0]) {
      setFile(e.dataTransfer.files[0]);
      setError(null);
    }
  };

  const handleDragOver = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    setIsDragging(true);
  };

  const handleDragLeave = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    setIsDragging(false);
  };

  const formatFileSize = (bytes: number) => {
    if (bytes === 0) return "0 B";
    const k = 1024;
    const sizes = ["B", "KB", "MB", "GB"];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(1)) + " " + sizes[i];
  };

  const handleProcessResume = async () => {
    if (!file) return;
    setIsLoading(true);
    setError(null);

    try {
      const formData = new FormData();
      formData.append("file", file);

      const data = await apiClient.post<any>("/analyze", formData);

      // Use the real score from the backend
      const generatedScore = data.match_score || 0;

      // Save analysis results and scores to localStorage
      localStorage.setItem("analysisResult", JSON.stringify(data));

      const prevScore = localStorage.getItem("matchScore") || "0";
      localStorage.setItem("prevMatchScore", prevScore);
      localStorage.setItem("matchScore", generatedScore.toString());

      // Navigate to the analysis page
      router.push("/dashboard/analysis");
    } catch (err: unknown) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError("An unexpected error occurred. Please ensure the backend server is running.");
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen pb-32 animate-in fade-in duration-500">
      {/* Top Bar */}
      <div className="flex items-center p-6 border-b border-border/50 sticky top-0 bg-background/80 backdrop-blur-md z-10">
        <button onClick={() => router.back()} className="p-2 mr-4 rounded-full hover:bg-secondary transition-colors">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="m15 18-6-6 6-6"/></svg>
        </button>
        <h1 className="text-xl font-bold tracking-tight">Upload Resume</h1>
      </div>

      {/* Hero Section */}
      <div className="relative w-full h-64 md:h-80 overflow-hidden bg-primary/20">
        <div className="absolute inset-0 bg-gradient-to-br from-primary/30 to-accent/30 mix-blend-overlay" />
        <div className="absolute inset-0 bg-gradient-to-t from-background via-background/80 to-transparent" />
        
        <div className="absolute bottom-0 left-0 p-8 w-full max-w-4xl mx-auto">
          <div className="inline-block bg-primary px-3 py-1 rounded-md mb-4 shadow-lg shadow-primary/20">
            <span className="text-[10px] font-bold text-white tracking-widest">AI ANALYSIS</span>
          </div>
          <h2 className="text-3xl md:text-5xl font-black text-foreground tracking-tight mb-2">SkillSync AI</h2>
          <p className="text-lg md:text-xl text-muted-foreground">Level up your career with AI insights</p>
        </div>
      </div>

      <div className="max-w-4xl mx-auto p-6 md:p-8 mt-4">
        {/* Section Title */}
        <div className="flex items-center mb-6">
          <div className="w-1.5 h-6 bg-primary rounded-full mr-3" />
          <h3 className="text-sm font-bold text-muted-foreground tracking-widest">SELECT YOUR RESUME</h3>
        </div>

        {/* Upload Area */}
        <div 
          className={`relative w-full h-64 rounded-3xl border-2 border-dashed transition-all duration-300 flex flex-col items-center justify-center cursor-pointer overflow-hidden ${isDragging ? "border-primary bg-primary/10 scale-[1.02]" : "border-border/80 bg-secondary/20 hover:bg-secondary/40"}`}
          onDrop={handleDrop}
          onDragOver={handleDragOver}
          onDragLeave={handleDragLeave}
          onClick={() => fileInputRef.current?.click()}
        >
          <input 
            type="file" 
            ref={fileInputRef} 
            onChange={handleFileChange} 
            className="hidden" 
            accept=".pdf,.docx,application/pdf,application/vnd.openxmlformats-officedocument.wordprocessingml.document" 
          />
          <div className="w-20 h-20 rounded-full bg-primary/10 flex items-center justify-center mb-6">
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="text-primary"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/><polyline points="17 8 12 3 7 8"/><line x1="12" y1="3" x2="12" y2="15"/></svg>
          </div>
          <p className="text-xl font-bold text-foreground mb-2">Tap or drag to browse files</p>
          <p className="text-sm text-muted-foreground">PDF or DOCX • Max 10MB</p>
        </div>

        {/* Error Message */}
        {error && (
          <div className="mt-4 animate-in slide-in-from-top-2 fade-in duration-300 bg-red-500/10 border border-red-500/30 rounded-2xl p-4 flex items-start gap-3">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#ef4444" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="shrink-0 mt-0.5"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>
            <p className="text-sm text-red-400 leading-relaxed">{error}</p>
          </div>
        )}

        {/* File Details / Empty State */}
        <div className="mt-8">
          {file ? (
            <div className="animate-in slide-in-from-bottom-4 fade-in duration-300">
              <h3 className="text-sm font-bold text-muted-foreground tracking-widest mb-4">FILE SELECTED</h3>
              <div className="bg-secondary/30 border border-border/50 rounded-2xl p-4 flex items-center shadow-lg">
                <div className="w-14 h-14 rounded-xl bg-primary/20 flex items-center justify-center mr-4 shrink-0">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="text-primary"><path d="M14.5 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V7.5L14.5 2z"/><polyline points="14 2 14 8 20 8"/><line x1="16" y1="13" x2="8" y2="13"/><line x1="16" y1="17" x2="8" y2="17"/><line x1="10" y1="9" x2="8" y2="9"/></svg>
                </div>
                <div className="flex-1 min-w-0">
                  <p className="font-bold text-foreground text-lg truncate">{file.name}</p>
                  <div className="flex items-center mt-1">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#22c55e" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round" className="mr-1.5"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/><polyline points="22 4 12 14.01 9 11.01"/></svg>
                    <span className="text-xs font-bold text-green-500">{formatFileSize(file.size)} • Ready</span>
                  </div>
                </div>
                <button 
                  onClick={(e) => { e.stopPropagation(); setFile(null); setError(null); }}
                  className="w-10 h-10 rounded-full bg-red-500/10 flex items-center justify-center hover:bg-red-500/20 transition-colors shrink-0 ml-4"
                >
                  <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#ef4444" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M18 6 6 18"/><path d="m6 6 12 12"/></svg>
                </button>
              </div>
            </div>
          ) : (
            <div className="bg-primary/5 border border-primary/20 rounded-2xl p-5 flex items-center">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="text-primary shrink-0 mr-4"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/><polyline points="17 8 12 3 7 8"/><line x1="12" y1="3" x2="12" y2="15"/></svg>
              <p className="text-sm text-primary leading-relaxed">
                No file selected yet. Please upload your resume to start the AI analysis.
              </p>
            </div>
          )}
        </div>
      </div>

      {/* Bottom Sticky Action Bar */}
      <div className="fixed bottom-16 md:bottom-0 left-0 md:left-64 right-0 bg-background/90 backdrop-blur-lg border-t border-border/50 p-4 md:p-6 z-20">
        <div className="max-w-4xl mx-auto">
          <Button 
            size="lg" 
            fullWidth 
            disabled={!file || isLoading}
            className={`h-16 rounded-2xl text-lg font-bold shadow-xl transition-all ${file && !isLoading ? "shadow-primary/25 translate-y-0 opacity-100" : "opacity-50 grayscale"}`}
            onClick={handleProcessResume}
          >
            {isLoading ? (
              <>
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="mr-3 animate-spin"><path d="M21 12a9 9 0 1 1-6.219-8.56"/></svg>
                Analyzing Resume...
              </>
            ) : (
              <>
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="mr-3"><path d="m12 3-1.912 5.813a2 2 0 0 1-1.275 1.275L3 12l5.813 1.912a2 2 0 0 1 1.275 1.275L12 21l1.912-5.813a2 2 0 0 1 1.275-1.275L21 12l-5.813-1.912a2 2 0 0 1-1.275-1.275L12 3Z"/><path d="M5 3v4"/><path d="M3 5h4"/><path d="M19 17v4"/><path d="M17 19h4"/></svg>
                Process Resume
              </>
            )}
          </Button>
        </div>
      </div>
    </div>
  );
}
