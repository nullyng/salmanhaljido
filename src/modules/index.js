import { combineReducers } from "redux";
import map from "modules/map";
import region from "modules/region";

const rootReducer = combineReducers({
  map,
  region,
});

export default rootReducer;
