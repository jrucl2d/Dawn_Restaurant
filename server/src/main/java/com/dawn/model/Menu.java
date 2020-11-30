package com.dawn.model;

import com.dawn.common.CloudConstants;
import com.dawn.dto.MenuDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;

    @Column
    private String menuTitle;

    @Column
    private String menuDescription;

    @Column
    private int price;

    @Column
    private String imageFileName;

    @ManyToOne
    private Store store;

    @OneToMany
    private List<MenuOrder> menuOrders = new ArrayList<>();

    public Menu(int menuId) {
        this.menuId = menuId;
    }

    public Menu(String menuTitle, String menuDescription, int price, String imageFileName, Store store) {
        this.menuTitle = menuTitle;
        this.menuDescription = menuDescription;
        this.price = price;
        this.imageFileName = imageFileName;
        this.store = store;
    }

    public static MenuDTO.GetMenu toMenuDTOGetMenu(Menu menu) {
        return new MenuDTO.GetMenu(
                menu.getMenuId(), menu.getStore().getStoreId(),
                menu.getMenuTitle(), menu.getMenuDescription(),
                menu.getPrice(), CloudConstants.CloudStorageBaseURL + "/" + menu.getImageFileName());
    }

    public static MenuDTO.OrderListItem toMenuDTOOrderListItem(Menu menu) {
        return new MenuDTO.OrderListItem(menu.getMenuId(), menu.getMenuTitle(), menu.getPrice(),
                CloudConstants.CloudStorageBaseURL + "/" + menu.getImageFileName());
    }
}
