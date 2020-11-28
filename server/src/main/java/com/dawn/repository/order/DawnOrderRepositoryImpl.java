package com.dawn.repository.order;

import com.dawn.dto.OrderDTO;
import com.dawn.model.DawnOrder;
import com.dawn.model.QDawnOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class DawnOrderRepositoryImpl extends QuerydslRepositorySupport implements CustomDawnOrderRepository {

    @PersistenceContext
    EntityManager em;

    public DawnOrderRepositoryImpl() {
        super(DawnOrderRepositoryImpl.class);
    }

    public void deleteAllOrderOfStore(int storeId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QDawnOrder qOrder = new QDawnOrder("qOrder");
        queryFactory
                .delete(qOrder)
                .where(qOrder.store.storeId.eq(storeId))
                .execute();
    }

    @Override
    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QDawnOrder qOrder = new QDawnOrder("qOrder");
        queryFactory
                .update(qOrder)
                .where(qOrder.dawnOrderId.eq(order.getOrderId()))
                .set(qOrder.orderStatus, order.getOrderStatus())
                .execute();
    }

    @Override
    public List<DawnOrder> findByStoreId(int storeId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QDawnOrder qOrder = new QDawnOrder("qOrder");
        return queryFactory.selectFrom(qOrder)
                .where(qOrder.store.storeId.eq(storeId))
                .fetch();
    }
}
