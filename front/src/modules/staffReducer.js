// 액션 생성 함수

export const initalStaff = (staffs) => {
  return { type: "INIT_STAFF", staffs };
};
export const addStaff = (newStaff) => {
  return { type: "ADD_STAFF", newStaff };
};
export const deleteStaff = (staffId) => {
  return { type: "DELETE_STAFF", staffId };
};
export const updateStaff = (staffInfo) => {
  return { type: "UPDATE_STAFF", staffInfo };
};

// Initial State
const initialState = [
  // {
  //   storeId: "oweijfweojfewoijf124214",
  //   staffId: "lwkejglkwjgoiweweoieg",
  //   staffName: "동아무개",
  //   staffBirth: "2020-01-13",
  //   staffPosition: "사장",
  //   staffPay: "2400원/시",
  //   staffSex: "male",
  //   staffImage: "",
  // },
  // {
  //   storeId: "oweijfweojfewoijf124214",
  //   staffId: "lwkejglkwjgo23iweweoieg",
  //   staffName: "정아무개",
  //   staffBirth: "2020-11-02",
  //   staffPosition: "매니저",
  //   staffPay: "1200원/월",
  //   staffSex: "female",
  //   staffImage: "",
  // },
];

export default function storeReducer(state = initialState, action) {
  switch (action.type) {
    case "INIT_STAFF":
      return action.staffs;
    case "ADD_STAFF":
      return [...state, action.newStaff];
    case "DELETE_STAFF":
      return state.filter((staff) => staff.staffId !== action.staffId);
    case "UPDATE_STAFF":
      return state.map((staff) => {
        return staff.staffId === action.staffInfo.staffId
          ? action.staffInfo
          : staff;
      });
    default:
      return state;
  }
}
