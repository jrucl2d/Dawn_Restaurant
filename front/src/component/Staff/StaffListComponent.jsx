import React, { useState } from "react";
import StaffComponent from "./StaffComponent";

function StaffListComponent() {
  const [staffs, setStaffs] = useState([
    {
      staffId: "wfwefewfewfqe",
      staffName: "김아무개",
      staffPosition: "사장",
      staffImg: "",
    },
    {
      staffId: "ewgwgewgewg",
      staffName: "박아무개",
      staffPosition: "매니저",
      staffImg: "",
    },
    {
      staffId: "nxfgntmtymdymydt",
      staffName: "정아무개",
      staffPosition: "알바",
      staffImg: "",
    },
    {
      staffId: "qwehwehqewrhrqehq",
      staffName: "동아무개",
      staffPosition: "알바",
      staffImg: "",
    },
    {
      staffId: "qwehwehqewrhrqehq",
      staffName: "동아무개",
      staffPosition: "알바",
      staffImg: "",
    },
    {
      staffId: "qwehwehqewrhrqehq",
      staffName: "동아무개",
      staffPosition: "알바",
      staffImg: "",
    },
    {
      staffId: "qwehwehqewrhrqehq",
      staffName: "동아무개",
      staffPosition: "알바",
      staffImg: "",
    },
  ]);
  return (
    <div className="staffContainer">
      {staffs.map((staff) => (
        <StaffComponent key={staff.staffId} staffInfo={staff} />
      ))}
    </div>
  );
}

export default StaffListComponent;
