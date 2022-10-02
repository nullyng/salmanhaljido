const SET_INPUT = "search/SET_INPUT";
const SET_CATEGORY = "search/SET_CATEGORY";
const SET_NEWS = "search/SET_NEWS";
const SET_TOTAL_COUNT = "search/SET_TOTAL_COUNT";
const SET_SEARCH = "search/SET_SEARCH";

export const setInput = (input) => ({
  type: SET_INPUT,
  input,
});
export const setCategory = (category) => ({
  type: SET_CATEGORY,
  category,
});
export const setNews = (news) => ({
  type: SET_NEWS,
  news,
});
export const setTotalCount = (totalCount) => ({
  type: SET_TOTAL_COUNT,
  totalCount,
});
export const setSearch = (search) => ({
  type: SET_SEARCH,
  search,
});

const initialState = {
  input: "",
  category: "",
  news: [],
  totalCount: 0,
  search: false, // 검색 여부
};

export default function search(state = initialState, action) {
  switch (action.type) {
    case SET_INPUT:
      return {
        ...state,
        input: action.input,
      };
    case SET_CATEGORY:
      return {
        ...state,
        category: action.category,
      };
    case SET_NEWS:
      return {
        ...state,
        news: action.news,
      };
    case SET_TOTAL_COUNT:
      return {
        ...state,
        totalCount: action.totalCount,
      };
    case SET_SEARCH:
      return {
        ...state,
        search: action.search,
      };
    default:
      return state;
  }
}
