const { apiInstance } = require("api");

const api = apiInstance();

function getBoard(category, pageNumber, success) {
  api.get(`boards/${category}?pageNo=${pageNumber}`).then(success);
}

export { getBoard };
