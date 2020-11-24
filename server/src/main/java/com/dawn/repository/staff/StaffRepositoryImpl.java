package com.dawn.repository.staff;

import com.dawn.model.QStaff;
import com.dawn.model.Staff;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StaffRepositoryImpl extends QuerydslRepositorySupport implements CustomStaffRepository {

    @PersistenceContext
    EntityManager em;

    public StaffRepositoryImpl() {
        super(StaffRepositoryImpl.class);
    }

    @Override
    public List<Staff> findByStoreByStoreId(int storeId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QStaff qStaff = new QStaff("qStaff");
        return queryFactory.selectFrom(qStaff)
                .where(qStaff.store.storeId.eq(storeId))
                .fetch();
    }
}
