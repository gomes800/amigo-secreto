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

export interface Page<T> {
  content: T[];
  pageable: {
    pageNumber: number;
    pageSize: number;
    sort: {
      sorted: boolean;
      unsorted: boolean;
      empty: boolean;
    };
    offset: number;
    paged: boolean;
    unpaged: boolean;
  };
  totalPages: number;
  totalElements: number;
  last: boolean;
  first: boolean;
  size: number;
  number: number;
  sort: {
    sorted: boolean;
    unsorted: boolean;
    empty: boolean;
  };
  numberOfElements: number;
  empty: boolean;
}

export type UserPage = Page<UserResponseDTO>;
