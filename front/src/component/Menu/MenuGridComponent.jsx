import React from "react";
import "./MenuStyle.css";
import MenuComponent from "./MenuComponent";

function MenuGridComponent() {
  return (
    <div id="menuList">
      <h2>메뉴 목록</h2>
      <div id="menuGrid">
        <MenuComponent />
        <MenuComponent />
        <MenuComponent />
        <MenuComponent />
        <MenuComponent />
        <MenuComponent />
        <MenuComponent /> <MenuComponent />
        <MenuComponent />
        <MenuComponent />
        <MenuComponent />
        <MenuComponent />
        <MenuComponent />
      </div>
    </div>
  );
}

export default MenuGridComponent;
