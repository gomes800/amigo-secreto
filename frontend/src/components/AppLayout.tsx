import React from "react";
import { Link } from "react-router-dom";

interface AppLayoutProps {
  children: React.ReactNode;
}

export function AppLayout({ children }: AppLayoutProps) {
  return (
    <div className="min-h-screen bg-slate-100 flex flex-col">
      <header className="bg-white shadow-sm">
        <div className="max-w-5xl mx-auto px-4 py-3 flex items-center justify-between">
          <Link to="/" className="text-xl font-semibold text-slate-800">
            Amigo Secreto
          </Link>

          <nav className="flex items-center gap-4 text-sm">
            <Link
              to="/"
              className="text-slate-600 hover:text-slate-900 transition"
            >
              Meu Perfil
            </Link>
            <Link
              to="/login"
              className="text-slate-600 hover:text-slate-900 transition"
            >
              Login
            </Link>
          </nav>
        </div>
      </header>

      <main className="flex-1 flex items-center justify-center">
        {children}
      </main>
    </div>
  );
}
