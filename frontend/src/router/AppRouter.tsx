import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import LoginPage from "../pages/LoginPage";
import UserPage from "../pages/UserPage";
import ProfileSetupPage from "../pages/ProfileSetupPage";

export function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/me" element={<UserPage />} />
        <Route path="/profile/setup" element={<ProfileSetupPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
}
