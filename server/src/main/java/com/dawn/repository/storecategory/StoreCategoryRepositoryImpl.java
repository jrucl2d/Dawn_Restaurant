package com.dawn.repository.storecategory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class StoreCategoryRepositoryImpl extends QuerydslRepositorySupport implements CustomStoreCategory {

    public StoreCategoryRepositoryImpl() {
        super(StoreCategoryRepositoryImpl.class);
    }
}
