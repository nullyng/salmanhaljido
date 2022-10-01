import axios from "axios";

function apiInstance() {
  const instance = axios.create({
    baseURL: "https://j7d110.p.ssafy.io/api",
    headers: {
      "Content-type": "application/json",
    },
  });

  return instance;
}

export { apiInstance };
