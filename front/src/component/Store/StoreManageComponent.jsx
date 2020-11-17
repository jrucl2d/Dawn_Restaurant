import React from "react";
import { Button } from "react-bootstrap";
import MenuGridComponent from "../Menu/MenuGridComponent";
import OrderListComponent from "../Order/OrderListComponent";
import StaffListComponent from "../Staff/StaffListComponent";

function StoreManageComponent({ location }) {
  return (
    <div id="storeManage">
      <header>
        <h1 id="siteName">새벽 식당</h1>
      </header>
      <main id="storeMain">
        <div id="storeLeftSide">
          <div id="mainButtons">
            <Button>메뉴 추가</Button>
            <Button>직원 추가</Button>
            <Button>매출 통계</Button>
          </div>
          <StaffListComponent />
        </div>
        <div id="storeMiddleSide">
          <MenuGridComponent />
        </div>
        <div id="storeRightSide">
          <OrderListComponent />
        </div>
      </main>
      <footer>
        <div>{decodeURIComponent(location.search.split("=")[1])}</div>
      </footer>
    </div>
  );
}

export default StoreManageComponent;
