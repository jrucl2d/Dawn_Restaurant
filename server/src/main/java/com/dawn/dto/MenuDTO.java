package com.dawn.dto;

import com.dawn.model.Menu;
import lombok.*;

public class MenuDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Create {
        private int storeId;
        private String menuTitle;
        private String menuDescription;
        private int price;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Get {
        private int menuId;
        private int storeId;
        private String menuTitle;
        private String menuDescription;
        private int price;
        private String imageURL;

        public static MenuDTO.Get fromMenu(Menu menu) {
            return new MenuDTO.Get(
                    menu.getMenuId(), menu.getStore().getStoreId(),
                    menu.getMenuTitle(), menu.getMenuDescription(),
                    menu.getPrice(), menu.getImageURL());
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Remove {
        private int menuId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderListItem {
        private int menuId;
        private String menuTitle;
        private int price;
        private String imageURL;
    }
}
