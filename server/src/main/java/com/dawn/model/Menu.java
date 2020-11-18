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
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int menuId;

    @Column
    private String menuTitle;

    @Column
    private String menuDescription;

    @Column
    private int price;

    @Column
    private String imageURL;

    @ManyToOne
    private Store store;

    @OneToMany
    private List<MenuOrder> menuOrders = new ArrayList<>();

    public Menu(int menuId) {
        this.menuId = menuId;
    }

    public Menu(String menuTitle, String menuDescription, int price, String imageURL, Store store) {
        this.menuTitle = menuTitle;
        this.menuDescription = menuDescription;
        this.price = price;
        this.imageURL = imageURL;
        this.store = store;
    }
}
