import React from "react";

interface InfoRowProps {
  label: string;
  value?: string | null;
}

export function InfoRow({ label, value }: InfoRowProps) {
  return (
    <div className="flex justify-between border-b border-slate-100 pb-2">
      <span className="font-medium text-slate-500">{label}</span>
      <span className="text-slate-800 ml-4">
        {value && value.trim() !== "" ? value : "—"}
      </span>
    </div>
  );
}
