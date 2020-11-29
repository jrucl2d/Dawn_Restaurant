import React, { useState, useEffect } from "react";
import axios from "axios";
import Menu from "./Menu";
import { useDispatch } from "react-redux";
import { clientOrder } from "../../modules/orderReducer";

const storeID = 2;

// 메뉴 컴포넌트를 버튼으로 만들어서 누를 때마다 각자 아이디로 카운트
const MenuList = ({ history }) => {
  const dispatch = useDispatch();
  const [menus, setMenus] = useState([]);
  const [quantity, setQuantity] = useState({});
  const [total, setTotal] = useState(0);

  useEffect(() => {
    (async () => {
      const result = await axios.get(`/stores/store/${storeID}/menus`);
      setMenus(result.data);
    })();
  }, []);

  const onClickMenu = (e) => {
    if (e.target.name === undefined) {
      return;
    }
    setTotal(total + +e.target.name.split("-")[2]);
    setQuantity({
      ...quantity,
      [e.target.name]:
        typeof quantity[e.target.name] === "number"
          ? quantity[e.target.name] + 1
          : 1,
    });
  };
  const onClickCancel = () => {
    alert("선택이 취소되었습니다!");
    setQuantity({});
  };
  const onClickGo = (e) => {
    e.preventDefault();
    quantity["totalMoney"] = total;
    quantity["storeId"] = storeID;
    dispatch(clientOrder(quantity));
    history.push("/order-list");
  };

  return (
    <div className="auth-wrapper">
      <div className="auth-inner">
        <div className="menu">Menu List</div>
        <div className="outerBox">
          <div className="MenuBox">
            {menus.map((menu) => (
              <button
                className="Menu"
                key={menu.menuId}
                onClick={onClickMenu}
                name={`${menu.menuId}-${menu.menuTitle}-${menu.price}`}
              >
                <Menu menu={menu} />
              </button>
            ))}
          </div>
        </div>
        <div className="selectedFoods">
          {Object.entries(quantity).map((theMenu) => (
            <span key={theMenu[0]}>
              {theMenu[0].split("-")[1]}:{theMenu[1]}개/
            </span>
          ))}
        </div>
        <button
          className="btn btn-primary btn-block"
          varient="secondary"
          onClick={onClickGo}
        >
          {total !== 0 && `총 ${total}원`} 주문하기
        </button>
        &nbsp;
        <button
          type="submit"
          className="btn btn-danger btn-block"
          varient="secondary"
          onClick={onClickCancel}
        >
          선택 취소
        </button>
      </div>
    </div>
  );
};

export default MenuList;
