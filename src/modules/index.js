import { combineReducers } from "redux";

import map from "modules/map";
import region from "modules/region";
import input from "modules/input";
import category from "modules/category";

const rootReducer = combineReducers({
  map,
  region,
  input,
  category,
});

export default rootReducer;
