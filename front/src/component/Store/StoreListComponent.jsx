import React, { useState } from "react";
import StoreRankGraphComponent from "./StoreRankGraphComponent";
import StoreComponent from "./StoreComponent";
import "./StoreStyle.css";

function StoreListComponent() {
  // useEffect로 처음에 점포 목록 axios로 가져오기
  const [stores, setStores] = useState([
    {
      storeName: "바보 파스타 한국점",
      storeId: "sdlkfjasdlkf",
    },
    {
      storeName: "멍청이 피자 프랑스점",
      storeId: "weoigjweogjwdljfw",
    },
  ]);
  return (
    <div>
      <header>
        <h1 id="siteName">새벽 식당</h1>
      </header>
      <div id="storeListComponent">
        <StoreRankGraphComponent />
        <div id="storeList">
          {stores.map((store) => (
            <StoreComponent key={store.storeId} store={store} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default StoreListComponent;
