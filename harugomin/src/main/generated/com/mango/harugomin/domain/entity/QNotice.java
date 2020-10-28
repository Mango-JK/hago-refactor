package com.mango.harugomin.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 1006662834L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotice notice = new QNotice("notice");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> isRead = createNumber("isRead", Integer.class);

    public final StringPath message = createString("message");

    public final NumberPath<Long> noticeId = createNumber("noticeId", Long.class);

    public final StringPath returnUrl = createString("returnUrl");

    public final QComment targetCommentId;

    public final QPost targetPostId;

    public final QUser targetUserId;

    public final NumberPath<Integer> typeId = createNumber("typeId", Integer.class);

    public QNotice(String variable) {
        this(Notice.class, forVariable(variable), INITS);
    }

    public QNotice(Path<? extends Notice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotice(PathMetadata metadata, PathInits inits) {
        this(Notice.class, metadata, inits);
    }

    public QNotice(Class<? extends Notice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.targetCommentId = inits.isInitialized("targetCommentId") ? new QComment(forProperty("targetCommentId"), inits.get("targetCommentId")) : null;
        this.targetPostId = inits.isInitialized("targetPostId") ? new QPost(forProperty("targetPostId"), inits.get("targetPostId")) : null;
        this.targetUserId = inits.isInitialized("targetUserId") ? new QUser(forProperty("targetUserId")) : null;
    }

}

