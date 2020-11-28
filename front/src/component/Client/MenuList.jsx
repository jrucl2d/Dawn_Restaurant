import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom'; 
import axios from 'axios';
import Menu from './Menu';

const storeID = 1;

// 메뉴 컴포넌트를 버튼으로 만들어서 누를 때마다 각자 아이디로 카운트
const MenuList = ()=>{
    const [menus, setMenus] = useState([]);
    const [menuNum, setMenuNum] = useState({});
    // setMenuNum({...menuNum, menuId : menuNum.menuId + 1})
    
    useEffect(()=> {
      ( async () => { 
        const result = await axios.get(`/stores/store/${storeID}/menus`);
        console.log(result);
        setMenus(result.data);
      }
      )();
    }, []);
    
    return (
      <div className="auth-wrapper">
      <div className="auth-inner"> 
      <div className="menu">Menu List</div>
          {/* {menus.map(menu => <div>{menu.menuTitle}</div>)} */}
        <div className="MenuBox">
          <button className="Menu"><Menu /></button>
          <button className="Menu"><Menu /></button>
          <button className="Menu"><Menu /></button>

          <button className="Menu"><Menu /></button>
          <button className="Menu"><Menu /></button>
          <button className="Menu"><Menu /></button>

        </div>
        &nbsp;
          <Link to='/order-list'>
            <button type="submit" className="btn btn-primary btn-block" varient="secondary">
              주문하기
            </button>
          </Link>
          &nbsp;
          <Link to='menu-list'>
            <button type="submit" className="btn btn-danger btn-block" varient="secondary" onClick={() => alert("선택이 취소되었습니다!")}>
              선택 취소
            </button>
          </Link>
        </div>       
      </div>
    );
};

export default MenuList;