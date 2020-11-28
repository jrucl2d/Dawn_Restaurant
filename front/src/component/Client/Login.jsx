import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setUser } from "../../modules/userReducer";

const Login = ({ history }) => {
  const dispatch = useDispatch();
  const [info, setInfo] = useState({
    email: "",
    password: "",
  });
  const onClickLogin = (e) => {
    e.preventDefault();
    if (info.email === "" || info.password === "") {
      alert("모든 정보를 입력하세요");
      return;
    }
    if (info.email[0] === "a") {
      info["userId"] = 1;
    } else if (info.email[0] === "b") {
      info["userId"] = 2;
    }
    dispatch(setUser(info));
    alert("환영합니다! 새벽식당입니다!");
    if (info.userId === 1) {
      history.push("/store");
    } else {
      history.push("/menu-list");
    }
  };
  const onChangeInfo = (e) => {
    setInfo({
      ...info,
      [e.target.name]: e.target.value,
    });
  };

  return (
    <div className="auth-wrapper">
      <div className="auth-inner">
        <form>
          <h3>로그인</h3>

          <div className="form-group">
            <label>이메일</label>
            <input
              type="email"
              name="email"
              className="form-control"
              placeholder="Enter email"
              onChange={onChangeInfo}
              value={info.email}
            />
          </div>

          <div className="form-group">
            <label>비밀번호</label>
            <input
              type="password"
              name="password"
              className="form-control"
              placeholder="Enter password"
              onChange={onChangeInfo}
              value={info.password}
            />
          </div>
          <button
            className="btn btn-primary btn-block"
            varient="secondary"
            onClick={onClickLogin}
          >
            로그인
          </button>
          <hr />
          <div>
            회원이 아니라면 <Link to="./sign-up">회원가입</Link> 하세요
          </div>
        </form>
      </div>
    </div>
  );
};
export default Login;
