import { type FormEvent, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import type { UserResponseDTO, UserProfileUpdateDTO } from "../types/userType";
import { updateProfile } from "../services/userService";

import { AppLayout } from "../components/AppLayout";

interface LocationState {
  user?: UserResponseDTO;
}

export default function ProfileSetupPage() {
  const navigate = useNavigate();
  const location = useLocation();
  const state = location.state as LocationState | null;
  const initialUser = state?.user;

  const [form, setForm] = useState<UserProfileUpdateDTO>({
    firstName: initialUser?.firstName ?? "",
    lastName: initialUser?.lastName ?? "",
    birthDate: initialUser?.birthDate ?? null,
    gender: initialUser?.gender ?? null,
    wishList: initialUser?.wishList ?? null,
    preferences: initialUser?.preferences ?? null,
  });

  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const handleChange =
    (field: keyof UserProfileUpdateDTO) =>
    (
      e: React.ChangeEvent<
        HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
      >
    ) => {
      const value = e.target.value || null;

      setForm((prev) => ({
        ...prev,
        [field]: value,
      }));
    };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);
    setErrorMessage(null);

    try {
      if (!form.firstName || !form.lastName) {
        setErrorMessage("Nome e sobrenome são obrigatórios.");
        setIsSubmitting(false);
        return;
      }

      const payload: UserProfileUpdateDTO = {
        ...form,
        birthDate:
          form.birthDate && form.birthDate !== "" ? form.birthDate : null,
      };

      await updateProfile(payload);
      navigate("/", { replace: true });
    } catch (error: any) {
      console.error(error);
      const msg =
        error?.response?.data?.message ||
        "Erro ao atualizar perfil. Tente novamente.";
      setErrorMessage(msg);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <AppLayout>
      <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-xl">
        <h1 className="text-2xl font-semibold text-slate-900 mb-2">
          Complete seu perfil
        </h1>
        <p className="text-sm text-slate-600 mb-6">
          Só mais alguns detalhes para finalizar seu cadastro.
        </p>

        {errorMessage && (
          <div className="mb-4 rounded-md bg-red-50 border border-red-200 px-3 py-2 text-sm text-red-700">
            {errorMessage}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-slate-700 mb-1">
                Nome
              </label>
              <input
                type="text"
                value={form.firstName ?? ""}
                onChange={handleChange("firstName")}
                className="w-full rounded-md border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
                maxLength={100}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-slate-700 mb-1">
                Sobrenome
              </label>
              <input
                type="text"
                value={form.lastName ?? ""}
                onChange={handleChange("lastName")}
                className="w-full rounded-md border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
                maxLength={100}
                required
              />
            </div>
          </div>

          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-slate-700 mb-1">
                Data de nascimento
              </label>
              <input
                type="date"
                value={form.birthDate ?? ""}
                onChange={handleChange("birthDate")}
                className="w-full rounded-md border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-slate-700 mb-1">
                Gênero
              </label>
              <select
                value={form.gender ?? ""}
                onChange={handleChange("gender")}
                className="w-full rounded-md border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                <option value="">Prefiro não informar</option>
                <option value="MASCULINO">Masculino</option>
                <option value="FEMININO">Feminino</option>
                <option value="OUTRO">Outro</option>
              </select>
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1">
              Wishlist
            </label>
            <textarea
              value={form.wishList ?? ""}
              onChange={handleChange("wishList")}
              className="w-full rounded-md border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              rows={3}
              placeholder="O que você gostaria de ganhar no amigo secreto?"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1">
              Preferências
            </label>
            <textarea
              value={form.preferences ?? ""}
              onChange={handleChange("preferences")}
              className="w-full rounded-md border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              rows={3}
              placeholder="Ex: gosta de café, não gosta de chocolate..."
            />
          </div>

          <div className="pt-4 flex justify-end gap-3">
            <button
              type="button"
              onClick={() => navigate("/", { replace: true })}
              className="px-4 py-2 rounded-md text-sm font-medium text-slate-600 border border-slate-300 hover:bg-slate-50 transition"
            >
              Pular por enquanto
            </button>

            <button
              type="submit"
              disabled={isSubmitting}
              className="px-4 py-2 rounded-md text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 disabled:opacity-60 transition"
            >
              {isSubmitting ? "Salvando..." : "Salvar perfil"}
            </button>
          </div>
        </form>
      </div>
    </AppLayout>
  );
}
