package com.dawn.repository.staff;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepositoryImpl extends QuerydslRepositorySupport implements CustomStaffRepository {

    public StaffRepositoryImpl() {
        super(StaffRepositoryImpl.class);
    }
}
