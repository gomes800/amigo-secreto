import { AppLayout } from "../components/AppLayout";

const BACKEND_BASE_URL = "http://localhost:8080";

export default function LoginPage() {
  const handleLogin = (provider: "google" | "github") => {
    window.location.href = `${BACKEND_BASE_URL}/oauth2/authorization/${provider}`;
  };

  return (
    <AppLayout>
      <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md text-center">
        <h1 className="text-2xl font-semibold text-slate-900 mb-2">
          Entrar no Amigo Secreto
        </h1>
        <p className="text-sm text-slate-600 mb-6">
          Faça login usando uma das opções abaixo:
        </p>

        <div className="flex flex-col gap-3">
          <button
            onClick={() => handleLogin("google")}
            className="w-full inline-flex items-center justify-center gap-2 px-4 py-2 rounded-md bg-white border border-slate-200 text-slate-800 font-medium hover:bg-slate-50 transition"
          >
            <span className="text-red-500 text-lg">G</span>
            <span>Continuar com Google</span>
          </button>

          <button
            onClick={() => handleLogin("github")}
            className="w-full inline-flex items-center justify-center gap-2 px-4 py-2 rounded-md bg-slate-900 text-white font-medium hover:bg-black transition"
          >
            <span className="text-lg">🐙</span>
            <span>Continuar com GitHub</span>
          </button>
        </div>

        <p className="text-xs text-slate-400 mt-6">
          Ao continuar, você concorda com os termos de uso e política de
          privacidade do aplicativo.
        </p>
      </div>
    </AppLayout>
  );
}
