import React from "react";
import { Route } from "react-router-dom";
import StoreListComponent from "../component/Store/StoreListComponent";
import StoreManageComponent from "../component/Store/StoreManageComponent";
import StoreStatisticsComponent from "../component/Store/StoreStatisticsComponent";
import Login from "../component/Client/Login";
import SignUp from "../component/Client/Signup";
import MenuList from "../component/Client/MenuList";
import OrderList from "../component/Client/OrderList";
import Payment from "../component/Client/Payment";
import Finish from "../component/Client/Finish";

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
      {/* 클라이언트 경로 */}
      <Route exact path='/' component={Login} />
      <Route path="/sign-up" component={SignUp} />
      <Route path="/menu-list" component={MenuList} />
      <Route path="/order-list" component={OrderList} />          
      <Route path="/payment" component={Payment} />
      <Route path="/finish" component={Finish} />
    </>
  );
}

export default IndexRouter;
