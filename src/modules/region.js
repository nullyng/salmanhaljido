const SET_RCMD_DATA = "region/SET_RCMD_DATA";
const SET_CURR_REGION = "region/SET_CURR_REGION";
const SET_STATISTICS = "region/SET_STATISTICS";
const SET_REAL_ESTATE = "region/SET_REAL_ESTATE";

export const setRcmdData = (rcmdData) => ({ type: SET_RCMD_DATA, rcmdData });
export const setCurrRegion = (currRegion) => ({
  type: SET_CURR_REGION,
  currRegion,
});
export const setStatistics = (statistics) => ({
  type: SET_STATISTICS,
  statistics,
});
export const setRealEstate = (realEstate) => ({
  type: SET_REAL_ESTATE,
  realEstate,
});

const initialState = {
  // 추천 지역 전체 정보
  rcmdData: [],
  currRegion: {}, // 추천 지역 중 선택된 지역 정보
  statistics: {}, // 추천 지역 중 선택된 지역의 통계 정보
  realEstate: [], // 추천 지역 중 선택된 지역의 부동산 정보
};

export default function region(state = initialState, action) {
  switch (action.type) {
    case SET_RCMD_DATA:
      return {
        ...state,
        rcmdData: action.rcmdData,
      };
    case SET_CURR_REGION:
      return {
        ...state,
        currRegion: action.currRegion,
      };
    case SET_STATISTICS:
      return {
        ...state,
        statistics: action.statistics,
      };
    case SET_REAL_ESTATE:
      return {
        ...state,
        realEstate: action.realEstate,
      };
    default:
      return state;
  }
}
