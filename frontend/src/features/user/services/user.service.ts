import { api } from "./api";
import type {
  User,
  UserPage,
  UserProfileUpdateDTO,
  UserResponseDTO,
} from "../types/user.types";

export const userService = {
  getAll: async (page: number = 0, size: number = 5): Promise<UserPage> => {
    const response = await api.get("/users", {
      params: { page, size },
    });
    return response.data;
  },

  getByI: async (userId: number): Promise<UserResponseDTO> => {
    const response = await api.get(`/users/${userId}`);
    return response.data;
  },

  updateProfile: async (
    data: UserProfileUpdateDTO
  ): Promise<UserResponseDTO> => {
    const response = await api.put(`/users/profile`, data);
    return response.data;
  },
};
