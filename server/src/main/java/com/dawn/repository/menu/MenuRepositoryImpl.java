package com.dawn.repository.menu;

import com.dawn.model.Menu;
import com.dawn.model.QMenu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MenuRepositoryImpl extends QuerydslRepositorySupport implements CustomMenuRepository {

    @PersistenceContext
    EntityManager em;

    public MenuRepositoryImpl() {
        super(MenuRepositoryImpl.class);
    }

    @Override
    public List<Menu> findByStoreId(int storeId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMenu qMenu = new QMenu("qMenu");
        return queryFactory.selectFrom(qMenu)
                    .where(qMenu.store.storeId.eq(storeId)).fetch();
    }

}
