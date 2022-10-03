import { apiInstance } from "api";

const api = apiInstance();

function getRegionRcmd(data, success) {
  api.get(`api/recommendations`, { params: data }).then(success);
}

export { getRegionRcmd };
