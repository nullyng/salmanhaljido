const SET_RCMD_DATA = "map/SET_RCMD_DATA";

export const setRcmdData = (rcmdData) => ({ type: SET_RCMD_DATA, rcmdData });

const initialState = {
  rcmdData: []
};

export default function map(state = initialState, action) {
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