import { combineReducers } from "redux";
import storeReducer from "./storeReducer";
import staffReducer from "./staffReducer";
import menuReducer from "./menuReducer";
import orderReducer from "./orderReducer";
import userReducer from "./userReducer";

const rootReducer = combineReducers({
  storeReducer,
  staffReducer,
  menuReducer,
  orderReducer,
  userReducer,
});

export default rootReducer;
