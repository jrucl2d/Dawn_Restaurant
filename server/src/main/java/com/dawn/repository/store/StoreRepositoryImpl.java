package com.dawn.repository.store;

import com.dawn.model.QStore;
import com.dawn.model.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StoreRepositoryImpl extends QuerydslRepositorySupport implements CustomStoreRepository {

    @PersistenceContext
    EntityManager em;

    public StoreRepositoryImpl() {
        super(StoreRepositoryImpl.class);
    }

    @Override
    public List<Store> getAllStoreOfUserByUserId(int userId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QStore qStore = new QStore("qStore");
        return queryFactory.selectFrom(qStore)
                .where(qStore.user.userId.eq(userId)).fetch();
    }

    @Override
    public void deleteAllStoreOfUserByUserId(int userId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QStore qStore = new QStore("qStore");
        queryFactory
                .delete(qStore)
                .where(qStore.user.userId.eq(userId))
                .execute();
    }
}
