package com.dawn.repository.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements CustomUserRepository {

    private static final Logger logger= LoggerFactory.getLogger(UserRepositoryImpl.class);

    @PersistenceContext
    EntityManager em;

    public UserRepositoryImpl() {
        super(UserRepositoryImpl.class);
    }


}
