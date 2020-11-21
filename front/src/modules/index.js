import { combineReducers } from "redux";
import storeReducer from "./storeReducer";
import staffReducer from "./staffReducer";
import menuReducer from "./menuReducer";
import orderReducer from "./orderReducer";

const rootReducer = combineReducers({
  storeReducer,
  staffReducer,
  menuReducer,
  orderReducer,
});

export default rootReducer;
