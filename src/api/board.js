const { apiInstance } = require("api");

const api = apiInstance();

function getBoard(category, pageNumber, success) {
  api.get(`boards/${category}?pageNo=${pageNumber}`).then(success);
}

function getSearchBoard(category, pageNumber, search, success) {
  api.get(`boards/${category}?pageNo=${pageNumber}&search=${search}`)
    .then(success);
}

export { getBoard, getSearchBoard };
