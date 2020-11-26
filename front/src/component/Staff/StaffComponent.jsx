import React, { useState } from "react";
import "./StaffStyle.css";
import { Button } from "react-bootstrap";
import StaffModifyModalComponent from "./StaffModifyModalComponent";

function StaffComponent({ staffInfo, storeId }) {
  const [showModal, setShowModal] = useState(false);

  const onClickStaff = () => {
    setShowModal(true);
  };

  return (
    <>
      <Button className="card" id="staffComponentCard" onClick={onClickStaff}>
        <div id="staffComponentContent">
          <img src={staffInfo.staffImage} alt="사진" />
          <div id="staffComponentInfo">
            <span>이름 : {staffInfo.staffName}</span>
            <span>직급 : {staffInfo.staffPosition}</span>
          </div>
        </div>
      </Button>
      <StaffModifyModalComponent
        showModal={showModal}
        setShowModal={setShowModal}
        storeId={storeId}
        staff={staffInfo}
      />
    </>
  );
}

export default StaffComponent;
