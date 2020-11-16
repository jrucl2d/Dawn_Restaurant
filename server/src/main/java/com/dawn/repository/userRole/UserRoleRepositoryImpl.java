package com.dawn.repository.userRole;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepositoryImpl extends QuerydslRepositorySupport implements CustomUserRoleRepository {

    public UserRoleRepositoryImpl() {
        super(UserRoleRepositoryImpl.class);
    }
}
