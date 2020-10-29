package com.mango.harugomin.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.mango.harugomin.domain.entity.QHistory.history;

@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteAllByUsers(Long userId) {
        queryFactory.delete(history).where(history.user.userId.eq(userId)).execute();
    }
}
