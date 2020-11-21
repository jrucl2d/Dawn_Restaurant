// 액션 생성 함수
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
  {
    storeId: "oweijfweojfewoijf124214",
    menuId: "lfjwelfewjfklewjglkjwe",
    menuName: "망한 음식",
    menuPrice: 12000,
    menuOrigin: "한국산",
    menuIntroduce: "엄청나게 맛난 음식",
    menuImage: "",
  },
  {
    storeId: "oweijfweojfewoijf124214",
    menuId: "we;glkwgkgwgkw;;wegwgkwjlkwjg",
    menuName: "안 엄청난 음식",
    menuPrice: 12023400,
    menuOrigin: "북한산",
    menuIntroduce: "엄청나게 맛 없는 음식",
    menuImage: "",
  },
  {
    storeId: "oweijfweojfewoijf124214",
    menuId: "we;wgwe;;wegwgkwjlkwjg",
    menuName: "열라 엄청난 음식",
    menuPrice: 123400,
    menuOrigin: "태국산",
    menuIntroduce: "열나게 맛 없는 음식",
    menuImage: "",
  },
];

export default function storeReducer(state = initialState, action) {
  switch (action.type) {
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
