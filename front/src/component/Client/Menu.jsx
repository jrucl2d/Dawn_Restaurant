import React from "react";

const Menu = ({ menu }) => {
  return (
    <>
      <img
        src={"https://storage.googleapis.com/sogong/" + menu.imageURL}
        className="imageURL foodImageForClient"
        alt="음식 사진"
      />
      <div className="menuTitle">{menu.menuTitle} </div>
      <div className="price">{menu.price}원</div>
    </>
  );
};

export default Menu;
