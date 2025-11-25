import type { Group } from "./groupType";

export interface User {
  userId: number;
  email: string;
  username: string;
  provider: string;
  providerId: number;
  avatarUrl: string | null;
  firstName: string | null;
  lastName: string | null;
  birthDate: string | null;
  gender: string | null;
  groups: Group[] | null;
  registerDate: string;
  wishList: string | null;
  preferences: string | null;
}

export interface UserResponseDTO {
  userId: number;
  email: string;
  username: string;
  provider: string;
  avatarUrl: string | null;
  firstName: string | null;
  lastName: string | null;
  birthDate: string | null;
  gender: string | null;
  wishList: string | null;
  preferences: string | null;
}

export interface UserProfileUpdateDTO {
  firstName: string;
  lastName: string;
  birthDate: string | null;
  gender: string | null;
  wishList: string | null;
  preferences: string | null;
}
