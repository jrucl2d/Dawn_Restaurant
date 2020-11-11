import React from "react";
import { Route } from "react-router-dom";
import StoreListComponent from "../component/Store/StoreListComponent";

function IndexRouter() {
  return (
    <div>
      {/* 점포 리스트 확인 및 추가 화면 */}
      <Route path="/store" exact component={StoreListComponent} />
    </div>
  );
}

export default IndexRouter;
