const SET_MAP_DATA = "map/SET_MAP_DATA";
const SET_MARKERS = "map/SET_MARKERS";
const SET_CURR_MAP = "map/SET_CURR_MAP";

export const setMapData = (mapData) => ({ type: SET_MAP_DATA, mapData });
export const setMarkers = (markers) => ({ type: SET_MARKERS, markers });
export const setCurrMap = (currMap) => ({ type: SET_CURR_MAP, currMap });

const initialState = {
  mapData: [],
  markers: [],
  currMap: {},
};

export default function region(state = initialState, action) {
  switch (action.type) {
    case SET_MAP_DATA:
      return {
        ...state,
        mapData: action.mapData,
      };
    case SET_MARKERS:
      return {
        ...state,
        markers: action.markers,
      };
    case SET_CURR_MAP:
      return {
        ...state,
        currMap: action.currMap,
      }
    default:
      return state;
  }
}