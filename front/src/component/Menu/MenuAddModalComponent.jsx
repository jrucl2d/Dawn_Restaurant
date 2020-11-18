import React, { useState } from "react";
import { Modal, Button, Form, InputGroup, FormControl } from "react-bootstrap";
import "./MenuStyle.css";
import { v4 as uuid } from "uuid";

const noResize = { resize: "none" };

function MenuAddModalComponent({
  showModal,
  setShowModal,
  storeId,
  menus,
  setMenus,
}) {
  const [imageURL, setImageURL] = useState(""); // base64 정보
  const [menuImage, setMenuImage] = useState(""); // 단순 파일 정보
  const [menuInfo, setMenuInfo] = useState({
    menuName: "",
    menuPrice: 0,
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
    // 데이터베이스에 저장하는 과정 필요
    // try catch, const result = await axios.post..... 해서
    // 사용자 정보와 가게 정보 보내야 할 수도
    console.log(storeId);
    const sendingData = {
      menuId: uuid(),
      menuName: menuInfo.menuName,
      menuPrice: menuInfo.menuPrice,
      menuOrigin: menuInfo.menuOrigin,
      menuIntroduce: menuInfo.menuIntroduce,
      menuImage: menuImage
        ? menuImage.name.split(".")[0] +
          "_" +
          Date.now() +
          "." +
          menuImage.name.split(".")[1]
        : "",
    };

    const result = true;
    if (!result) {
      alert("오류가 발생했습니다. 다시 시도해주세요.");
      return;
    }
    // 데이터베이스 저장에 성공했을 때 받은 result 값으로 설정하는 코드로 변경 필요
    setMenus([...menus, sendingData]);

    setMenuInfo({
      menuName: "",
      menuPrice: 0,
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
            <InputGroup className="mb-3">
              <FormControl
                placeholder="메뉴 가격"
                aria-label="원"
                type="number"
                onChange={onChangeMenuInfo}
                name="menuPrice"
                value={menuInfo.storePrice}
              />
              <InputGroup.Append>
                <InputGroup.Text>원</InputGroup.Text>
              </InputGroup.Append>
            </InputGroup>
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
