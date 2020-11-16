package com.dawn.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStaff is a Querydsl query type for Staff
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStaff extends EntityPathBase<Staff> {

    private static final long serialVersionUID = -759523922L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStaff staff = new QStaff("staff");

    public final DateTimePath<java.util.Date> birthDate = createDateTime("birthDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath position = createString("position");

    public final BooleanPath sex = createBoolean("sex");

    public final NumberPath<Integer> staffId = createNumber("staffId", Integer.class);

    public final QStore store;

    public final NumberPath<Integer> wagePerHour = createNumber("wagePerHour", Integer.class);

    public QStaff(String variable) {
        this(Staff.class, forVariable(variable), INITS);
    }

    public QStaff(Path<? extends Staff> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStaff(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStaff(PathMetadata metadata, PathInits inits) {
        this(Staff.class, metadata, inits);
    }

    public QStaff(Class<? extends Staff> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

