import { apiInstance } from "api";

const api = apiInstance();

function getCategoryRcmd(data, success) {
  api.get(`api/categories/recommendations`, { params: data }).then(success);
}

function recommendCategory(data, success) {
  api
    .get(`api/categories/recommendations/like`, { params: data })
    .then(success);
}

export { getCategoryRcmd, recommendCategory };
