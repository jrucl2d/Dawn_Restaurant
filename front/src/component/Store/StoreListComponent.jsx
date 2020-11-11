import React, { useState, useRef } from "react";
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
  const [deleteMode, setDeleteMode] = useState(false);
  const [deleteSelected, setDeleteSelected] = useState([]);

  const deleteBtnRef = useRef(null);

  const onClickDelete = (e) => {
    e.preventDefault();
    if (!deleteMode) {
      deleteBtnRef.current.className = "btn btn-danger deleting";
    } else {
      alert("zzz");
      setStores(
        stores.filter((store) => {
          if (deleteSelected.findIndex((v) => v === store.storeId) === -1) {
            return store;
          }
        })
      );
      deleteBtnRef.current.className = "btn btn-warning notDeleting";
    }
    setDeleteMode(!deleteMode);
    setDeleteSelected([]);
  };

  return (
    <div>
      <header>
        <h1 id="siteName">새벽 식당</h1>
      </header>
      <div id="storeListComponent">
        <StoreRankGraphComponent />
        <div id="storeList">
          <div className="container">
            <h2>
              {deleteMode ? "삭제할 점포를 모두 선택하세요." : "보유 점포"}
            </h2>
            {stores.map((storeInfo) => (
              <div className="card" key={storeInfo.storeId}>
                <StoreComponent
                  storeInfo={storeInfo}
                  deleteMode={deleteMode ? true : false}
                  deleteSelected={deleteSelected}
                  setDeleteSelected={setDeleteSelected}
                />
              </div>
            ))}
          </div>
          <div className="buttons">
            <button type="button" className="btn btn-primary">
              점포 추가
            </button>
            <button
              type="button"
              className="btn btn-warning notDeleting"
              ref={deleteBtnRef}
              onClick={onClickDelete}
            >
              {deleteMode ? "삭제하기" : "점포 삭제"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default StoreListComponent;
