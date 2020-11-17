import React from "react";
import { Button } from "react-bootstrap";
import MenuComponent from "../Menu/MenuComponent";
import OrderComponent from "../Order/OrderComponent";
import StaffComponent from "../Staff/StaffComponent";

function StoreManageComponent({ location }) {
  console.log(location);
  return (
    <div id="storeManage">
      <header>
        <h1 id="siteName">새벽 식당</h1>
      </header>
      <main id="storeMain">
        <div id="storeLeftSide">
          <Button>메뉴 추가</Button>
          <Button>직원 추가</Button>
          <Button>매출 통계</Button>
          <StaffComponent />
        </div>
        <div id="storeMiddleSide">
          <MenuComponent />
        </div>
        <div id="storeRightSide">
          <OrderComponent />
        </div>
      </main>
      <footer>{decodeURIComponent(location.search.split("=")[1])}</footer>
    </div>
  );
}

export default StoreManageComponent;
