import { combineReducers } from "redux";
import storeReducer from "./storeReducer";
import staffReducer from "./staffReducer";
import menuReducer from "./menuReducer";

const rootReducer = combineReducers({
  storeReducer,
  staffReducer,
  menuReducer,
});

export default rootReducer;
