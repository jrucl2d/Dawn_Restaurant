import React from "react";
import { Link } from "react-router-dom";

function StoreComponent({ store }) {
  return (
    <Link className="card-body">
      <span>{store.storeName}</span>
    </Link>
  );
}

export default StoreComponent;
