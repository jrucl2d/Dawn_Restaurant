import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import axios from "axios";
import { setOrderId } from "../../modules/orderReducer";

const Payment = ({ history }) => {
  const dispatch = useDispatch();
  const orders = useSelector((state) => state.orderReducer);
  const userInfo = useSelector((state) => state.userReducer);
  const [isCard, setIsCard] = useState(false);
  const [info, setInfo] = useState({
    num: 0,
    pw: "",
  });

  const onClickPay = (e) => {
    e.preventDefault();
    if (!orders[0]) {
      return;
    }
    const menuData = [];
    Object.entries(orders[0]).forEach((v) => {
      if (v[0] !== "totalMoney" && v[0] !== "storeId") {
        menuData.push({
          menuId: +v[0].split("-")[0],
          quantity: v[1],
        });
      }
    });
    const sendingData = {
      storeId: orders[0].storeId,
      userId: userInfo.userId,
      menusOrders: menuData,
    };
    (async () => {
      try {
        if (isCard) {
          await axios.post("/payment", {
            cardId: info.num,
            cardPassword: info.pw,
          });
        }

        const result = await axios.post("/stores/orders", sendingData);
        dispatch(setOrderId(result.data.result.orderId));
        alert("결제에 성공했습니다.");
        history.push("/Finish");
      } catch (err) {
        console.log(err.message);
        alert("결제에 실패했습니다.");
        return;
      }
    })();
  };
  const onChangeInfo = (e) => {
    setInfo({
      ...info,
      [e.target.name]: e.target.value,
    });
  };
  return (
    <div className="auth-wrapper">
      <div className="auth-inner">
        <div>
          <div className="isCard">
            <span>주문 진행</span>
            <span className="selectIsCard">
              <label htmlFor="noCard">현금</label>
              <input
                type="radio"
                id="noCard"
                checked={!isCard && true}
                name="isCard"
                onClick={() => setIsCard(false)}
              />
              <label htmlFor="Card">카드</label>
              <input
                type="radio"
                id="Card"
                name="isCard"
                checked={isCard && true}
                onClick={() => setIsCard(true)}
              />
            </span>
          </div>
          <br />
          {isCard ? (
            <>
              <div className="form-group">
                <input
                  type="number"
                  name="num"
                  className="form-control"
                  placeholder="카드 번호"
                  onChange={onChangeInfo}
                  value={info.num}
                />
              </div>
              <div className="form-group">
                <input
                  type="password"
                  name="pw"
                  className="form-control"
                  placeholder="카드 비밀번호"
                  onChange={onChangeInfo}
                  value={info.pw}
                />
              </div>
              <hr />
              <div> 합계 {orders[0] && orders[0].totalMoney}원</div>
            </>
          ) : (
            <>
              <h5>카운터에서 현금으로 결제해 주세요</h5>
              <div> 합계 {orders[0] && orders[0].totalMoney}원</div>
            </>
          )}
          &nbsp;
          <button
            type="submit"
            className="btn btn-primary btn-block"
            varient="secondary"
            onClick={onClickPay}
          >
            결제하기
          </button>
          &nbsp;
          <Link to="/order-list">
            <button
              type="submit"
              className="btn btn-danger btn-block"
              varient="secondary"
              onClick={() => alert("결제를 취소하시겠습니까?")}
            >
              주문취소
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Payment;
