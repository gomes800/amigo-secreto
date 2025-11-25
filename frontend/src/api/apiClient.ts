import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/api",
  withCredentials: true,
});

apiClient.interceptors.request.use((config) => {
  const xsrfCookieName = "XSRF-TOKEN";

  const getCookie = (name: string) => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop()!.split(";").shift();
    return null;
  };

  const token = getCookie(xsrfCookieName);

  if (token && !config.headers["X-XSRF-TOKEN"]) {
    config.headers["X-XSRF-TOKEN"] = token;
  }

  return config;
});

export default apiClient;
