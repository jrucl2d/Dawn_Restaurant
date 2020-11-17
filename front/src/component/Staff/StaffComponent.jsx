import React from "react";
import "./StaffStyle.css";
import { Button } from "react-bootstrap";

function StaffComponent({ staffInfo }) {
  return (
    <Button className="card" id="staffComponentCard">
      <div id="staffComponentContent">
        <img src="#" alt="사진" />
        <div id="staffComponentInfo">
          <span>이름 : {staffInfo.staffName}</span>
          <span>직급 : {staffInfo.staffPosition}</span>
        </div>
      </div>
    </Button>
  );
}

export default StaffComponent;
