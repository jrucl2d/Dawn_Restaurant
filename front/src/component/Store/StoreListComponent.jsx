import React, { useState, useRef, useEffect } from "react";
import StoreRankGraphComponent from "./StoreRankGraphComponent";
import StoreComponent from "./StoreComponent";
import StoreAddModalComponent from "./StoreAddModalComponent";
import { useSelector, useDispatch } from "react-redux";
import { deleteStore } from "../../modules/storeReducer";

import "./StoreStyle.css";

function StoreListComponent() {
  const dispatch = useDispatch();
  const stores = useSelector((state) => state.storeReducer);
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
      dispatch(deleteStore(deleteSelected));

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
          <div className="storeButtons">
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
        />
      </div>
    </div>
  );
}

export default StoreListComponent;
