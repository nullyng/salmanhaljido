import { apiInstance } from "api";

const api = apiInstance();

function getCategoryRcmd(data, success) {
  api.get(`api/categories/recommendations`, data).then(success);
}

function recommendCategory(data, success) {
  api.get(`api/categories/recommendations/like`, data).then(success);
}

export { getCategoryRcmd, recommendCategory };
