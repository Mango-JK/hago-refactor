package com.mango.harugomin.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.mango.harugomin.domain.entity.QHashtag.hashtag;

@RequiredArgsConstructor
public class HashtagRepositoryImpl implements HashtagRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public void countUp(Long tagId) {
        queryFactory
                .update(hashtag)
                .where(hashtag.tagId.eq(tagId))
                .set(hashtag.postingCount, hashtag.postingCount.add(1))
                .execute();
    }
}
