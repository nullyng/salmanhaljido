import { combineReducers } from "redux";

import map from "modules/map";
import region from "modules/region";
import input from "modules/input";
import category from "modules/category";
import loading from "modules/loading";
import survey from "modules/survey";
import search from "modules/search";

const rootReducer = combineReducers({
  map,
  region,
  input,
  category,
  loading,
  survey,
  search,
});

export default rootReducer;
