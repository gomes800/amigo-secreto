import { useEffect, useState } from "react";
import { getMyProfile } from "../services/userService";
import type { UserResponseDTO } from "../types/userType";
import { AppLayout } from "../components/AppLayout";
import { InfoRow } from "../components/InfoRow";
import { useNavigate } from "react-router-dom";

type Status = "idle" | "loading" | "success" | "error" | "unauthenticated";

export default function UserPage() {
  const [user, setUser] = useState<UserResponseDTO | null>(null);
  const [status, setStatus] = useState<Status>("idle");
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUser = async () => {
      setStatus("loading");
      setErrorMessage(null);

      try {
        const data = await getMyProfile();

        const isProfileIncomplete =
          !data.firstName ||
          data.firstName.trim() === "" ||
          !data.lastName ||
          data.lastName.trim() === "";

        if (isProfileIncomplete) {
          navigate("/profile/setup", { replace: true, state: { user: data } });
          return;
        }

        setUser(data);
        setStatus("success");
      } catch (error: any) {
        console.error(error);

        if (
          error?.response?.status === 401 ||
          error?.response?.status === 403
        ) {
          setStatus("unauthenticated");
          setErrorMessage("Você precisa estar logado para ver este perfil.");
        } else {
          setStatus("error");
          setErrorMessage(
            error?.response?.data?.message ||
              "Ocorreu um erro ao carregar seu perfil."
          );
        }
      }
    };

    fetchUser();
  }, [navigate]);

  if (status === "loading") {
    return (
      <AppLayout>
        <div className="text-slate-700 text-lg">Carregando seu perfil...</div>
      </AppLayout>
    );
  }

  if (status === "unauthenticated") {
    return (
      <AppLayout>
        <div className="bg-white shadow-md rounded-lg p-6 max-w-md w-full text-center">
          <h1 className="text-2xl font-semibold mb-2 text-slate-800">
            Acesso restrito
          </h1>
          <p className="text-slate-600 mb-4">
            Você precisa estar autenticado via OAuth2 para acessar esta página.
          </p>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            className="inline-flex items-center justify-center px-4 py-2 rounded-md bg-blue-600 text-white font-medium hover:bg-blue-700 transition"
          >
            Entrar com Google
          </a>
        </div>
      </AppLayout>
    );
  }

  if (status === "error") {
    return (
      <AppLayout>
        <div className="bg-white shadow-md rounded-lg p-6 max-w-md w-full text-center">
          <h1 className="text-2xl font-semibold mb-2 text-red-600">
            Erro ao carregar perfil
          </h1>
          <p className="text-slate-600 mb-4">
            {errorMessage || "Tente novamente mais tarde."}
          </p>
        </div>
      </AppLayout>
    );
  }

  if (!user) {
    return <AppLayout>{null}</AppLayout>;
  }

  const fullName =
    (user.firstName || user.lastName) &&
    `${user.firstName ?? ""} ${user.lastName ?? ""}`.trim();

  return (
    <AppLayout>
      <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-lg">
        <div className="flex items-center gap-4 mb-6">
          {user.avatarUrl ? (
            <img
              src={user.avatarUrl}
              alt={user.username ?? user.email}
              className="w-16 h-16 rounded-full object-cover border border-slate-200"
            />
          ) : (
            <div className="w-16 h-16 rounded-full bg-slate-200 flex items-center justify-center text-xl font-semibold text-slate-600">
              {user.username?.[0]?.toUpperCase() ?? user.email[0].toUpperCase()}
            </div>
          )}

          <div>
            <h1 className="text-2xl font-semibold text-slate-800">
              {fullName || user.username || "Usuário"}
            </h1>
            <p className="text-sm text-slate-500">{user.email}</p>
            <p className="text-xs text-slate-400">
              Provider: {user.provider || "N/A"}
            </p>
          </div>
        </div>

        <div className="space-y-3 text-sm text-slate-700">
          <InfoRow label="Username" value={user.username} />
          <InfoRow label="Nome" value={fullName} />
          <InfoRow
            label="Data de nascimento"
            value={
              user.birthDate &&
              new Date(user.birthDate).toLocaleDateString("pt-BR")
            }
          />
          <InfoRow label="Gênero" value={user.gender} />
          <InfoRow label="Wishlist" value={user.wishList} />
          <InfoRow label="Preferências" value={user.preferences} />
        </div>
      </div>
    </AppLayout>
  );
}
