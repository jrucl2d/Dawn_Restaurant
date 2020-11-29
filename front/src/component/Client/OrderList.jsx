import React from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

const OrderList = () => {
  const orders = useSelector((state) => state.orderReducer);

  console.log(orders);
  return (
    <div className="auth-wrapper">
      <div className="auth-inner">
        <div>
          <div>주문 정보</div>
          <hr />
          <ul className="orderCalcListUL">
            {orders[0] &&
              Object.entries(orders[0]).map(
                (order) =>
                  order[0] !== "totalMoney" &&
                  order[0] !== "storeId" && (
                    <li key={order[0]} className="orderCalcList">
                      <span>{order[0].split("-")[1]}</span>
                      <span>{order[1]}개 </span>
                    </li>
                  )
              )}
          </ul>
          <hr />
          <div> 합계 {orders[0] && orders[0].totalMoney}원</div>
          <Link to="/payment">
            <button
              type="submit"
              className="btn btn-primary btn-block"
              varient="secondary"
              onClick={() => alert("결제하시겠습니까?")}
            >
              결제하기
            </button>
          </Link>
          &nbsp;
          <Link to="menu-list">
            <button
              type="submit"
              className="btn btn-danger btn-block"
              varient="secondary"
              onClick={() => alert("메뉴창으로 돌아갑니다.")}
            >
              주문 취소
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default OrderList;
