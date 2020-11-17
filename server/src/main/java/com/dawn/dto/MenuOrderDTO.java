package com.dawn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class MenuOrderDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Create {
        private int menuId;
        private int quantity;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Get {
        private int menuOrderId;
        private int quantity;
        private MenuDTO.OrderListItem menu;
    }
}
