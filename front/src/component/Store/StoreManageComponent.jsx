import React, { useState } from "react";
import { Button } from "react-bootstrap";
import MenuGridComponent from "../Menu/MenuGridComponent";
import OrderListComponent from "../Order/OrderListComponent";
import StaffListComponent from "../Staff/StaffListComponent";
import MenuAddModalComponent from "../Menu/MenuAddModalComponent";

function StoreManageComponent({ location }) {
  const [showAddMenu, setShowAddMenu] = useState(false);

  const onClickAddMenu = () => {
    setShowAddMenu(true);
  };

  return (
    <div id="storeManage">
      <header>
        <h1 id="siteName">새벽 식당</h1>
      </header>
      <main id="storeMain">
        <div id="storeLeftSide">
          <div id="mainButtons">
            <Button onClick={onClickAddMenu}>메뉴 추가</Button>
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
        <MenuAddModalComponent
          showModal={showAddMenu}
          setShowModal={setShowAddMenu}
        />
      </main>
      <footer>
        <div>{decodeURIComponent(location.search.split("=")[1])}</div>
      </footer>
    </div>
  );
}

export default StoreManageComponent;
