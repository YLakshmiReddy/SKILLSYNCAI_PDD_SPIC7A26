"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";

export default function AccountInfoScreen() {
  const router = useRouter();
  
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [targetRole, setTargetRole] = useState("");

  const primaryPurple = "#673AB7";

  React.useEffect(() => {
    const data = localStorage.getItem("userData");
    if (data) {
      const user = JSON.parse(data);
      setFullName(user.full_name || "");
      setEmail(user.email || "");
      setPhone(user.phone || "+1 234 567 890");
      setTargetRole(user.role || "Professional");
    }
  }, []);

  const handleSave = () => {
    const data = localStorage.getItem("userData");
    if (data) {
      const user = JSON.parse(data);
      const updatedUser = { ...user, full_name: fullName, email, phone, role: targetRole };
      localStorage.setItem("userData", JSON.stringify(updatedUser));
    }
    router.back();
  };

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
        <h1 className="text-lg font-bold text-[#1A1C1E]">Account Information</h1>

        <button className="ml-auto p-2 text-[#673AB7] hover:bg-indigo-50 rounded-full transition-colors">
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
            <polyline points="7 10 12 15 17 10"></polyline>
            <line x1="12" y1="15" x2="12" y2="3"></line>
          </svg>
        </button>
      </div>

      <div className="max-w-xl mx-auto px-6 py-10">
        {/* Profile Picture Section */}
        <div className="flex flex-col items-center mb-12">
          <div className="relative group cursor-pointer">
            <div className="w-[140px] h-[140px] rounded-full bg-[#F5F5F5] border-[3px] border-[#673AB7] flex items-center justify-center overflow-hidden shadow-md">
              <svg width="70" height="70" viewBox="0 0 24 24" fill="none" stroke="#757575" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
                <circle cx="12" cy="7" r="4"></circle>
              </svg>
            </div>
            {/* Camera Overlay */}
            <div className="absolute bottom-2 right-2 w-9 h-9 bg-[#673AB7] rounded-full flex items-center justify-center shadow-lg border-2 border-white">
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="white" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path>
                <circle cx="12" cy="13" r="4"></circle>
              </svg>
            </div>
          </div>
        </div>

        {/* Form Fields */}
        <div className="space-y-6">
          <EditableField 
            label="Full Name" 
            value={fullName} 
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setFullName(e.target.value)}
            icon={<path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>}
            primaryColor={primaryPurple}
          />
          
          <EditableField 
            label="Email Address" 
            value={email} 
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEmail(e.target.value)}
            type="email"
            icon={<><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path><polyline points="22,6 12,13 2,6"></polyline></>}
            primaryColor={primaryPurple}
          />

          <EditableField 
            label="Phone Number" 
            value={phone} 
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setPhone(e.target.value)}
            type="tel"
            icon={<path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"></path>}
            primaryColor={primaryPurple}
          />

          <EditableField 
            label="Target Role" 
            value={targetRole} 
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setTargetRole(e.target.value)}
            icon={<><rect x="2" y="7" width="20" height="14" rx="2" ry="2"></rect><path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path></>}
            primaryColor={primaryPurple}
          />

          <div className="pt-8">
            <button 
              onClick={handleSave}
              className="w-full h-14 bg-[#673AB7] hover:bg-[#5E35B1] text-white font-bold rounded-xl shadow-lg shadow-indigo-100 transition-all flex items-center justify-center"
            >
              Save Changes
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

function EditableField({ label, value, onChange, icon, type = "text", primaryColor }: any) {
  return (
    <div className="flex flex-col">
      <label className="text-sm font-bold text-[#212121] mb-2 ml-1">{label}</label>
      <div className="relative">
        <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none" style={{ color: primaryColor }}>
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            {icon}
            {label === "Full Name" && <circle cx="12" cy="7" r="4"></circle>}
          </svg>
        </div>
        <input
          type={type}
          value={value}
          onChange={onChange}
          className="w-full h-14 pl-12 pr-4 bg-white border border-[#E2E8F0] rounded-xl text-[#1A1C1E] font-medium focus:border-[#673AB7] outline-none transition-all"
        />
      </div>
    </div>
  );
}
