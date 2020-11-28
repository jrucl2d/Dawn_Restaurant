import React from 'react';
import {Link} from 'react-router-dom';

const Payment = ()=>{
    return (
        <div className="auth-wrapper">
        <div className="auth-inner"> 
        <div>
            <div>주문 진행</div>
            <div>결제 방식</div>

            <div className="form-group">
                <input
                    type="email"
                    className="form-control"
                    placeholder="카드 번호"
                />
            </div>

            <div className="form-group">
                <input
                    type="password"
                    className="form-control"
                    placeholder="카드 비밀번호"
                />
            </div>
            <hr />
            <div> 합계 0원</div>
            <Link to='/Finish'>
                <button type="submit" className="btn btn-primary btn-block" varient="secondary" onClick={() => alert("결제하시겠습니까?")}>
                결제하기
                </button>
            </Link>

            <Link to='/order-list'>
                <button type="submit" className="btn btn-primary btn-block" varient="secondary" onClick={() => alert("결제를 취소하시겠습니까?")}>
                주문취소
                </button>
            </Link>
        </div>
        </div>
        </div>
    );
};

export default Payment;