package com.dawn.repository.store;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class StoreRepositoryImpl extends QuerydslRepositorySupport implements CustomStoreRepository {

    public StoreRepositoryImpl() {
        super(StoreRepositoryImpl.class);
    }
}
