import React, { useState } from "react";
import { Modal, Button, Form, InputGroup, FormControl } from "react-bootstrap";
import "./StaffStyle.css";
import { useDispatch } from "react-redux";
import { updateStaff, deleteStaff } from "../../modules/staffReducer";
import axios from "axios";

function StaffModifyModalComponent({
  showModal,
  setShowModal,
  staff,
  storeId,
}) {
  const dispatch = useDispatch();
  const [imageURL, setImageURL] = useState(staff.staffImage); // base64 정보
  const [staffImage, setStaffImage] = useState(""); // 단순 파일 정보
  const [staffInfo, setStaffInfo] = useState({
    staffName: staff.staffName,
    staffBirth: staff.staffBirth,
    staffPosition: staff.staffPosition,
    staffPay: staff.staffPay.split("원")[0],
  });
  const [payRadio, setPayRadio] = useState(
    staff.staffPay.split("/")[1] === "월" ? "month" : "hour"
  );
  const [sexRadio, setSexRadio] = useState(staff.staffSex);

  const onChangeFile = (e) => {
    e.preventDefault();
    let reader = new FileReader();
    const file = e.target.files[0];
    reader.onloadend = () => {
      setStaffImage(file);
      setImageURL(reader.result);
    };
    reader.readAsDataURL(file);
  };

  const onChangeStaffInfo = (e) => {
    setStaffInfo({
      ...staffInfo,
      [e.target.name]: e.target.value,
    });
  };
  const onClickClose = () => {
    setShowModal(false);
  };
  const onClickSave = () => {
    if (
      staffInfo.staffName === "" ||
      staffInfo.staffPosition === "" ||
      staffInfo.staffPay === ""
    ) {
      alert("필요한 정보를 모두 입력해야 합니다.");
      return;
    }
    // 여기서 데이터베이스에 저장하는 과정 필요
    (async () => {
      const forSend = {
        staffId: +staff.staffId,
        name: staffInfo.staffName,
        position: staffInfo.staffPosition,
        birthDate: staffInfo.staffBirth,
        sex: sexRadio === "male" ? true : false,
        wagePerHour: `${staffInfo.staffPay}원/${
          payRadio === "hour" ? "시" : "월"
        }`,
      };
      const result = await axios.put("/staff", forSend);

      if (result.status !== 200) {
        alert("오류가 발생했습니다. 다시 시도해주세요.");
        return;
      }
      dispatch(
        updateStaff({
          storeId,
          staffId: result.data.result.staffId,
          staffName: result.data.result.name,
          staffBirth: result.data.result.birthDate,
          staffPosition: result.data.result.position,
          staffPay: result.data.result.wagePerHour,
          staffSex: result.data.result.sex ? "male" : "female",
          staffImage: result.data.result.profileImageURL,
        })
      );
    })();

    alert("직원 정보를 수정했습니다.");
    onClickClose();
  };
  const onClickDelete = () => {
    if (window.confirm("정말 직원 정보를 삭제하시겠습니까?")) {
      (async () => {
        const result = await axios.delete("/staff/" + staff.staffId);
        if (result.status !== 200) {
          alert("오류가 발생했습니다. 다시 시도해주세요.");
          return;
        }
        dispatch(deleteStaff(staff.staffId));
      })();

      onClickClose();
    }
  };

  return (
    <Modal show={showModal} onHide={() => {}}>
      <Modal.Header>
        <Modal.Title>직원 추가</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          {imageURL === "" ? (
            <div id="addStaffImage">사진 추가</div>
          ) : (
            <img src={imageURL} id="staffImage" alt="스태프 이미지"></img>
          )}

          <Form.Group>
            <Form.File onChange={onChangeFile} accept=".jpeg, .jpg, .png" />
          </Form.Group>
          <Form.Group>
            <Form.Control
              required
              type="text"
              placeholder="직원 이름 *"
              name="staffName"
              onChange={onChangeStaffInfo}
              value={staffInfo.staffName}
            />
          </Form.Group>
          <Form.Group>
            <InputGroup>
              <InputGroup.Text id="staffBirthText">생년월일</InputGroup.Text>
              <InputGroup.Append id="staffBirthInputAppend">
                <Form.Control
                  required
                  type="date"
                  placeholder="생년월일"
                  name="staffBirth"
                  onChange={onChangeStaffInfo}
                  value={staffInfo.staffBirth}
                />
              </InputGroup.Append>
            </InputGroup>
          </Form.Group>
          <Form.Group>
            <Form.Control
              required
              type="text"
              placeholder="직책"
              name="staffPosition"
              onChange={onChangeStaffInfo}
              value={staffInfo.staffPosition}
            />
          </Form.Group>
          <InputGroup className="mb-3">
            <FormControl
              placeholder="시급/월급"
              aria-label="원"
              type="number"
              onChange={onChangeStaffInfo}
              name="staffPay"
              value={staffInfo.staffPay}
            />
            <InputGroup.Append
              name="staffPayRadio"
              onChange={(e) => setPayRadio(e.target.value)}
            >
              <InputGroup.Text>원</InputGroup.Text>
              &nbsp; &nbsp;
              <Form.Check
                inline
                defaultChecked={payRadio === "hour" ? true : false}
                label="시급"
                value="hour"
                type="radio"
                name="staffPayRadio"
                id={`inline-radio-1`}
              />
              <Form.Check
                inline
                defaultChecked={payRadio === "month" ? true : false}
                label="월급"
                value="month"
                type="radio"
                name="staffPayRadio"
                id={`inline-radio-2`}
              />
            </InputGroup.Append>
          </InputGroup>
          <InputGroup
            name="staffSexRadio"
            onChange={(e) => setSexRadio(e.target.value)}
          >
            &nbsp; &nbsp; 성별 &nbsp; &nbsp;
            <Form.Check
              inline
              label="남"
              defaultChecked={sexRadio === "male" ? true : false}
              value="male"
              type="radio"
              name="staffSexRadio"
              id={`inline-radio-3`}
            />
            <Form.Check
              inline
              defaultChecked={sexRadio === "female" ? true : false}
              label="여"
              value="female"
              type="radio"
              name="staffSexRadio"
              id={`inline-radio-4`}
            />
          </InputGroup>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="primary" onClick={onClickSave}>
          저장
        </Button>
        <Button variant="danger" onClick={onClickDelete}>
          삭제
        </Button>
        <Button variant="secondary" onClick={onClickClose}>
          닫기
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default StaffModifyModalComponent;
