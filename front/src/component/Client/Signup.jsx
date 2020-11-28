import { Component } from "react";
import {Link} from 'react-router-dom'; 

export default class SignUp extends Component {
  render() {
    return (
      <div className="auth-wrapper">
      <div className="auth-inner"> 
      <form>
        <h3>회원가입</h3>

        <div className="form-group">
          <label>이메일</label>
          <input
            type="text"
            className="form-control"
            placeholder="Enter Email"
          />
        </div>

        <div className="form-group">
          <label>닉네임</label>
          <input
            type="text"
            className="form-control"
            placeholder="Enter Nickname"
          />
        </div>

        <div className="form-group">
          <label>비밀번호</label>
          <input
            type="password"
            className="form-control"
            placeholder="Enter Password"
          />
        </div>

        <div className="form-group">
          <label>비밀번호 확인</label>
          <input
            type="password"
            className="form-control"
            placeholder="Enter Password"
          />
        </div>
        <Link to='/'>
        <button
          type="submit"
          className="btn btn-primary btn-block"
          onClick={() => alert("회원가입이 완료되었습니다!")}
        >
          회원가입
        </button>
        </Link>
        <p className="forgot-password text-right">
          Already registered <Link to="./">sign in?</Link>
        </p>
      </form>
      </div>
      </div>
    );
  }
}
