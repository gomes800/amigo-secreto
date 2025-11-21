export interface User {
  userId: number;
  email: string;
  provider: string;
  providerId: string;
  username: string;
  avatarUrl?: string;
  firstName?: string;
  lastName?: string;
  birthDate?: string;
  gender?: string;
  registerDate: string;
  wishList?: string;
  preferences?: string;
}

export interface UserProfileUpdateDTO {
  fistName: string;
  lastName: string;
  birthDate?: string;
  gender?: string;
  wishList?: string;
  preferences?: string;
}

export interface UserResponseDTO {
  userId: number;
  firstName?: string;
  lastName?: string;
  birthDate?: string;
  gender?: string;
  wishList?: string;
  preferences?: string;
}

export interface UserSummaryDTO {
  userId: number;
  firstName: string;
  lastName: string;
  wishList: string;
}
