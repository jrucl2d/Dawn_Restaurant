package com.dawn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrderDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Create {
        private int storeId;
        private List<MenuOrderDTO.Create> menusOrders;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Get {
        private int orderId;
        private int totalPrice;
        private List<MenuOrderDTO.Get> menusOrders;
    }

}
