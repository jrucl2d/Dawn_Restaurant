import React from "react";
import { Button } from "react-bootstrap";

function StoreManageComponent({ location }) {
  console.log(location);
  return (
    <div id="storeManage">
      <header>
        <h1 id="siteName">새벽 식당</h1>
      </header>
      <main>
        <Button>메뉴 추가</Button>
      </main>
      <footer>{decodeURIComponent(location.search.split("=")[1])}</footer>
    </div>
  );
}

export default StoreManageComponent;
