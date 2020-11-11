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
          <div className="container">
            <h2>보유 점포</h2>
            {stores.map((store) => (
              <div className="card">
                <StoreComponent key={store.storeId} store={store} />
              </div>
            ))}
          </div>
          <div className="buttons">
            <button type="button" class="btn btn-primary">
              점포 추가
            </button>
            <button type="button" class="btn btn-danger">
              점포 삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default StoreListComponent;
