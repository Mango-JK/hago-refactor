package com.mango.harugomin.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHistory is a Querydsl query type for History
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHistory extends EntityPathBase<History> {

    private static final long serialVersionUID = -60636678L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHistory history = new QHistory("history");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final NumberPath<Integer> commentNum = createNumber("commentNum", Integer.class);

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> hits = createNumber("hits", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final StringPath postImage = createString("postImage");

    public final StringPath tagName = createString("tagName");

    public final StringPath title = createString("title");

    public final QUser user;

    public QHistory(String variable) {
        this(History.class, forVariable(variable), INITS);
    }

    public QHistory(Path<? extends History> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHistory(PathMetadata metadata, PathInits inits) {
        this(History.class, metadata, inits);
    }

    public QHistory(Class<? extends History> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

