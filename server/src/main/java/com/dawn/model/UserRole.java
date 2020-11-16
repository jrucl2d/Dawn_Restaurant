package com.dawn.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority {

    public static final String USER_ROLE_MEMBER = "MEMBER";

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ROLE_NAME")
    private String roleName;

    protected UserRole() {}

    public UserRole(User user, String roleName) {
        this.user = user;
        this.roleName = roleName;
    }
    @Override
        public String getAuthority() {
            return roleName;
    }
}
