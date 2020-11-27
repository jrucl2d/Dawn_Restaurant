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
          orderStatus: order.orderStatus,
          total: order.totalPrice,
          timestamp: `${order.createdAt[0]}-${order.createdAt[1]}-${
            +order.createdAt[3] + 9 >= 24
              ? +order.createdAt[2] + 1
              : order.createdAt[2]
          }`,
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
      console.log(forSet);
      dispatch(initialOrder(forSet));
    })();
  }, []);

  return (
    <div id="orderList">
      <h2>주문 현황</h2>
      {orders.map(
        (order) =>
          order.orderStatus !== "판매 완료" && (
            <OrderComponent key={order.orderId} order={order} />
          )
      )}
    </div>
  );
}

export default OrderListComponent;
