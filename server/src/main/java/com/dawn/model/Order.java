package com.dawn.model;

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
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Order(int totalPrice, Store store) {
        this.totalPrice = totalPrice;
        this.store = store;

    }
}
