package com.dawn.repository.menuorder;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MenuOrderRepositoryImpl extends QuerydslRepositorySupport implements CustomMenuOrderRepository {

    public MenuOrderRepositoryImpl() {
        super(MenuOrderRepositoryImpl.class);
    }
}
