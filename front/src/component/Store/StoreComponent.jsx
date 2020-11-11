import React from "react";

function StoreComponent({ store }) {
  return (
    <div>
      <button>{store.storeName}</button>
    </div>
  );
}

export default StoreComponent;
