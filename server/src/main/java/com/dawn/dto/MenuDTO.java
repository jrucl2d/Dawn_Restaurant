package com.dawn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class MenuDTO {

    @Getter
    @Setter
    @AllArgsConstructor
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
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Remove {
        private int menuId;
    }
}
