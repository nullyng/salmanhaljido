import { combineReducers } from "redux";

import map from "modules/map";
import region from "modules/region";
import CategorySet from "modules/CategorySet"

const rootReducer = combineReducers({
  map,
  region,
  CategorySet,
});

export default rootReducer;
