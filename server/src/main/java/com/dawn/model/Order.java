package com.dawn.model;

import com.dawn.dto.MenuOrderDTO;
import com.dawn.dto.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus = OrderStatus.NEW;

    @Column
    private int totalPrice;

    @Column
    private boolean isPayed = false;

    @OneToMany
    private List<MenuOrder> menuOrders = new ArrayList<>();

    @ManyToOne
    private Store store;

    public Order(int orderId, int totalPrice, Store store) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.store = store;

    }

    public Order(int totalPrice, Store store) {
        this.totalPrice = totalPrice;
        this.store = store;

    }

    public static OrderDTO.GetOrder toOrderDTOGet(Order order) {
        return new OrderDTO.GetOrder(
                order.getOrderId(),
                order.getTotalPrice(),
                order.getMenuOrders().stream()
                        .map(x -> new MenuOrderDTO.Get(
                                x.getMenuOrderId(), x.getQuantity(),
                                Menu.toMenuDTOOrderListItem(x.getMenu())))
                        .collect(Collectors.toList()));
    }
}
