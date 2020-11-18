import React from "react";

function MenuComponent({ menu }) {
  return (
    <button id="menuGridItem">
      {menu.menuImage === "" ? (
        <div id="menuNoImage">메뉴 이미지가 없습니다</div>
      ) : (
        <img src="#" alt="음식사진" />
      )}

      <h5>{menu.menuName}</h5>
    </button>
  );
}

export default MenuComponent;
