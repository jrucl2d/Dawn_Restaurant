import React, { useState } from "react";
import { Form, InputGroup, FormControl } from "react-bootstrap";
import "./MenuStyle.css";
import { useDispatch } from "react-redux";
import { deleteMenu, updateMenu } from "../../modules/menuReducer";
import axios from "axios";

function MenuModifyComponent({ deMenuEditMode, menu, storeId, imageURLFirst }) {
  const dispatch = useDispatch();
  const [imageURL, setImageURL] = useState(imageURLFirst); // base64 정보
  // eslint-disable-next-line
  const [menuImage, setMenuImage] = useState(""); // 단순 파일 정보
  const [menuInfo, setMenuInfo] = useState({
    menuName: menu.menuName,
    menuPrice: menu.menuPrice,
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

    (async () => {
      const forSend = {
        menuId: menu.menuId,
        menuTitle: menuInfo.menuName,
        menuDescription: menuInfo.menuIntroduce,
        price: +menuInfo.menuPrice,
      };
      const result = await axios.put("/menu", forSend);

      if (result.status !== 200) {
        alert("오류가 발생했습니다. 다시 시도해주세요.");
        return;
      }
      dispatch(
        updateMenu({
          storeId,
          menuId: result.data.result.menuId,
          menuName: result.data.result.menuTitle,
          menuPrice: result.data.result.menuPrice,
          menuIntroduce: result.data.result.menuDescription,
          menuImage: menu.imageURL,
        })
      );
      alert("메뉴 정보를 수정했습니다.");
      deMenuEditMode();
    })();
  };
  const onClickDelete = async () => {
    if (window.confirm("정말 해당 메뉴를 삭제하시겠습니까?")) {
      const result = await axios.delete(`/menu/${menu.menuId}`);
      if (result.status !== 200) {
        alert("오류 발생!");
        return;
      }
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
            placeholder="메뉴 이름 *"
            name="menuName"
            onChange={onChangeMenuInfo}
            value={menuInfo.menuName}
          />
        </Form.Group>
        <Form.Group>
          <InputGroup className="mb-3">
            <FormControl
              placeholder="메뉴 가격 *"
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
