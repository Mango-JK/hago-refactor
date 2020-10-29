package com.mango.harugomin.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLiker is a Querydsl query type for Liker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLiker extends EntityPathBase<Liker> {

    private static final long serialVersionUID = -385203551L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLiker liker = new QLiker("liker");

    public final QComment comment;

    public final NumberPath<Long> likerId = createNumber("likerId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QLiker(String variable) {
        this(Liker.class, forVariable(variable), INITS);
    }

    public QLiker(Path<? extends Liker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLiker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLiker(PathMetadata metadata, PathInits inits) {
        this(Liker.class, metadata, inits);
    }

    public QLiker(Class<? extends Liker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
    }

}

