import React, { useEffect } from "react";
import OrderComponent from "./OrderComponent";
import "./OrderStyle.css";
import { useSelector, useDispatch } from "react-redux";
import axios from "axios";
import { initialOrder } from "../../modules/orderReducer";

function OrderListComponent({ storeId }) {
  const orders = useSelector((state) => state.orderReducer);
  const dispatch = useDispatch();

  useEffect(() => {
    (async () => {
      const { data } = await axios.get(`/stores/store/${storeId}/sales`);
      const theOrders = data.result.orders;
      const forSet = [];
      theOrders.forEach((order) => {
        forSet.push({
          orderId: order.orderId,
          orderStatus: "새 주문", // 상태가 '음식 수령'인 것들은 제외하고 넣어라...!!!!!!!!!!!!
          total: order.totalPrice,
          menus: order.menusOrders.map((v) => {
            return {
              menuId: v.menu.menuId,
              menuName: v.menu.menuTitle,
              menuPrice: v.menu.price,
              menuNum: v.quantity,
            };
          }),
        });
      });
      dispatch(initialOrder(forSet));
    })();
  }, []);

  return (
    <div id="orderList">
      <h2>주문 현황</h2>
      {orders.map((order) => (
        <OrderComponent key={order.orderId} order={order} />
      ))}
    </div>
  );
}

export default OrderListComponent;
