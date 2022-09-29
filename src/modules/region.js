const SET_RCMD_DATA = "region/SET_RCMD_DATA";
const SET_CURR_REGION = "region/SET_CURR_REGION";
const SET_STATISTICS = "region/SET_STATISTICS";
const SET_REAL_ESTATE = "region/SET_REAL_ESTATE";

export const setRcmdData = (rcmdData) => ({ type: SET_RCMD_DATA, rcmdData });
export const setCurrRegion = (currRegion) => ({ type: SET_CURR_REGION, currRegion });
export const setStatistics = (statistics) => ({ type: SET_STATISTICS, statistics });
export const setRealEstate = (realEstate) => ({ type: SET_REAL_ESTATE, realEstate });

const initialState = {
  // 추천 지역 전체 정보
  rcmdData: [{ name: "대구광역시 남구", ranking: 1, score: 5, code: "27200", lat: 128.585329627647, lng: 35.8351771566264, stat: ["통계1", "통계2"], re: [{ title: "1", url: "https:/www.naver.com" }, { title: "2", url: "https:/www.naver.com" }], },
  { name: "대구광역시 중구", ranking: 2, score: 5, code: "27110", lat: 128.685659919878, lng: 35.8665352870788, stat: ["통계1", "통계2"], re: [{ title: "1", url: "https:/www.naver.com" }, { title: "2", url: "https:/www.naver.com" }], },
  { name: "대구광역시 수성구", ranking: 3, score: 4, code: "27260", lat: 128.66127441262, lng: 35.8338488599246, stat: ["통계1", "통계2"], re: [{ title: "1", url: "https:/www.naver.com" }, { title: "2", url: "https:/www.naver.com" }], },
  { name: "대구광역시 북구", ranking: 4, score: 3.5, code: "27230", lat: 128.577204365928, lng: 35.9289223694324, stat: ["통계1", "통계2"], re: [{ title: "1", url: "https:/www.naver.com" }, { title: "2", url: "https:/www.naver.com" }], },
  { name: "대구광역시 서구", ranking: 5, score: 3, code: "27170", lat: 128.549697600138, lng: 35.8750016701213, stat: ["통계1", "통계2"], re: [{ title: "1", url: "https:/www.naver.com" }, { title: "2", url: "https:/www.naver.com" }], },
  { name: "대구광역시 동구", ranking: 6, score: 2, code: "27140", lat: 128.685659919878, lng: 35.9344263269646, stat: ["통계1", "통계2"], re: [{ title: "1", url: "https:/www.naver.com" }, { title: "2", url: "https:/www.naver.com" }], }],
  currRegion: {}, // 추천 지역 중 선택된 지역 정보
  statistics: [], // 추천 지역 중 선택된 지역의 통계 정보
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