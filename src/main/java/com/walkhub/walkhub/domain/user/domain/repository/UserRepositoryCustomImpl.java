package com.walkhub.walkhub.domain.user.domain.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import com.walkhub.walkhub.domain.teacher.vo.QUserListInfoVO;
import com.walkhub.walkhub.domain.teacher.vo.UserListInfoVO;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserListInfoVO> queryUserList(Integer page, AuthorityScope scope, SortStandard sort, Integer grade, Integer classNum, User currentUser) {
        long size = 4;
        return queryFactory
                .select(new QUserListInfoVO(
                        user.id.as("userId"),
                        user.name,
                        user.profileImageUrl,
                        user.section.grade,
                        user.section.classNum,
                        user.number,
                        MathExpressions.round(exerciseAnalysis.walkCount.avg(), 1).as("averageWalkCount"),
                        exerciseAnalysis.walkCount.sum().as("totalWalkCount"),
                        MathExpressions.round(exerciseAnalysis.distance.avg(), 1).as("averageDistance"),
                        exerciseAnalysis.distance.sum().as("totalDistance"),
                        user.authority.eq(Authority.TEACHER).as("isTeacher")
                ))
                .from(user)
                .join(user.exerciseAnalyses, exerciseAnalysis)
                .where(
                        user.school.eq(currentUser.getSchool()),
                        buildFilteringCondition(scope),
                        gradeEq(grade),
                        classNumEq(classNum),
                        exerciseAnalysis.date.after(LocalDate.now().minusDays(7))
                )
                .offset((long) page * size)
                .limit(size)
                .orderBy(buildSortCondition(sort))
                .groupBy(user.id)
                .fetch();
    }

    private BooleanExpression gradeEq(Integer grade) {
        return grade != null ? user.section.grade.eq(grade) : null;
    }

    private BooleanExpression classNumEq(Integer classNum) {
        return classNum != null ? user.section.classNum.eq(classNum) : null;
    }

    private BooleanExpression buildFilteringCondition(AuthorityScope scope) {
        switch (scope) {
            case ALL:
                return user.authority.eq(Authority.TEACHER).or(user.authority.eq(Authority.USER));
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
                        user.name.asc(), user.authority.asc()
                };
            case GCN:
                return new OrderSpecifier[]{
                        user.section.grade.asc(),
                        user.section.classNum.asc(),
                        user.number.asc(),
                        user.authority.asc()
                };
            case WALK_COUNT:
                return new OrderSpecifier[]{
                        exerciseAnalysis.walkCount.desc(),
                        user.authority.asc()
                };
            case DISTANCE:
                return new OrderSpecifier[]{
                        exerciseAnalysis.distance.desc(),
                        user.authority.asc()
                };
            default:
                return new OrderSpecifier[]{};
        }
    }
}
