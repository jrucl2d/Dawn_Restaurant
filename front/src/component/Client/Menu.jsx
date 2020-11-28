import React from 'react';

// imageURL: "menu/3-image.jpg"
// menuDescription: ""
// menuId: 3
// menuTitle: "자장면"
// price: 100
// storeId: 1

// const [menu, setMenu] = useState([
//     {
//         menuTitle: '자장면',
//         imageURL: 'https://t1.daumcdn.net/liveboard/fclc/537979e129a94f40a9d55ebf3ba82496.JPG',
//         price: 100,
//     }
// ])

const Menu = () =>{
    const menuTitle = "자장면";
    const imageURL = 'https://t1.daumcdn.net/liveboard/fclc/537979e129a94f40a9d55ebf3ba82496.JPG';
    const price = 100;
    
    return (
        <div>
            <img src={imageURL} className="imageURL"/>
            <div className="menuTitle">{menuTitle} </div>
            <div classANme="price">{price}원</div>
        </div>
    );
};

export default Menu;