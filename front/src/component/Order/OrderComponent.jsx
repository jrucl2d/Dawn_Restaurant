import React, { useState, useEffect } from "react";
import { DropdownButton, Dropdown } from "react-bootstrap";

const orderStatusList = ["새 주문", "조리 중", "조리 완료", "음식 수령"];
const cssStatusList = ["danger", "success", "warning", "secondary"];

function OrderComponent({ order }) {
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
    setShowStatusList(orderStatusList.filter((v) => v !== selectedStatus));
    setTitleStatus(selectedStatus);
  };

  return (
    <div id="orderCard">
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
    </div>
  );
}

export default OrderComponent;
