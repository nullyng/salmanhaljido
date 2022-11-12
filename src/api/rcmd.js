import { apiInstance } from "api";

const api = apiInstance();

function getRegionRcmd(data, success) {
  api.get(`recommendations`, { params: data }).then(success);
}

function getRealEstate(search, success) {
  api.get(`boards/REAL_ESTATE?pageNo=0&search=${search}`).then(success);
}

export { getRegionRcmd, getRealEstate };
