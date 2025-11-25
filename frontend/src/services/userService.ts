import apiClient from "../api/apiClient";
import type { UserProfileUpdateDTO, UserResponseDTO } from "../types/userType";

export async function getMyProfile(): Promise<UserResponseDTO> {
  const response = await apiClient.get<UserResponseDTO>("/users/me");
  return response.data;
}

export async function updateProfile(
  payload: UserProfileUpdateDTO
): Promise<UserResponseDTO> {
  const response = await apiClient.put<UserResponseDTO>(
    "/users/profile",
    payload
  );
  return response.data;
}
