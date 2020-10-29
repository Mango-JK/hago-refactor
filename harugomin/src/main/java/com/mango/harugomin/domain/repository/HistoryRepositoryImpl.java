package com.mango.harugomin.domain.repository;

import com.mango.harugomin.domain.entity.QHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteAllByUsers(Long userId) {
        queryFactory
                .delete(QHistory.history)
                .where(QHistory.history.user.userId.eq(userId))
                .execute();
    }
}
