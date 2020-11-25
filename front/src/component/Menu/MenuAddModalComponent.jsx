import React, { useState } from "react";
import { Modal, Button, Form, InputGroup, FormControl } from "react-bootstrap";
import "./MenuStyle.css";
import { useDispatch } from "react-redux";
import { addMenu } from "../../modules/menuReducer";
import axios from "axios";

const noResize = { resize: "none" };

function MenuAddModalComponent({ showModal, setShowModal, storeId }) {
  const dispatch = useDispatch();
  const [imageURL, setImageURL] = useState(""); // base64 정보
  const [menuImage, setMenuImage] = useState(""); // 단순 파일 정보
  const [menuInfo, setMenuInfo] = useState({
    menuName: "",
    menuPrice: 0,
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

    (async () => {
      const formData = new FormData();
      const forSend = {
        storeId: +storeId,
        menuTitle: menuInfo.menuName,
        menuDescription: menuInfo.menuIntroduce,
        price: +menuInfo.menuPrice,
      };
      formData.append("menu", JSON.stringify(forSend));
      formData.append("menuImage", menuImage);
      const result = await axios.post("/menu", formData, {
        headers: {
          Authorization: "token",
          "Content-Type": "multipart/form-data",
        },
      });

      if (result.status !== 200) {
        alert("오류가 발생했습니다. 다시 시도해주세요.");
        return;
      }
      dispatch(
        addMenu({
          storeId,
          menuId: result.data.result.menuId,
          menuName: result.data.result.menuTitle,
          menuPrice: result.data.result.menuPrice,
          menuIntroduce: result.data.result.menuDescription,
          menuImage: result.data.result.imageURL,
        })
      );
    })();

    setMenuInfo({
      menuName: "",
      menuPrice: 0,
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
              placeholder="메뉴 이름 *"
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
                value={menuInfo.menuPrice}
              />
              <InputGroup.Append>
                <InputGroup.Text>원 *</InputGroup.Text>
              </InputGroup.Append>
            </InputGroup>
          </Form.Group>
          <Form.Group>
            <Form.Control
              placeholder="메뉴 소개"
              as="textarea"
              rows={3}
              style={noResize}
              name="menuIntroduce"
              onChange={onChangeMenuInfo}
              value={menuInfo.menuIntroduce}
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
