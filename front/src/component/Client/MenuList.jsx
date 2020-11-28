import React from 'react';
import {Link} from 'react-router-dom'; 

const MenuList = ()=>{
    return (
      <div className="auth-wrapper">
      <div className="auth-inner"> 
      <div>Menu List</div>
          <Link to='/order-list'>
            <button type="submit" className="btn btn-primary btn-block" varient="secondary">
              주문하기
            </button>
          </Link>
          <Link to='menu-list'>
            <button type="submit" className="btn btn-primary btn-block" varient="secondary" onClick={() => alert("선택이 취소되었습니다!")}>
              선택 취소
            </button>
          </Link>
        </div>       
      </div>
    );
};

export default MenuList;