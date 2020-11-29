import React from 'react';
import {Link} from 'react-router-dom'; 

const OrderList = ()=>{
    return (
        <div className="auth-wrapper">
        <div className="auth-inner"> 
        <div>
            <div>주문 정보</div>
            <hr />
            <div>주문 내역들~~~</div>
            <hr />
            <div> 합계 0원</div>
            <Link to='/payment'>
                <button type="submit" className="btn btn-primary btn-block" varient="secondary" onClick={() => alert("결제하시겠습니까?")}>
                결제하기
                </button>
            </Link>
            &nbsp;
            <Link to='menu-list'>
                <button type="submit" className="btn btn-danger btn-block" varient="secondary" onClick={() => alert("메뉴창으로 돌아갑니다.")}>
                주문 취소
                </button>
            </Link>
        </div>
        </div>
        </div>
    );
};

export default OrderList;