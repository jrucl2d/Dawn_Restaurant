import React, { useEffect } from "react";
import StaffComponent from "./StaffComponent";
import { useSelector, useDispatch } from "react-redux";
import axios from "axios";
import { initalStaff } from "../../modules/staffReducer";

function StaffListComponent({ storeId }) {
  const staffs = useSelector((state) => state.staffReducer);
  const dispatch = useDispatch();

  useEffect(() => {
    (async () => {
      const staffsResponse = await axios.get(`/staffs/store/${storeId}`);
      const gotStaffs = staffsResponse.data.result;
      const theStaffs = [];
      gotStaffs.sort((a, b) => a.staffId - b.staffId);
      gotStaffs.forEach((staff) => {
        theStaffs.push({
          storeId: storeId,
          staffId: staff.staffId,
          staffName: staff.name,
          staffBirth: staff.birthDate.split(" ")[0],
          staffPosition: staff.position,
          staffPay: staff.wagePerHour,
          staffSex: staff.sex ? "male" : "female",
          staffImage: staff.profileImageURL,
        });
      });
      dispatch(initalStaff(theStaffs));
    })();
  }, []);

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
