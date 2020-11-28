import React, { Component } from "react";
import {Link} from 'react-router-dom'; 

export default class Login extends Component {
  render() {
    return (
      <div className="auth-wrapper">
      <div className="auth-inner"> 
      <form>
        <h3>로그인</h3>

        <div className="form-group">
          <label>이메일</label>
          <input
            type="email"
            className="form-control"
            placeholder="Enter email"
          />
        </div>

        <div className="form-group">
          <label>비밀번호</label>
          <input
            type="password"
            className="form-control"
            placeholder="Enter password"
          />
        </div>

        <div className="form-group">
          <div className="custom-control custom-checkbox">
            <input
              type="checkbox"
              className="custom-control-input"
              id="customCheck1"
            />
            <label className="custom-control-label" htmlFor="customCheck1">
              Remember me
            </label>
          </div>
        </div>
        <Link to='/menu-list'>
        <button type="submit" className="btn btn-primary btn-block" varient="secondary" onClick={() => alert("환영합니다! 새벽식당입니다!")}>
          로그인
        </button>
        </Link>
        <hr />
        <div>회원이 아니라면 <Link to='./sign-up'>회원가입</Link> 하세요</div>
      </form>
      </div>
      </div>
    );
  }
}
