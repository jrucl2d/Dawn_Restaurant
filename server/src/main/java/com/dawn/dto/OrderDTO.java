package com.dawn.dto;

import com.dawn.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class OrderDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateOrder {
        private int storeId;
        private List<MenuOrderDTO.Create> menusOrders;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetOrder {
        private int orderId;
        private int totalPrice;
        private List<MenuOrderDTO.Get> menusOrders;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderStateUpdate {
        private int orderId;
        private OrderStatus orderStatus;
    }
}
