package com.dawn.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MenuOrder {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int menuOrderId;

    @Column
    private int quantity;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Menu menu;

    public MenuOrder(int quantity, Order order, Menu menu) {
        this.quantity = quantity;
        this.order = order;
        this.menu = menu;
    }
}
