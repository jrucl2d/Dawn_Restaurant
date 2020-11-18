import React from "react";
import "./MenuStyle.css";
import MenuComponent from "./MenuComponent";
import { useSelector } from "react-redux";

function MenuGridComponent() {
  const menus = useSelector((state) => state.menuReducer);
  return (
    <div id="menuList">
      <h2>메뉴 목록</h2>
      <div id="menuGrid">
        {menus.map((menu) => (
          <MenuComponent key={menu.menuId} menu={menu} />
        ))}
      </div>
    </div>
  );
}

export default MenuGridComponent;
