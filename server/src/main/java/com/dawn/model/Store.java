package com.dawn.model;


import com.dawn.dto.StoreDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.dawn.common.CloudConstatns.CloudStorageBaseURL;

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

    @Column
    private String profileImageURL;

    @OneToMany
    private List<Store> stores = new ArrayList<>();

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

    public Store(String storeTitle, String location, String businessHour, String description, String profileImageURL, User user) {
        this.storeTitle = storeTitle;
        this.location = location;
        this.businessHour = businessHour;
        this.description = description;
        this.profileImageURL = profileImageURL;
        this.user = user;
    }

    public static StoreDTO.GetStore toGetStore(Store store) {
        return new StoreDTO.GetStore(
                store.getStoreId(), store.getStoreTitle(), store.getLocation(),
                store.getBusinessHour(), store.getDescription(), CloudStorageBaseURL + "/" + store.getProfileImageURL());
    }
}
