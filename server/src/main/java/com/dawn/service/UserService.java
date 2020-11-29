package com.dawn.service;

import com.dawn.model.User;
import com.dawn.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public int getUserId(String loginId){
        return userRepository.getUserId(loginId);

    }
}
