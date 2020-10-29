package com.mango.harugomin.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserHashtag is a Querydsl query type for UserHashtag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserHashtag extends EntityPathBase<UserHashtag> {

    private static final long serialVersionUID = -1415620633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserHashtag userHashtag = new QUserHashtag("userHashtag");

    public final QHashtag hashtag;

    public final QUser user;

    public final NumberPath<Long> userHashtagId = createNumber("userHashtagId", Long.class);

    public QUserHashtag(String variable) {
        this(UserHashtag.class, forVariable(variable), INITS);
    }

    public QUserHashtag(Path<? extends UserHashtag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserHashtag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserHashtag(PathMetadata metadata, PathInits inits) {
        this(UserHashtag.class, metadata, inits);
    }

    public QUserHashtag(Class<? extends UserHashtag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashtag = inits.isInitialized("hashtag") ? new QHashtag(forProperty("hashtag")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

