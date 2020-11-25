// 액션 생성 함수
export const initailMenu = (menus) => {
  return { type: "INITIAL_MENU", menus };
};
export const addMenu = (newMenu) => {
  return { type: "ADD_MENU", newMenu };
};
export const deleteMenu = (menuId) => {
  return { type: "DELETE_MENU", menuId };
};
export const updateMenu = (menuInfo) => {
  return { type: "UPDATE_MENU", menuInfo };
};

// Initial State
const initialState = [
  // {
  //   storeId: "oweijfweojfewoijf124214",
  //   menuId: "lfjwelfewjfklewjglkjwe",
  //   menuName: "망한 음식",
  //   menuPrice: 12000,
  //   menuOrigin: "한국산",
  //   menuIntroduce: "엄청나게 맛난 음식",
  //   menuImage: "",
  // },
];

export default function storeReducer(state = initialState, action) {
  switch (action.type) {
    case "INITIAL_MENU":
      return action.menus;
    case "ADD_MENU":
      return [...state, action.newMenu];
    case "DELETE_MENU":
      return state.filter((Menu) => Menu.menuId !== action.menuId);
    case "UPDATE_MENU":
      return state.map((menu) => {
        return menu.menuId === action.menuInfo.menuId ? action.menuInfo : menu;
      });
    default:
      return state;
  }
}
