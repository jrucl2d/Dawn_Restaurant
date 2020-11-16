package com.dawn.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StoreCategory {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int storeCategoryId;

    private String categoryTitle;

    @ManyToOne
    private Store store;

    public StoreCategory(String categoryTitle, Store store) {
        this.categoryTitle = categoryTitle;
        this.store = store;
    }
}
