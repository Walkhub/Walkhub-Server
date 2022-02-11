package com.walkhub.walkhub.domain.rank.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.rank.domain.UserRank;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.rank.domain.QUserRank.userRank;

@RequiredArgsConstructor
public class UserRankRepositoryCustomImpl implements UserRankRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public UserRank getMyRankByAccountId(Long userId, Integer classNum, String dateType, LocalDate date) {
        return queryFactory
                .selectFrom(userRank)
                .where(
                        userRank.userId.eq(userId),
                        classNumEq(classNum),
                        userRank.dateType.eq(dateType),
                        userRank.createdAt.eq(date)
                )
                .fetchOne();
    }

    @Override
    public List<UserRank> getUserRankListBySchoolId(Long schoolId, Integer classNum, String dateType, LocalDate date) {
        return queryFactory
                .selectFrom(userRank)
                .where(
                        userRank.schoolId.eq(schoolId),
                        classNumEq(classNum),
                        userRank.dateType.eq(dateType),
                        userRank.createdAt.eq(date)
                )
                .fetch();
    }

    private BooleanExpression classNumEq(Integer classNum) {
        return classNum != null ? userRank.scopeType.eq("CLASS").and(userRank.classNum.eq(classNum)) : userRank.scopeType.eq("SCHOOL");
    }
}
