import React from "react";
import OrderComponent from "./OrderComponent";
import "./OrderStyle.css";
import { useSelector } from "react-redux";

function OrderListComponent() {
  const orders = useSelector((state) => state.orderReducer);
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
