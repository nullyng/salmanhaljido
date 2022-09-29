import { combineReducers } from "redux";

import map from "modules/map";
import region from "modules/region";
import input from "modules/input";
import CategorySet from "modules/CategorySet";

const rootReducer = combineReducers({
  map,
  region,
  input,
  CategorySet,
});

export default rootReducer;
