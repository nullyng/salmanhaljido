const SET_RCMD_DATA = "region/SET_RCMD_DATA";

export const setRcmdData = (rcmdData) => ({ type: SET_RCMD_DATA, rcmdData });

const initialState = {
  rcmdData: []
};

export default function region(state = initialState, action) {
  switch (action.type) {
    case SET_RCMD_DATA:
      return {
        ...state,
        rcmdData: action.rcmdData,
      };
    default:
      return state;
  }
}