import React, { useRef } from "react";
import { Link } from "react-router-dom";

function StoreComponent({
  storeInfo,
  deleteMode,
  deleteSelected,
  setDeleteSelected,
}) {
  const selectedRef = useRef(null);
  const onClickDeleteSelect = (e) => {
    e.preventDefault();
    // 이미 있었으면 삭제 리스트에서 빼기
    if (deleteSelected.findIndex((v) => v === e.target.name) !== -1) {
      setDeleteSelected(deleteSelected.filter((v) => v !== e.target.name));
      selectedRef.current.className = "card-body";
    } else {
      setDeleteSelected([...deleteSelected, e.target.name]);
      selectedRef.current.className = "card-body deleteSelected";
    }
  };

  return (
    <>
      {deleteMode ? (
        <button
          onClick={onClickDeleteSelect}
          className="card-body"
          ref={selectedRef}
          name={storeInfo.storeId}
        >
          <span>{storeInfo.storeName}</span>
        </button>
      ) : (
        <Link to="#" className="card-body">
          <span>{storeInfo.storeName}</span>
        </Link>
      )}
    </>
  );
}

export default StoreComponent;
