import React, { useState, useEffect, useRef } from "react";
import "./MenuStyle.css";
import MenuComponent from "./MenuComponent";
import { useSelector, useDispatch } from "react-redux";
import { initailMenu } from "../../modules/menuReducer";

import axios from "axios";

function MenuGridComponent({ storeId }) {
  const dispatch = useDispatch();
  const gridRef = useRef();
  const menus = useSelector((state) => state.menuReducer);
  const [editMode, setEditMode] = useState(false);
  const [selectedMenu, setSelectedMenu] = useState(null);

  useEffect(() => {
    (async () => {
      const menusResponse = await axios.get(`/stores/store/${storeId}/menus`);
      const menus = menusResponse.data;
      const theMenus = menus.map((menu) => {
        return {
          storeId: menu.storeId,
          menuId: menu.menuId,
          menuName: menu.menuTitle,
          menuPrice: menu.price,
          menuIntroduce: menu.menuDescription ? menu.menuDescription : "",
          menuImage: menu.imageURL ? menu.imageURL : "",
        };
      });
      dispatch(initailMenu(theMenus));
    })();
    // eslint-disable-next-line
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
