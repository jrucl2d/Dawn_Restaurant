import React, { useEffect, useState } from "react";
import StoreStatisticGraphComponent from "./StoreStatisticGraphComponent";
import { useSelector } from "react-redux";

function StoreStatisticsComponent() {
  const orders = useSelector((state) => state.orderReducer);
  const [totalMoney, setTotalMoney] = useState(0);

  useEffect(() => {
    let theTotal = 0;
    orders.map((order) => {
      order.menus.forEach((v) => {
        theTotal += v.menuPrice * v.menuNum;
      });
    });
    setTotalMoney(theTotal);
  }, [orders, totalMoney]);

  return (
    <>
      <h1>매출 확인</h1>
      <div id="statisticsContainer">
        <div id="infos">
          <h2>금일 매출 : {totalMoney}원</h2>
          <h2>금일 판매 건수 : {orders.length}건</h2>
          <div id="orders">
            {orders.map((order) => {
              let theTotal = 0;
              order.menus.forEach((v) => {
                theTotal += v.menuPrice * v.menuNum;
              });
              return (
                <div className="order">
                  <span>
                    {order.menus[0].menuName} 외 {order.menus.length - 1}
                  </span>
                  <span>총 {theTotal} 원</span>
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
