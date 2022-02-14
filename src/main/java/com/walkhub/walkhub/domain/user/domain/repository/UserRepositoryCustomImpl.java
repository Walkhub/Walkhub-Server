package com.walkhub.walkhub.domain.user.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import com.walkhub.walkhub.domain.teacher.vo.QUserListInfoVO;
import com.walkhub.walkhub.domain.teacher.vo.UserListInfoVO;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.SectionFacade;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.querydsl.QuerydslUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QuerydslUtil querydslUtil;
    private final SectionFacade sectionFacade;

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
                        user.authority.eq(Authority.TEACHER).as("isTeacher")
                ))
                .from(user)
                .leftJoin(user.exerciseAnalyses, exerciseAnalysis)
                .where(
                        user.school.eq(currentUser.getSchool()),
                        buildFilteringCondition(scope),
                        gradeEq(grade),
                        classNumEq(classNum)
                )
                .offset((long)page * size)
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
                return new OrderSpecifier[] {};
        }
    }
}
