import React, { useState, useRef } from "react";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
import MenuGridComponent from "../Menu/MenuGridComponent";
import OrderListComponent from "../Order/OrderListComponent";
import StaffListComponent from "../Staff/StaffListComponent";
import MenuAddModalComponent from "../Menu/MenuAddModalComponent";
import StaffAddModalComponent from "../Staff/StaffAddModalComponent";

function StoreManageComponent({ location }) {
  const storeId = useRef(location.pathname.split("/")[2]);
  const [showAddMenu, setShowAddMenu] = useState(false);
  const [showAddStaff, setShowAddStaff] = useState(false);

  const onClickAddMenu = () => {
    setShowAddMenu(true);
  };
  const onClickAddStaff = () => {
    setShowAddStaff(true);
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
            <Button onClick={onClickAddStaff}>직원 추가</Button>
            <Link to={`/store/${storeId.current}/statistics`}>
              <Button>매출 통계</Button>
            </Link>
          </div>
          <StaffListComponent storeId={storeId.current} />
        </div>
        <div id="storeMiddleSide">
          <MenuGridComponent storeId={storeId.current} location />
        </div>
        <div id="storeRightSide">
          <OrderListComponent />
        </div>
        <MenuAddModalComponent
          showModal={showAddMenu}
          setShowModal={setShowAddMenu}
          storeId={storeId.current}
        />
        <StaffAddModalComponent
          showModal={showAddStaff}
          setShowModal={setShowAddStaff}
          storeId={storeId.current}
        />
      </main>
      <footer>
        <div>{decodeURIComponent(location.search.split("=")[1])}</div>
      </footer>
    </div>
  );
}

export default StoreManageComponent;
