import { apiInstance } from "api";

const api = apiInstance();

function getCategoryRcmd(data, success) {
  api.get(`/categories/recommendations`, data).then(success);
}

export { getCategoryRcmd };
