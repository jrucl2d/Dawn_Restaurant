import React, { useRef } from "react";
import MenuModifyComponent from "./MenuModifyComponent";

function MenuComponent({
  menu,
  editMode,
  setEditMode,
  selectedMenu,
  setSelectedMenu,
}) {
  const menuRef = useRef(null);
  const onClickMenu = () => {
    menuRef.current.classList.add("menuEditMode");
    setEditMode(!editMode);
    setSelectedMenu(menu.menuId);
  };
  const deMenuEditMode = () => {
    setEditMode(false);
    setSelectedMenu(null);
    menuRef.current && menuRef.current.classList.remove("menuEditMode");
  };
  return (
    <button
      className="menuGridItem"
      onClick={onClickMenu}
      ref={menuRef}
      disabled={editMode}
    >
      {editMode && menu.menuId === selectedMenu && (
        <div>
          <MenuModifyComponent menu={menu} deMenuEditMode={deMenuEditMode} />
        </div>
      )}
      {menu.menuId !== selectedMenu && (
        <>
          {menu.menuImage === "" ? (
            <div id="menuNoImage">메뉴 이미지가 없습니다</div>
          ) : (
            <img src="#" alt="음식사진" />
          )}
          <h5>{menu.menuName}</h5>
        </>
      )}
    </button>
  );
}

export default MenuComponent;
