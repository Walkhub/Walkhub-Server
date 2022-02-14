package com.walkhub.walkhub.domain.rank.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.QUserRankVO;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.rank.domain.QUserRank.userRank;

@RequiredArgsConstructor
public class UserRankRepositoryCustomImpl implements UserRankRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final Long LIMIT = 100L;

    @Override
    public UserRankVO getMyRankByUserId(Long userId, Integer classNum, String dateType, LocalDate date) {
        return queryFactory
                .select(new QUserRankVO(
                        userRank.userId,
                        userRank.name,
                        userRank.grade,
                        userRank.classNum,
                        userRank.ranking,
                        userRank.profileImageUrl,
                        userRank.walkCount
                ))
                .from(userRank)
                .where(
                        userRank.userId.eq(userId),
                        classNumEq(classNum),
                        userRank.dateType.eq(dateType),
                        userRank.createdAt.eq(date)
                )
                .fetchOne();
    }

    @Override
    public List<UserRankVO> getUserRankListBySchoolId(Long schoolId, Integer classNum, String dateType, LocalDate date) {
        return queryFactory
                .select(new QUserRankVO(
                        userRank.userId,
                        userRank.name,
                        userRank.grade,
                        userRank.classNum,
                        userRank.ranking,
                        userRank.profileImageUrl,
                        userRank.walkCount
                ))
                .from(userRank)
                .where(
                        userRank.schoolId.eq(schoolId),
                        classNumEq(classNum),
                        userRank.dateType.eq(dateType),
                        userRank.createdAt.eq(date)
                )
                .limit(LIMIT)
                .orderBy(userRank.ranking.asc())
                .fetch();
    }

    private BooleanExpression schoolIdEq(Long schoolId) {
        return schoolId != null ? userRank.schoolId.eq(schoolId) : null;
    }

    private BooleanExpression

    private BooleanExpression classNumEq(Integer classNum) {
        return classNum != null ? userRank.scopeType.eq("CLASS").and(userRank.classNum.eq(classNum)) : userRank.scopeType.eq("SCHOOL");
    }
}
