import React, { useState, useEffect } from "react";
import { Button } from "react-bootstrap";
import MenuGridComponent from "../Menu/MenuGridComponent";
import OrderListComponent from "../Order/OrderListComponent";
import StaffListComponent from "../Staff/StaffListComponent";
import MenuAddModalComponent from "../Menu/MenuAddModalComponent";
import StaffAddModalComponent from "../Staff/StaffAddModalComponent";

function StoreManageComponent({ location }) {
  const [showAddMenu, setShowAddMenu] = useState(false);
  const [showAddStaff, setShowAddStaff] = useState(false);
  const [menus, setMenus] = useState([
    {
      menuId: "lfjwelfewjfklewjglkjwe",
      menuName: "엄청난 음식",
      menuPrice: 12000,
      menuOrigin: "한국산",
      menuIntroduce: "엄청나게 맛난 음식",
      menuImage: "",
    },
  ]);

  useEffect(() => {
    console.log(menus);
  }, [menus]);

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
          menus={menus}
          setMenus={setMenus}
        />
        <StaffAddModalComponent
          showModal={showAddStaff}
          setShowModal={setShowAddStaff}
        />
      </main>
      <footer>
        <div>{decodeURIComponent(location.search.split("=")[1])}</div>
      </footer>
    </div>
  );
}

export default StoreManageComponent;
