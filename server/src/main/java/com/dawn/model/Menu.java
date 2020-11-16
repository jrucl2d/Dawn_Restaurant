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

    private String menuTitle;

    private String menuDescription;

    private int price;

    private String imageURL;

    @ManyToOne
    private Store store;

    @OneToMany
    private List<MenuOrder> menuOrders = new ArrayList<>();


    public Menu(String menuTitle, String menuDescription, int price, String imageURL, Store store) {
        this.menuTitle = menuTitle;
        this.menuDescription = menuDescription;
        this.price = price;
        this.imageURL = imageURL;
        this.store = store;
    }
}
