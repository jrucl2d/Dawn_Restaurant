// 액션 생성 함수
export const setUser = (userInfo) => {
  return { type: "SET_USER", userInfo };
};

// Initial State
const initialState = {
  userId: 1,
};

export default function userReducer(state = initialState, action) {
  switch (action.type) {
    case "SET_USER":
      return action.userInfo;
    default:
      return state;
  }
}
