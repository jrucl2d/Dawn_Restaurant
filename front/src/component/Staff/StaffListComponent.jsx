import React, { useState } from "react";
import StaffComponent from "./StaffComponent";

function StaffListComponent({ staffs }) {
  return (
    <div className="staffContainer">
      {staffs.map((staff) => (
        <StaffComponent key={staff.staffId} staffInfo={staff} />
      ))}
    </div>
  );
}

export default StaffListComponent;
