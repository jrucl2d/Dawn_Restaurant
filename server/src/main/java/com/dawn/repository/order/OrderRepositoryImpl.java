package com.dawn.repository.order;

import com.dawn.dto.OrderDTO;
import com.dawn.model.Order;
import com.dawn.model.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

    @PersistenceContext
    EntityManager em;

    public OrderRepositoryImpl() {
        super(OrderRepositoryImpl.class);
    }

    public void deleteAllOrderOfStore(int storeId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QOrder qOrder = new QOrder("qOrder");
        queryFactory
                .delete(qOrder)
                .where(qOrder.store.storeId.eq(storeId))
                .execute();
    }

    @Override
    public void updateStateOfOrder(OrderDTO.OrderStateUpdate order) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QOrder qOrder = new QOrder("qOrder");
        queryFactory
                .update(qOrder)
                .where(qOrder.orderId.eq(order.getOrderId()))
                .set(qOrder.orderStatus, order.getOrderStatus());
    }

    @Override
    public List<Order> findByStoreId(int storeId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QOrder qOrder = new QOrder("qOrder");
        return queryFactory.selectFrom(qOrder)
                .where(qOrder.store.storeId.eq(storeId))
                .fetch();
    }
}
