package com.dawn.repository.user;

import com.dawn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, CustomUserRepository {

    User findUserByUserId(int userId);
    User findUserByLoginId(String loginId);

    @Query("select u.userId from User u where u.loginId=?1")
    int getUserId(String loginId);
}
