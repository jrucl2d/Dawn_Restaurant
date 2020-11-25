// 액션 생성 함수
export const initialStore = (stores) => {
  return { type: "INITIAL_STORE", stores };
};
export const addStore = (newStore) => {
  return { type: "ADD_STORE", newStore };
};
export const deleteStore = (storeId) => {
  return { type: "DELETE_STORE", storeId };
};

// Initial State
const initialState = [
  // {
  //   storeId: "oweijfweojfewoijf124214",
  //   storeName: "빛나는피자 태국점",
  //   storeLocation: "태국",
  //   storeTime: "11:00~12:00(평일)",
  //   storeIntroduce: "엄청 맛있어요",
  //   storeImage: "",
  // },
  // {
  //   storeId: "wegegwegweghqwh34hewgwqf",
  //   storeName: "참신한치킨 아이티점",
  //   storeLocation: "아이티",
  //   storeTime: "01:00~02:00(평일)",
  //   storeIntroduce: "엄청 맛있었으면 좋겠네요",
  //   storeImage: "",
  // },
];

export default function storeReducer(state = initialState, action) {
  switch (action.type) {
    case "INITIAL_STORE":
      return action.stores;
    case "ADD_STORE":
      return [...state, action.newStore];
    case "DELETE_STORE":
      // eslint-disable-next-line array-callback-return
      return state.filter((store) => {
        if (action.storeId.findIndex((v) => +v === +store.storeId) === -1) {
          return store;
        }
      });
    default:
      return state;
  }
}
