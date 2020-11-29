import React from "react";
import { useSelector } from "react-redux";

const Finish = () => {
  const orderId = useSelector((state) => state.orderReducer);
  return (
    <div className="auth-wrapper">
      <div className="auth-inner">
        <div className="finish">
          <div className="finishInfo">
            <div>
              <div>주문이 정상적으로 완료되었습니다.</div>
              <img
                src="https://static.thenounproject.com/png/35367-200.png"
                className="finishImage"
                alt="사진"
              ></img>
              <div>내 주문 번호: {orderId}</div>
              <div>예상 소요시간: 15분</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Finish;
