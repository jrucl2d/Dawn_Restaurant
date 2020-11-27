import React, { useEffect, useState } from "react";
import StoreStatisticGraphComponent from "./StoreStatisticGraphComponent";
import { useSelector } from "react-redux";

function StoreStatisticsComponent() {
  const orders = useSelector((state) => state.orderReducer);
  const [totalMoney, setTotalMoney] = useState(0);

  useEffect(() => {
    let theTotal = 0;
    orders.map((order) => {
      // eslint-disable-next-line
      theTotal += order.total;
    });
    setTotalMoney(theTotal);
  }, [orders, totalMoney]); // 상태가 '음식 수령'인 것만 여기에 표시

  return (
    <>
      <h1>매출 확인</h1>
      <div id="statisticsContainer">
        <div id="infos">
          <h2>금일 매출 : {totalMoney}원</h2>
          <h2>금일 판매 건수 : {orders.length}건</h2>
          <div id="orders">
            {orders.map((order) => {
              return (
                <div className="order" key={order.orderId}>
                  <span>
                    {order.menus[0].menuName} 외 {order.menus.length - 1}
                  </span>
                  <span>총 {order.total} 원</span>
                </div>
              );
            })}
          </div>
        </div>
        <StoreStatisticGraphComponent />
      </div>
    </>
  );
}

export default StoreStatisticsComponent;
