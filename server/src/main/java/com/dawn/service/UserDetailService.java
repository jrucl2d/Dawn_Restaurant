package com.dawn.service;

import com.dawn.model.User;
import com.dawn.repository.user.UserRepository;
import com.dawn.security.MainUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        requireNonNull(username, "username is null");
        User user = userRepository.findUserByLoginId(username);
        MainUserDetails mainUserDetail = new MainUserDetails(user, userRepository);
        requireNonNull(mainUserDetail, "mainUserDetail is null");
        return mainUserDetail;
    }
}
