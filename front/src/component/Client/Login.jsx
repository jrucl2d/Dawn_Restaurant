import React, { useState, useRef } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const Login = ({ history }) => {
  const isAdminRef = useRef();
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
    (async () => {
      try {
        const jwt = await axios.post("/login", {
          username: info.email,
          password: info.password,
        });
        const userId = await axios.get(
          `/getUserId/${info.email.replace(".", "-")}`
        );
        localStorage.setItem("userId", userId.data);
        if (isAdminRef.current.checked) {
          alert("환영합니다.");
          history.push("/store");
        } else {
          alert("환영합니다.");
          history.push("/menu-list");
        }
      } catch (err) {
        alert("로그인 정보가 없습니다.  다시 시도해주세요.");
      }
    })();
  };
  const onChangeInfo = (e) => {
    setInfo({
      ...info,
      [e.target.name]: e.target.value,
    });
  };
  const onClickLogout = () => {
    localStorage.removeItem("userId");
    history.push("/");
  };
  const onClickGoOrder = () => {
    history.push("/menu-list");
  };
  return (
    <div className="auth-wrapper">
      <div className="auth-inner">
        {localStorage.getItem("userId") === null ? (
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
            <label htmlFor="isAdmin">관리자</label>&nbsp;
            <input type="checkbox" name="isAdmin" ref={isAdminRef} />
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
        ) : (
          <>
            <h3>로그아웃</h3>
            <hr />
            <h4>로그아웃을 하시겠습니까?</h4>
            <hr />
            <button
              onClick={onClickLogout}
              className="btn btn-warning btn-block"
              varient="warning"
            >
              로그아웃
            </button>
            <button
              className="btn btn-primary btn-block"
              varient="secondary"
              onClick={onClickGoOrder}
            >
              주문하러 가기
            </button>
          </>
        )}
      </div>
    </div>
  );
};
export default Login;
