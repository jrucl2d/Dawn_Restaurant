package com.dawn.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDawnOrder is a Querydsl query type for DawnOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDawnOrder extends EntityPathBase<DawnOrder> {

    private static final long serialVersionUID = 1436831592L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDawnOrder dawnOrder = new QDawnOrder("dawnOrder");

    public final NumberPath<Integer> dawnOrderId = createNumber("dawnOrderId", Integer.class);

    public final BooleanPath isPayed = createBoolean("isPayed");

    public final ListPath<MenuOrder, QMenuOrder> menuOrders = this.<MenuOrder, QMenuOrder>createList("menuOrders", MenuOrder.class, QMenuOrder.class, PathInits.DIRECT2);

    public final EnumPath<OrderStatus> orderStatus = createEnum("orderStatus", OrderStatus.class);

    public final QStore store;

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QDawnOrder(String variable) {
        this(DawnOrder.class, forVariable(variable), INITS);
    }

    public QDawnOrder(Path<? extends DawnOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDawnOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDawnOrder(PathMetadata metadata, PathInits inits) {
        this(DawnOrder.class, metadata, inits);
    }

    public QDawnOrder(Class<? extends DawnOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

