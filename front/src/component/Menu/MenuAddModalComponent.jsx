import React, { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import "./MenuStyle.css";

const noResize = { resize: "none" };

function MenuAddModalComponent({ showModal, setShowModal }) {
  const [imageURL, setImageURL] = useState(""); // base64 정보
  const [menuImage, setMenuImage] = useState(""); // 단순 파일 정보
  const [menuInfo, setMenuInfo] = useState({
    menuName: "",
    menuPrice: "",
    menuOrigin: "",
    menuIntroduce: "",
  });

  const onChangeFile = (e) => {
    e.preventDefault();
    let reader = new FileReader();
    const file = e.target.files[0];
    reader.onloadend = () => {
      setMenuImage(file);
      setImageURL(reader.result);
    };
    reader.readAsDataURL(file);
  };

  const onChangeMenuInfo = (e) => {
    setMenuInfo({
      ...menuInfo,
      [e.target.name]: e.target.value,
    });
  };
  const onClickClose = () => setShowModal(false);
  const onClickSave = () => {
    if (menuInfo.menuName === "" || menuInfo.storePrice === "") {
      alert("필요한 정보를 모두 입력해야 합니다.");
      return;
    }
    // 여기서 데이터베이스에 저장하는 과정 필요
    console.log(menuInfo);
    console.log(menuImage);
    // try catch, const result = await axios.post..... 해서
    const result = true;
    if (!result) {
      alert("오류가 발생했습니다. 다시 시도해주세요.");
      return;
    }

    setMenuInfo({
      menuName: "",
      menuPrice: "",
      menuOrigin: "",
      menuIntroduce: "",
    });
    setImageURL("");
    setMenuImage("");
    alert("새로운 메뉴를 생성했습니다.");
    onClickClose();
  };

  return (
    <Modal show={showModal} onHide={() => {}}>
      <Modal.Header>
        <Modal.Title>메뉴 추가</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          {imageURL === "" ? (
            <div id="addMenuImage">사진 추가</div>
          ) : (
            <img src={imageURL} id="menuImage" alt="메뉴 이미지"></img>
          )}

          <Form.Group>
            <Form.File onChange={onChangeFile} accept=".jpeg, .jpg, .png" />
          </Form.Group>
          <Form.Group>
            <Form.Control
              required
              type="text"
              placeholder="메뉴 이름"
              name="menuName"
              onChange={onChangeMenuInfo}
              value={menuInfo.menuName}
            />
          </Form.Group>
          <Form.Group>
            <Form.Control
              required
              type="text"
              placeholder="메뉴 가격"
              name="menuPrice"
              onChange={onChangeMenuInfo}
              value={menuInfo.storePrice}
            />
          </Form.Group>
          <Form.Group>
            <Form.Control
              required
              type="text"
              placeholder="원산지 정보"
              name="menuOrigin"
              onChange={onChangeMenuInfo}
              value={menuInfo.storeOrigin}
            />
          </Form.Group>
          <Form.Group>
            <Form.Control
              placeholder="메뉴 소개"
              as="textarea"
              rows={3}
              style={noResize}
              name="menuIntroduce"
              onChange={onChangeMenuInfo}
              value={menuInfo.storeIntroduce}
            />
          </Form.Group>
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

export default MenuAddModalComponent;
