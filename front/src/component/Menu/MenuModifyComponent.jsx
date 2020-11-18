import React, { useState } from "react";
import { Form, InputGroup, FormControl } from "react-bootstrap";
import "./MenuStyle.css";
import { useDispatch } from "react-redux";
import { deleteMenu, updateMenu } from "../../modules/menuReducer";

function MenuModifyComponent({ deMenuEditMode, menu, storeId }) {
  const dispatch = useDispatch();
  const [imageURL, setImageURL] = useState(""); // base64 정보
  const [menuImage, setMenuImage] = useState(""); // 단순 파일 정보
  const [menuInfo, setMenuInfo] = useState({
    menuName: menu.menuName,
    menuPrice: menu.menuPrice,
    menuOrigin: menu.menuOrigin,
    menuIntroduce: menu.menuIntroduce,
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
  const onClickSave = () => {
    if (menuInfo.menuName === "" || menuInfo.storePrice === "") {
      alert("필요한 정보를 모두 입력해야 합니다.");
      return;
    }
    // 데이터베이스에 저장하는 과정 필요
    // try catch, const result = await axios.post..... 해서
    // 사용자 정보와 가게 정보 보내야 할 수도
    const sendingData = {
      storeId,
      menuId: menu.menuId,
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
    dispatch(updateMenu(sendingData));
    alert("메뉴 정보를 수정했습니다.");
    deMenuEditMode();
  };
  const onClickDelete = () => {
    if (window.confirm("정말 해당 메뉴를 삭제하시겠습니까?")) {
      dispatch(deleteMenu(menu.menuId));
      deMenuEditMode();
    }
  };

  return (
    <>
      <Form>
        {imageURL === "" ? (
          <div id="modifyMenuImage">사진 추가</div>
        ) : (
          <img src={imageURL} id="modifyMenuShowImage" alt="메뉴 이미지"></img>
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
              value={menuInfo.menuPrice}
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
            value={menuInfo.menuOrigin}
          />
        </Form.Group>
        <Form.Group>
          <Form.Control
            placeholder="메뉴 소개"
            as="textarea"
            rows={3}
            name="menuIntroduce"
            onChange={onChangeMenuInfo}
            value={menuInfo.menuIntroduce}
          />
        </Form.Group>
      </Form>
      <div id="modifyMenuButtons">
        <div id="modifyMenuSave" onClick={onClickSave}>
          저장
        </div>
        <div id="modifyMenuDelete" onClick={onClickDelete}>
          삭제
        </div>
        <div id="modifyMenuCancel" onClick={deMenuEditMode}>
          취소
        </div>
      </div>
    </>
  );
}

export default MenuModifyComponent;
