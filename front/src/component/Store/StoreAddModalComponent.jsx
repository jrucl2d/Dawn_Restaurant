import React, { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { v4 as uuid } from "uuid";
import { useDispatch } from "react-redux";
import { addStore } from "../../modules/storeReducer";

const noResize = { resize: "none" };

function StoreAddModalComponent({ showAddStore, setShowAddStore }) {
  const dispatch = useDispatch();
  const [imageURL, setImageURL] = useState(""); // base64 정보
  const [storeImage, setStoreImage] = useState(""); // 단순 파일 정보
  const [storeInfo, setStoreInfo] = useState({
    storeName: "",
    storeLocation: "",
    storeTime: "",
    storeIntroduce: "",
  });

  const onChangeFile = (e) => {
    e.preventDefault();
    let reader = new FileReader();
    const file = e.target.files[0];
    reader.onloadend = () => {
      setStoreImage(file);
      setImageURL(reader.result);
    };
    reader.readAsDataURL(file);
  };

  const onChangeStoreInfo = (e) => {
    setStoreInfo({
      ...storeInfo,
      [e.target.name]: e.target.value,
    });
  };
  const onClickClose = () => setShowAddStore(false);
  const onClickSave = () => {
    if (storeInfo.storeName === "" || storeInfo.storeLocation === "") {
      alert("필요한 정보를 모두 입력해야 합니다.");
      return;
    }
    // 여기서 데이터베이스에 저장하는 과정 필요
    // try catch, const result = await axios.post..... 해서
    const result = true;
    if (!result) {
      alert("오류가 발생했습니다. 다시 시도해주세요.");
      return;
    }

    dispatch(addStore({ ...storeInfo, storeImage, storeId: uuid() }));

    setStoreInfo({
      storeName: "",
      storeLocation: "",
      storeTime: "",
      storeIntroduce: "",
    });
    setImageURL("");
    setStoreImage("");
    alert("새로운 점포를 생성했습니다.");
    onClickClose();
  };

  return (
    <Modal show={showAddStore} onHide={() => {}}>
      <Modal.Header>
        <Modal.Title>점포 추가</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          {imageURL === "" ? (
            <div id="addStoreImage">사진 추가</div>
          ) : (
            <img src={imageURL} id="storeImage" alt="매장 이미지"></img>
          )}

          <Form.Group>
            <Form.File onChange={onChangeFile} accept=".jpeg, .jpg, .png" />
          </Form.Group>
          <Form.Group>
            <Form.Control
              required
              type="text"
              placeholder="점포 상호명 *"
              name="storeName"
              onChange={onChangeStoreInfo}
              value={storeInfo.storeName}
            />
          </Form.Group>
          <Form.Group>
            <Form.Control
              required
              type="text"
              placeholder="점포 위치 *"
              name="storeLocation"
              onChange={onChangeStoreInfo}
              value={storeInfo.storeLocation}
            />
          </Form.Group>
          <Form.Group>
            <Form.Control
              required
              type="text"
              placeholder="영업 시간"
              name="storeTime"
              onChange={onChangeStoreInfo}
              value={storeInfo.storeTime}
            />
          </Form.Group>
          <Form.Group>
            <Form.Control
              placeholder="점포 소개"
              as="textarea"
              rows={3}
              style={noResize}
              name="storeIntroduce"
              onChange={onChangeStoreInfo}
              value={storeInfo.storeIntroduce}
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

export default StoreAddModalComponent;
