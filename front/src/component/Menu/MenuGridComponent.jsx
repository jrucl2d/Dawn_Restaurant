import React, { useState, useEffect, useRef } from "react";
import "./MenuStyle.css";
import MenuComponent from "./MenuComponent";
import { useSelector } from "react-redux";

import axios from "axios";

function MenuGridComponent({ storeId }) {
  const gridRef = useRef();
  const menus = useSelector((state) => state.menuReducer);
  const [editMode, setEditMode] = useState(false);
  const [selectedMenu, setSelectedMenu] = useState(null);

  useEffect(() => {
    (async () => {
      const menusResponse = await axios.get(`/stores/store/${storeId}/menus`);
      const menus = menusResponse.data;
      console.log(menus);
    })();
  }, []);

  useEffect(() => {
    if (editMode) {
      gridRef.current.classList.add("gridEditMode");
    } else {
      gridRef.current.classList.remove("gridEditMode");
    }
    const theIndex = menus.findIndex((v) => v.menuId === selectedMenu);
    gridRef.current.scrollTop = ((theIndex * window.innerHeight) / 100) * 35;
    // eslint-disable-next-line
  }, [editMode]);

  return (
    <div id="menuList">
      <h2>메뉴 목록</h2>
      <div className="menuGrid" ref={gridRef}>
        {menus.map((menu) => (
          <MenuComponent
            key={menu.menuId}
            menu={menu}
            editMode={editMode}
            setEditMode={setEditMode}
            selectedMenu={selectedMenu}
            setSelectedMenu={setSelectedMenu}
          />
        ))}
      </div>
    </div>
  );
}

export default MenuGridComponent;
