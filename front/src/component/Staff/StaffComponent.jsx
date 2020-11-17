import React from "react";
import "./StaffStyle.css";
import { Button } from "react-bootstrap";

function StaffComponent() {
  return (
    <Button className="card" id="staffComponentCard">
      <div id="staffComponentContent">
        <img src="#" alt="사진" />
        <div id="staffComponentInfo">
          <span>이름 : 이름</span>
          <span>직급 : 사장</span>
        </div>
      </div>
    </Button>
  );
}

export default StaffComponent;
