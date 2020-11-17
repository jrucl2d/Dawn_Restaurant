import React from "react";
import OrderComponent from "./OrderComponent";
import "./OrderStyle.css";

function OrderListComponent() {
  return (
    <div id="orderList">
      <h2>주문 현황</h2>
      <OrderComponent />
      <OrderComponent />
      <OrderComponent />
      <OrderComponent />
      <OrderComponent />
      <OrderComponent />
    </div>
  );
}

export default OrderListComponent;
