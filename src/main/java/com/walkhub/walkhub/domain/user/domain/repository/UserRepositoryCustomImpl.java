package com.walkhub.walkhub.domain.user.domain.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.vo.QUserDetailsVO;
import com.walkhub.walkhub.domain.user.domain.repository.vo.QUserListInfoVO;
import com.walkhub.walkhub.domain.user.domain.repository.vo.UserDetailsVO;
import com.walkhub.walkhub.domain.user.domain.repository.vo.UserListInfoVO;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserListInfoVO> searchUser(AuthorityScope scope, SortStandard sort, Integer grade, Integer classNum,
                                           User currentUser, String name) {
        return queryFactory
                .select(new QUserListInfoVO(
                        user.id,
                        user.name,
                        user.profileImageUrl,
                        section.grade,
                        section.classNum,
                        user.number,
                        MathExpressions.round(exerciseAnalysis.walkCount.avg(), 1).coalesce((double) 0),
                        exerciseAnalysis.walkCount.sum().coalesce(0),
                        MathExpressions.round(exerciseAnalysis.distance.avg(), 1).coalesce((double) 0),
                        exerciseAnalysis.distance.sum().coalesce(0),
                        user.authority.eq(Authority.TEACHER)
                ))
                .from(user)
                .leftJoin(user.exerciseAnalyses, exerciseAnalysis)
                .on(exerciseAnalysis.date.after(LocalDate.now().minusDays(7)))
                .leftJoin(user.section, section)
                .where(
                        user.school.eq(currentUser.getSchool()),
                        buildFilteringCondition(scope),
                        gradeEq(grade),
                        classNumEq(classNum),
                        nameEq(name)
                )
                .orderBy(buildSortCondition(sort))
                .groupBy(user.id, exerciseAnalysis.user)
                .fetch();
    }

    @Override
    public UserDetailsVO queryUserDetails(Long userId, LocalDate startAt, LocalDate endAt) {
        return queryFactory
                .select(new QUserDetailsVO(
                        user.id,
                        user.name,
                        user.profileImageUrl,
                        section.grade,
                        section.classNum,
                        user.number,
                        exerciseAnalysis.walkCount.avg().round().intValue().coalesce(0),
                        exerciseAnalysis.walkCount.sum().coalesce(0),
                        exerciseAnalysis.distance.avg().round().intValue().coalesce(0),
                        exerciseAnalysis.distance.sum().coalesce(0)
                ))
                .from(user)
                .leftJoin(user.section, section)
                .leftJoin(user.exerciseAnalyses, exerciseAnalysis)
                .on(exerciseAnalysis.date.between(startAt, endAt))
                .where(
                        user.id.eq(userId)
                )
                .fetchOne();
    }

    private BooleanExpression gradeEq(Integer grade) {
        return grade != null ? section.grade.eq(grade) : null;
    }

    private BooleanExpression classNumEq(Integer classNum) {
        return classNum != null ? section.classNum.eq(classNum) : null;
    }

    private BooleanExpression nameEq(String name) {
        return name == null || name.isEmpty() ? null : user.name.contains(name);
    }

    private BooleanExpression buildFilteringCondition(AuthorityScope scope) {
        switch (scope) {
            case ALL:
                return user.authority.eq(Authority.TEACHER)
                        .or(user.authority.eq(Authority.USER));
            case STUDENT:
                return user.authority.eq(Authority.USER);
            case TEACHER:
                return user.authority.eq(Authority.TEACHER);
            default:
                return null;
        }
    }

    private OrderSpecifier[] buildSortCondition(SortStandard sort) {
        switch (sort) {
            case NAME:
                return new OrderSpecifier[]{
                        user.name.asc()
                };
            case WALK_COUNT:
                return new OrderSpecifier[]{
                        exerciseAnalysis.walkCount.sum().desc(),
                        user.name.asc()
                };
            case DISTANCE:
                return new OrderSpecifier[]{
                        exerciseAnalysis.distance.sum().desc(),
                        user.name.asc()
                };
            default:
                return new OrderSpecifier[]{
                        section.grade.asc(),
                        section.classNum.asc(),
                        user.number.asc()
                };
        }
    }

}
