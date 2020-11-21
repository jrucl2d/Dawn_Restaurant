import React from "react";
import { Route } from "react-router-dom";
import StoreListComponent from "../component/Store/StoreListComponent";
import StoreManageComponent from "../component/Store/StoreManageComponent";
import StoreStatisticsComponent from "../component/Store/StoreStatisticsComponent";

function IndexRouter() {
  return (
    <>
      {/* 점포 리스트 확인 및 추가 화면 */}
      <Route path="/store" exact component={StoreListComponent} />
      <Route path="/store/:id" exact component={StoreManageComponent} />
      <Route
        path="/store/:id/statistics"
        exact
        component={StoreStatisticsComponent}
      />
    </>
  );
}

export default IndexRouter;
