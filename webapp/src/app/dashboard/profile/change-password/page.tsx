"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";

export default function ChangePasswordScreen() {
  const router = useRouter();
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [showCurrent, setShowCurrent] = useState(false);
  const [showNew, setShowNew] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState<{ type: "success" | "error", text: string } | null>(null);

  const handleUpdate = (e: React.FormEvent) => {
    e.preventDefault();
    if (newPassword !== confirmPassword) {
      setMessage({ type: "error", text: "New passwords do not match." });
      return;
    }
    if (newPassword.length < 8) {
      setMessage({ type: "error", text: "Password must be at least 8 characters." });
      return;
    }

    setIsLoading(true);
    // Simulate API call
    setTimeout(() => {
      setIsLoading(false);
      setMessage({ type: "success", text: "Password updated successfully!" });
      setTimeout(() => router.back(), 1500);
    }, 1000);
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
        <h1 className="text-lg font-bold text-[#1A1C1E]">Change Password</h1>
      </div>

      <div className="max-w-md mx-auto px-6 py-10">
        <div className="text-center mb-10">
          <div className="w-16 h-16 bg-indigo-50 rounded-2xl flex items-center justify-center mx-auto mb-4">
             <svg width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="#673AB7" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
               <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
               <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
             </svg>
          </div>
          <h2 className="text-2xl font-bold text-[#0F172A]">Secure Your Account</h2>
          <p className="text-slate-500 text-sm mt-2">Update your password regularly to stay safe.</p>
        </div>

        <form onSubmit={handleUpdate} className="space-y-6">
          <PasswordField
            label="Current Password"
            value={currentPassword}
            onChange={(e: any) => setCurrentPassword(e.target.value)}
            visible={showCurrent}
            toggleVisible={() => setShowCurrent(!showCurrent)}
          />

          <PasswordField
            label="New Password"
            value={newPassword}
            onChange={(e: any) => setNewPassword(e.target.value)}
            visible={showNew}
            toggleVisible={() => setShowNew(!showNew)}
          />

          <div className="flex flex-col">
            <label className="text-sm font-bold text-[#0F172A] mb-2 px-1">Confirm New Password</label>
            <input
              type="password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              placeholder="Re-type new password"
              className="w-full h-14 px-4 bg-[#F8FAFC] border border-[#E2E8F0] rounded-xl text-black outline-none focus:border-[#673AB7] transition-all"
              required
            />
          </div>

          {message && (
            <div className={`p-4 rounded-xl text-center text-sm font-bold animate-in fade-in slide-in-from-top-2 ${message.type === 'success' ? 'bg-green-50 text-green-600' : 'bg-red-50 text-red-600'}`}>
              {message.text}
            </div>
          )}

          <button
            type="submit"
            disabled={isLoading}
            className={`w-full h-14 rounded-xl font-bold text-white transition-all shadow-lg flex items-center justify-center ${isLoading ? 'bg-slate-300' : 'bg-[#673AB7] hover:bg-[#5E35B1]'}`}
          >
            {isLoading ? (
              <svg className="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
            ) : (
              "Update Password"
            )}
          </button>
        </form>
      </div>
    </div>
  );
}

function PasswordField({ label, value, onChange, visible, toggleVisible }: any) {
  return (
    <div className="flex flex-col">
      <label className="text-sm font-bold text-[#0F172A] mb-2 px-1">{label}</label>
      <div className="relative">
        <input
          type={visible ? "text" : "password"}
          value={value}
          onChange={onChange}
          placeholder={`Enter ${label.toLowerCase()}`}
          className="w-full h-14 px-4 pr-12 bg-[#F8FAFC] border border-[#E2E8F0] rounded-xl text-black outline-none focus:border-[#673AB7] transition-all"
          required
        />
        <button
          type="button"
          onClick={toggleVisible}
          className="absolute right-4 top-1/2 -translate-y-1/2 text-[#94A3B8] hover:text-[#673AB7] transition-colors"
        >
          {visible ? (
            <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>
          ) : (
            <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"></path><line x1="1" y1="1" x2="23" y2="23"></line></svg>
          )}
        </button>
      </div>
    </div>
  );
}
