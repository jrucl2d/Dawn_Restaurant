package com.dawn.repository.user;

import com.dawn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, CustomUserRepository {

    User findUserByUserId(int userId);
    User findUserByLoginId(String loginId);
}
