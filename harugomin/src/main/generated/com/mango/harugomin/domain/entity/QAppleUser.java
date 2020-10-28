package com.mango.harugomin.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppleUser is a Querydsl query type for AppleUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppleUser extends EntityPathBase<AppleUser> {

    private static final long serialVersionUID = -1961901525L;

    public static final QAppleUser appleUser = new QAppleUser("appleUser");

    public final StringPath userCode = createString("userCode");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAppleUser(String variable) {
        super(AppleUser.class, forVariable(variable));
    }

    public QAppleUser(Path<? extends AppleUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppleUser(PathMetadata metadata) {
        super(AppleUser.class, metadata);
    }

}

