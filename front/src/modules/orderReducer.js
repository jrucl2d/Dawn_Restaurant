// 액션 생성 함수
export const addOrder = (newOrder) => {
  return { type: "ADD_ORDER", newOrder };
};
export const deleteOrder = (orderId) => {
  return { type: "DELETE_ORDER", orderId };
};
export const changeOrderStatus = ({ orderId, orderStatus }) => {
  return { type: "CHANGE_ORDER_STATUS", orderId, orderStatus };
};

// Initial State
const initialState = [
  {
    orderId: "185jdfh2je98u344kjngkj",
    orderStatus: "새 주문",
    menus: [
      {
        menuId: "3werh513he2525325",
        menuName: "음식10",
        menuPrice: 120,
        menuNum: 35,
      },
      {
        menuId: "2351524twe",
        menuName: "음식3",
        menuPrice: 3100,
        menuNum: 30,
      },
    ],
  },
  {
    orderId: "185jdfhgkjere98u344kjngkj",
    orderStatus: "조리 중",
    menus: [
      {
        menuId: "!235132525325",
        menuName: "음식1",
        menuPrice: 2000,
        menuNum: 4,
      },
      {
        menuId: "2351524twe",
        menuName: "음식3",
        menuPrice: 3100,
        menuNum: 2,
      },
      {
        menuId: "!235132233525325",
        menuName: "음식4",
        menuPrice: 3100,
        menuNum: 3,
      },
    ],
  },
  {
    orderId: "lwekgjelwg03o1ilkregoiqwe",
    orderStatus: "조리 완료",
    menus: [
      {
        menuId: "!gwlj345190weg",
        menuName: "음식2",
        menuPrice: 5000,
        menuNum: 1,
      },
      {
        menuId: "2351524twe",
        menuName: "음식3",
        menuPrice: 3100,
        menuNum: 23,
      },
    ],
  },
  {
    orderId: "lwekgjel33wewg03o1ilkregoiqwe",
    orderStatus: "음식 수령",
    menus: [
      {
        menuId: "!gwlj345190weg",
        menuName: "음식2",
        menuPrice: 5000,
        menuNum: 3,
      },
      {
        menuId: "2351524tㅈㄷㄹㅈㄷwe",
        menuName: "음식5",
        menuPrice: 31020,
        menuNum: 3,
      },
    ],
  },
];

export default function orderReducer(state = initialState, action) {
  switch (action.type) {
    case "ADD_ORDER":
      return [...state, action.newOrder];
    case "DELETE_ORDER":
      return state.filter((order) => order.orderId !== action.orderId);
    case "CHANGE_ORDER_STATUS":
      // eslint-disable-next-line
      return state.map((order) => {
        if (order.orderId === action.orderId) {
          return {
            ...order,
            menus: [...order.menus],
            orderStatus: action.orderStatus,
          };
        } else {
          return order;
        }
      });
    default:
      return state;
  }
}
