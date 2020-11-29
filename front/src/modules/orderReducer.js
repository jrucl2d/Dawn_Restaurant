// 액션 생성 함수
export const initialOrder = (orders) => {
  return { type: "INITIAL_ORDER", orders };
};
export const addOrder = (newOrder) => {
  return { type: "ADD_ORDER", newOrder };
};
export const deleteOrder = (orderId) => {
  return { type: "DELETE_ORDER", orderId };
};
export const changeOrderStatus = ({ orderId, orderStatus }) => {
  return { type: "CHANGE_ORDER_STATUS", orderId, orderStatus };
};
export const clientOrder = (order) => {
  return { type: "CLIENT_ORDER", order };
};
export const setOrderId = (orderId) => {
  return { type: "SET_ORDERID", orderId };
};

// Initial State
const initialState = [
  // {
  //   orderId: "185jdfh2je98u344kjngkj",
  //   orderStatus: "새 주문",
  //   menus: [
  //     {
  //       menuId: "3werh513he2525325",
  //       menuName: "음식10",
  //       menuPrice: 120,
  //       menuNum: 35,
  //     },
  //     {
  //       menuId: "2351524twe",
  //       menuName: "음식3",
  //       menuPrice: 3100,
  //       menuNum: 30,
  //     },
  //   ],
  // },
];

export default function orderReducer(state = initialState, action) {
  switch (action.type) {
    case "INITIAL_ORDER":
      return action.orders;
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
    case "CLIENT_ORDER":
      return [action.order];
    case "SET_ORDERID":
      return action.orderId;
    default:
      return state;
  }
}
