import type { User } from "./userType";

export interface Group {
  groupId: number;
  name: string;
  size: number;
  createDate: string;
  participants: User[];
  groupAdmin: User;
  description: string;
  eventDate: string;
  priceLimit: number;
  drawCompleted: boolean;
  rules: string;
}
