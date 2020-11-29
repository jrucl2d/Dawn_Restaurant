import React, { useState, useRef } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const SignUp = ({ history }) => {
  const checkRef = useRef();
  const [info, setInfo] = useState({
    loginId: "",
    userName: "",
    password: "",
    password2: "",
  });

  const onChangeInfo = (e) => {
    setInfo({
      ...info,
      [e.target.name]: e.target.value,
    });
  };

  const onClickRegister = (e) => {
    e.preventDefault();
    if (
      info.loginId === "" ||
      info.userName === "" ||
      info.password === "" ||
      info.password2 === ""
    ) {
      alert("모든 정보를 입력해주세요");
      return;
    }
    if (info.password !== info.password2) {
      alert("비밀번호가 일치하지 않습니다");
      return;
    }
    (async () => {
      const result = await axios.post("/register", {
        loginId: info.loginId,
        userName: info.userName,
        password: info.password,
        isBusiness: checkRef.current.checked,
      });
      if (result.status !== 200) {
        alert("오류가 발생했습니다. 다시 시도해주세요.");
        return;
      }
      setInfo({
        loginId: "",
        userName: "",
        password: "",
        password2: "",
      });
      alert("회원가입이 완료되었습니다");
      history.push("/");
    })();
  };
  return (
    <div className="auth-wrapper">
      <div className="auth-inner">
        <form>
          <h3>회원가입</h3>
          <div className="form-group">
            <label>아이디</label>
            <input
              type="text"
              name="loginId"
              onChange={onChangeInfo}
              value={info.loginId}
              className="form-control"
              placeholder="아이디"
            />
          </div>
          <div className="form-group">
            <label>이름</label>
            <input
              type="text"
              name="userName"
              onChange={onChangeInfo}
              value={info.userName}
              className="form-control"
              placeholder="이름"
            />
          </div>
          <div className="form-group">
            <label>비밀번호</label>
            <input
              type="password"
              name="password"
              onChange={onChangeInfo}
              value={info.password}
              className="form-control"
              placeholder="비밀번호"
            />
          </div>
          <div className="form-group">
            <label>비밀번호 확인</label>
            <input
              type="password"
              name="password2"
              onChange={onChangeInfo}
              value={info.password2}
              className="form-control"
              placeholder="비밀번호 확인"
            />
          </div>
          <label htmlFor="isBusiness">사업자 여부</label>
          &nbsp;
          <input name="isBusiness" type="checkbox" ref={checkRef} />
          <button
            className="btn btn-primary btn-block"
            onClick={onClickRegister}
          >
            회원가입
          </button>
          <p className="forgot-password text-right">
            Already registered <Link to="./">sign in?</Link>
          </p>
        </form>
      </div>
    </div>
  );
};
export default SignUp;
