package com.dawn.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuOrder is a Querydsl query type for MenuOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuOrder extends EntityPathBase<MenuOrder> {

    private static final long serialVersionUID = 1533722877L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuOrder menuOrder = new QMenuOrder("menuOrder");

    public final QMenu menu;

    public final NumberPath<Integer> menuOrderId = createNumber("menuOrderId", Integer.class);

    public final QOrder order;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QMenuOrder(String variable) {
        this(MenuOrder.class, forVariable(variable), INITS);
    }

    public QMenuOrder(Path<? extends MenuOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuOrder(PathMetadata metadata, PathInits inits) {
        this(MenuOrder.class, metadata, inits);
    }

    public QMenuOrder(Class<? extends MenuOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

