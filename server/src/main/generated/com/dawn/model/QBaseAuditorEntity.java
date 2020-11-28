package com.dawn.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAuditorEntity is a Querydsl query type for BaseAuditorEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseAuditorEntity extends EntityPathBase<BaseAuditorEntity> {

    private static final long serialVersionUID = -320222466L;

    public static final QBaseAuditorEntity baseAuditorEntity = new QBaseAuditorEntity("baseAuditorEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public QBaseAuditorEntity(String variable) {
        super(BaseAuditorEntity.class, forVariable(variable));
    }

    public QBaseAuditorEntity(Path<? extends BaseAuditorEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAuditorEntity(PathMetadata metadata) {
        super(BaseAuditorEntity.class, metadata);
    }

}

