package com.walkhub.walkhub.domain.rank.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.QUserRankVO;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.rank.domain.QUserRank.userRank;

@RequiredArgsConstructor
public class UserRankRepositoryCustomImpl implements UserRankRepositoryCustom {

    private static final Long LIMIT = 100L;
    private final JPAQueryFactory queryFactory;

    @Override
    public UserRankVO getMyRankByUserId(Long userId, Integer grade, Integer classNum, DateType dateType,
                                        LocalDate date, UserRankScope scope) {
        return queryFactory
                .select(new QUserRankVO(
                        userRank.userId,
                        userRank.name,
                        userRank.ranking,
                        userRank.profileImageUrl,
                        userRank.walkCount
                ))
                .from(userRank)
                .where(
                        userIdEq(userId),
                        classNumEq(classNum),
                        dateTypeEq(dateType),
                        createdAtEq(date),
                        scopeTypeEq(scope)
                )
                .fetchOne();
    }

    @Override
    public List<UserRankVO> getUserRankListBySchoolId(Long schoolId, Integer grade, Integer classNum,
                                                      DateType dateType, LocalDate date, UserRankScope scope) {

        return queryFactory
                .select(new QUserRankVO(
                        userRank.userId,
                        userRank.name,
                        userRank.ranking,
                        userRank.profileImageUrl,
                        userRank.walkCount
                ))
                .from(userRank)
                .where(
                        schoolIdEq(schoolId),
                        gradeEq(grade),
                        classNumEq(classNum),
                        dateTypeEq(dateType),
                        createdAtEq(date),
                        scopeTypeEq(scope)
                )
                .limit(LIMIT)
                .orderBy(userRank.ranking.asc())
                .fetch();
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? userRank.userId.eq(userId) : null;
    }

    private BooleanExpression schoolIdEq(Long schoolId) {
        return schoolId != null ? userRank.schoolId.eq(schoolId) : null;
    }

    private BooleanExpression gradeEq(Integer grade) {
        return grade != null ? userRank.grade.eq(grade) : null;
    }

    private BooleanExpression classNumEq(Integer classNum) {
        return classNum != null ? userRank.scopeType.eq(UserRankScope.CLASS).and(userRank.classNum.eq(classNum)) : null;
    }

    private BooleanExpression dateTypeEq(DateType dateType) {
        switch (dateType) {
            case WEEK:
                return userRank.dateType.eq(DateType.WEEK);
            case MONTH:
                return userRank.dateType.eq(DateType.MONTH);
            default:
                return null;
        }
    }

    private BooleanExpression createdAtEq(LocalDate date) {
        return date != null ? userRank.createdAt.eq(date) : null;
    }

    private BooleanExpression scopeTypeEq(UserRankScope scope) {
        return scope != null ? userRank.scopeType.eq(scope) : null;
    }
}
