import React, { useState } from "react";
import { DropdownButton, Dropdown } from "react-bootstrap";

function OrderComponent() {
  const [foods, setFoods] = useState([
    {
      foodId: "!235132525325",
      foodName: "음식1",
      foodNum: 4,
    },
    {
      foodId: "2351524twe",
      foodName: "음식3",
      foodNum: 2,
    },
  ]);
  return (
    <div id="orderCard">
      <div id="foodInfo">
        {foods.map((food) => (
          <div key={food.foodId}>
            {food.foodName} ({food.foodNum}개)
          </div>
        ))}
      </div>
      <DropdownButton
        id="dropdown-basic-button"
        className="my-auto mx-auto"
        title="새주문"
      >
        <Dropdown.Item as="button">조리중</Dropdown.Item>
        <Dropdown.Item as="button">조리 완료</Dropdown.Item>
        <Dropdown.Item as="button">음식 수령</Dropdown.Item>
      </DropdownButton>
    </div>
  );
}

export default OrderComponent;
