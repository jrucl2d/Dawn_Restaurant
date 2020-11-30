import React, { useState } from "react";
import { Modal, Button, Form, InputGroup, FormControl } from "react-bootstrap";
import "./StaffStyle.css";
import { useDispatch } from "react-redux";
import { addStaff } from "../../modules/staffReducer";

import axios from "axios";

function StaffAddModalComponent({ showModal, setShowModal, storeId }) {
  const dispatch = useDispatch();
  const [imageURL, setImageURL] = useState(""); // base64 정보
  const [staffImage, setStaffImage] = useState(""); // 단순 파일 정보
  const [staffInfo, setStaffInfo] = useState({
    staffName: "",
    staffBirth: "",
    staffPosition: "",
    staffPay: 0,
  });
  const [payRadio, setPayRadio] = useState("hour");
  const [sexRadio, setSexRadio] = useState("male");

  const onChangeFile = (e) => {
    e.preventDefault();
    let reader = new FileReader();
    const file = e.target.files[0];
    try {
      reader.onloadend = () => {
        setStaffImage(file);
        setImageURL(reader.result);
      };
      reader.readAsDataURL(file);
    } catch (err) {
      console.error(err);
    }
  };

  const onChangeStaffInfo = (e) => {
    setStaffInfo({
      ...staffInfo,
      [e.target.name]: e.target.value,
    });
  };
  const onClickClose = () => {
    setStaffInfo({
      staffName: "",
      staffBirth: "",
      staffPosition: "",
      staffPay: 0,
    });
    setImageURL("");
    setStaffImage("");
    setShowModal(false);
  };
  const onClickSave = () => {
    if (
      staffInfo.staffName === "" ||
      staffInfo.staffPosition === "" ||
      staffInfo.staffPay === 0
    ) {
      alert("필요한 정보를 모두 입력해야 합니다.");
      return;
    }

    (async () => {
      const formData = new FormData();
      const forSend = {
        storeId: +storeId,
        name: staffInfo.staffName,
        position: staffInfo.staffPosition,
        birthDate: staffInfo.staffBirth,
        sex: sexRadio === "male" ? true : false,
        wagePerHour: `${staffInfo.staffPay}원/${
          payRadio === "hour" ? "시" : "월"
        }`,
      };
      formData.append("staff", JSON.stringify(forSend));
      formData.append("staffImage", staffImage);
      const result = await axios.post("/staff", formData, {
        headers: {
          Authorization: "token",
          "Content-Type": "multipart/form-data; charset=UTF-8",
        },
      });

      if (result.status !== 200) {
        alert("오류가 발생했습니다. 다시 시도해주세요.");
        return;
      }
      dispatch(
        addStaff({
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
      alert("새로운 직원 정보를 등록했습니다.");
      onClickClose();
      window.location.reload();
    })();
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
              placeholder="직책 *"
              name="staffPosition"
              onChange={onChangeStaffInfo}
              value={staffInfo.staffPosition}
            />
          </Form.Group>
          <InputGroup className="mb-3">
            <FormControl
              placeholder="시급/월급 *"
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
                defaultChecked
                label="시급"
                value="hour"
                type="radio"
                name="staffPayRadio"
                id={`inline-radio-1`}
              />
              <Form.Check
                inline
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
              defaultChecked
              label="남"
              value="male"
              type="radio"
              name="staffSexRadio"
              id={`inline-radio-3`}
            />
            <Form.Check
              inline
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
        <Button variant="secondary" onClick={onClickClose}>
          닫기
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default StaffAddModalComponent;
