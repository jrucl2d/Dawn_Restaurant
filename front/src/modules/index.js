import { combineReducers } from "redux";
import storeReducer from "./storeReducer";
import staffReducer from "./staffReducer";

const rootReducer = combineReducers({ storeReducer, staffReducer });

export default rootReducer;
