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
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int storeId;

    @Column
    private String storeTitle;

    @Column
    private String location;

    @Column
    private String businessHour;

    @Column
    private String description;

    @OneToMany
    private List<StoreCategory> storeCategories = new ArrayList<>();

    @OneToMany
    private List<Order> orders = new ArrayList<>();

    @OneToMany
    private List<Menu> menus = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Store(int storeId) {
        this.storeId = storeId;
    }

    public Store(String storeTitle, String location, String businessHour, String description, User user) {
        this.storeTitle = storeTitle;
        this.location = location;
        this.businessHour = businessHour;
        this.description = description;
        this.user = user;
    }
}
