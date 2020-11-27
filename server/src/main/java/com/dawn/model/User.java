package com.dawn.model;

import com.dawn.repository.user.UserType;
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
public class User extends BaseAuditorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "userId")
    private int userId;

    private boolean isBusiness;

    private String loginId;

    private String password;

    private String name;

    private int balance;

    @Column(nullable = true)
    private UserType userType = UserType.USER;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany
    private List<Store> stores = new ArrayList<>();

    @OneToMany
    private List<DawnOrder> orders = new ArrayList<>();

    public User(String loginId, String password, String name, boolean isBusiness) {
        this.isBusiness = isBusiness;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.balance = 100000;
    }

    public User(String loginId, String password, String name, boolean isBusiness, int balance) {
        this(loginId, password, name, isBusiness);
        this.balance = balance;
    }
}
