import React from "react";
import StaffComponent from "./StaffComponent";

function StaffListComponent({ staffs, storeId }) {
  return (
    <div className="staffContainer">
      {staffs.map((staff) => (
        <StaffComponent
          key={staff.staffId}
          staffInfo={staff}
          storeId={storeId}
        />
      ))}
    </div>
  );
}

export default StaffListComponent;
