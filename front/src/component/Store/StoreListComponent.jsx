import React, { useState, useRef, useEffect } from "react";
import StoreRankGraphComponent from "./StoreRankGraphComponent";
import StoreComponent from "./StoreComponent";
import StoreAddModalComponent from "./StoreAddModalComponent";

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
  const [showAddStore, setShowAddStore] = useState(false);

  const addBtnRef = useRef(null);
  const deleteBtnRef = useRef(null);

  useEffect(() => {
    if (stores.length === 0) {
      deleteBtnRef.current.disabled = true;
    } else {
      deleteBtnRef.current.disabled = false;
    }
  }, [stores]);

  const onClickAdd = () => setShowAddStore(true);

  const onClickDelete = () => {
    if (!deleteMode) {
      addBtnRef.current.disabled = true;
      deleteBtnRef.current.className = "btn btn-danger deleting";
    } else {
      addBtnRef.current.disabled = false;
      if (deleteSelected.length === 0) {
        setDeleteMode(!deleteMode);
        deleteBtnRef.current.className = "btn btn-warning notDeleting";
        return;
      }
      const answer = window.confirm("정말로 삭제하시겠습니까?");
      if (!answer) {
        return;
      }
      setStores(
        // eslint-disable-next-line array-callback-return
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
            <button
              type="button"
              className="btn btn-primary"
              onClick={onClickAdd}
              ref={addBtnRef}
            >
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
        <StoreAddModalComponent
          showAddStore={showAddStore}
          setShowAddStore={setShowAddStore}
          setStores={setStores}
          stores={stores}
        />
      </div>
    </div>
  );
}

export default StoreListComponent;
