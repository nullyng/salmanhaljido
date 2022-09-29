const SET_REGION = "input/SET_REGION";
const SET_USER_INFO = "input/SET_USER_INFO";
const SET_CATEGORIES = "input/SET_CATEGORIES";
const SET_PRICE = "input/SET_PRICE";

export const setRegion = (region) => ({ type: SET_REGION, region });
export const setUserInfo = (userInfo) => ({ type: SET_USER_INFO, userInfo });
export const setCategories = (categories) => ({
  type: SET_CATEGORIES,
  categories,
});
export const setPrice = (price) => ({ type: SET_PRICE, price });

const initialState = {
  region: "",
  userInfo: [],
  categories: [],
  price: [],
};

export default function input(state = initialState, action) {
  switch (action.type) {
    case SET_REGION:
      return {
        ...state,
        region: action.region,
      };
    case SET_USER_INFO:
      return {
        ...state,
        userInfo: action.userInfo,
      };
    case SET_CATEGORIES:
      return {
        ...state,
        categories: action.categories,
      };
    case SET_PRICE:
      return {
        ...state,
        price: action.price,
      };
    default:
      return state;
  }
}
