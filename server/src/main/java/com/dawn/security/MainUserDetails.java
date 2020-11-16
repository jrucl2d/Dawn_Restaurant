package com.dawn.security;

import com.dawn.model.User;
import com.dawn.model.UserRole;
import com.dawn.repository.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class MainUserDetails implements UserDetails {

    private final User user;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("user = " + user);
        User newUser = userRepository.findUserByLoginId(user.getLoginId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(newUser.getUserType().toString()));
        List<UserRole> userRoles = newUser.getUserRoles();
        for (UserRole role : userRoles) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getAuthority());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
