import React, { useState, useEffect } from "react";
import { DropdownButton, Dropdown } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { deleteOrder, changeOrderStatus } from "../../modules/orderReducer";
import axios from "axios";

const orderStatusList = ["새 주문", "조리 중", "조리 완료", "음식 수령"];
const cssStatusList = ["danger", "success", "warning", "secondary"];

function OrderComponent({ order }) {
  const dispatch = useDispatch();
  const [showStatusList, setShowStatusList] = useState([
    "조리 중",
    "조리 완료",
    "음식 수령",
  ]);
  const [titleStatus, setTitleStatus] = useState(order.orderStatus);

  useEffect(() => {
    setShowStatusList(orderStatusList.filter((v) => v !== order.orderStatus));
  }, [order]);

  const onClickStatus = (e) => {
    const selectedStatus = e.target.innerHTML;
    (async () => {
      await axios.put("/order", {
        orderId: order.orderId,
        orderStatus: orderStatusList.findIndex((v) => v === selectedStatus),
      });

      dispatch(
        changeOrderStatus({
          orderId: order.orderId,
          orderStatus: selectedStatus,
        })
      );
      setShowStatusList(orderStatusList.filter((v) => v !== selectedStatus));
      setTitleStatus(selectedStatus);
    })();
  };

  const onClickDeleteOrder = () => {
    (async () => {
      await axios.put("/order", {
        orderId: order.orderId,
        orderStatus: 4,
      });
      dispatch(deleteOrder(order.orderId));
    })();
  };

  return (
    <div
      className={
        titleStatus === "새 주문" ? "orderCard highlightOrder" : "orderCard"
      }
    >
      <div id="foodInfo">
        {order.menus.map((menu) => (
          <div key={menu.menuId}>
            {menu.menuName} ({menu.menuNum}개)
          </div>
        ))}
      </div>
      <DropdownButton
        id="dropdown-basic-button"
        className="my-auto mx-auto"
        variant={`${
          cssStatusList[orderStatusList.findIndex((v) => v === titleStatus)]
        }`}
        title={titleStatus}
      >
        <Dropdown.Item as="button" onClick={onClickStatus}>
          {showStatusList[0]}
        </Dropdown.Item>
        <Dropdown.Item as="button" onClick={onClickStatus}>
          {showStatusList[1]}
        </Dropdown.Item>
        <Dropdown.Item as="button" onClick={onClickStatus}>
          {showStatusList[2]}
        </Dropdown.Item>
      </DropdownButton>
      {titleStatus === "음식 수령" ? (
        <button className="deleteOrderBtn" onClick={onClickDeleteOrder}>
          X
        </button>
      ) : null}
    </div>
  );
}

export default OrderComponent;
