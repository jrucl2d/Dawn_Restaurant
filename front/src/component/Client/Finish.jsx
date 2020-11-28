import React from 'react';

const Finish = ()=>{
    return (
        <div className="auth-wrapper">
        <div className="auth-inner"> 
        <div className="finish">
            <div className="finishInfo">
                <div>
                    <div>주문이 정상적으로 완료되었습니다.</div>
                        <img src ="https://static.thenounproject.com/png/35367-200.png" className="finishImage"></img>
                    <div>내 주문 번호: 1</div>
                    <div>예상 소요시간: 15분</div>
                </div>
            </div>
        </div>
        </div>
        </div>
    );
};

export default Finish;