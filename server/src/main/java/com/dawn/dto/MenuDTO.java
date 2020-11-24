package com.dawn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MenuDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateMenu {
        private int storeId;
        private String menuTitle;
        private String menuDescription;
        private int price;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetMenu {
        private int menuId;
        private int storeId;
        private String menuTitle;
        private String menuDescription;
        private int price;
        private String imageURL;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
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
