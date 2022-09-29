const SET_REGION = "input/SET_REGION";
const SET_USER_INFO = "input/SET_USER_INFO";
const SET_PRICE = "input/SET_PRICE";

export const setRegion = (region) => ({ type: SET_REGION, region });
export const setUserInfo = (userInfo) => ({ type: SET_USER_INFO, userInfo });
export const setPrice = (price) => ({ type: SET_PRICE, price });

const initialState = {
  region: "",
  userInfo: {
    hasCar: false,
    hasPets: false,
    hasChildren: false,
  },
  price: {
    jeonse: [0, 28],
    maemae: [0, 28],
  },
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
    case SET_PRICE:
      return {
        ...state,
        price: action.price,
      };
    default:
      return state;
  }
}
