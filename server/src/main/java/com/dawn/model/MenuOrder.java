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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuOrderId;

    @Column
    private int quantity;

    @ManyToOne
    private DawnOrder order;

    @ManyToOne
    private Menu menu;

    public MenuOrder(int quantity, DawnOrder order, Menu menu) {
        this.quantity = quantity;
        this.order = order;
        this.menu = menu;
    }
}
