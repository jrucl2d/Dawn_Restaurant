import React from "react";
import StaffComponent from "./StaffComponent";
import { useSelector } from "react-redux";

function StaffListComponent({ storeId }) {
  const staffs = useSelector((state) => state.staffReducer);
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
