package com.walkhub.walkhub.domain.user.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QQueryUserListResponse_UserListInfo;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
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
    public List<QueryUserListResponse.UserListInfo> queryUserList(Integer page, String scope, String sort, Integer grade, Integer classNum, User currentUser) {
        long size = 4;
        return queryFactory
                .select(new QQueryUserListResponse_UserListInfo(
                        user.id.as("userId"),
                        user.name,
                        user.profileImageUrl,
                        user.section.grade,
                        user.section.classNum,
                        user.number,
                        user.authority.eq(Authority.TEACHER).as("isTeacher")
                ))
                .from(user)
                .leftJoin(exerciseAnalysis)
                .on(exerciseAnalysis.user.eq(user))
                .where(
                        user.school.eq(currentUser.getSchool()),
                        buildFilteringCondition(scope),
                        gradeAndClassNumEq(grade, classNum)
                )
                .offset((long)page * size)
                .limit(size)
                .orderBy(buildSortCondition(sort))
                .fetch();
    }

    private BooleanBuilder gradeAndClassNumEq(Integer grade, Integer classNum) {
        return gradeEq(grade).and(classNumEq(classNum));
    }

    private BooleanBuilder gradeEq(Integer grade) {
        return querydslUtil.nullSafeBuilder(() -> user.section.grade.eq(grade));
    }

    private BooleanBuilder classNumEq(Integer classNum) {
        return querydslUtil.nullSafeBuilder(() -> user.section.classNum.eq(classNum));
    }

    private BooleanExpression buildFilteringCondition(String scope) {
        switch (scope) {
            case "ALL":
                return user.authority.eq(Authority.TEACHER).or(user.authority.eq(Authority.USER));
            case "STUDENT":
                return user.authority.eq(Authority.USER);
            case "TEACHER":
                return user.authority.eq(Authority.TEACHER);
            default:
                return null;
        }
    }

    private OrderSpecifier[] buildSortCondition(String sort) {
        switch (sort) {
            case "NAME":
                return new OrderSpecifier[]{
                        user.name.asc(), user.authority.asc()
                };
            case "GCN":
                return new OrderSpecifier[]{
                        user.section.grade.asc(),
                        user.section.classNum.asc(),
                        user.number.asc(),
                        user.authority.asc()
                };
            case "WALK_COUNT":
                return new OrderSpecifier[]{
                        exerciseAnalysis.walkCount.desc(),
                        user.authority.asc()
                };
            case "DISTANCE":
                return new OrderSpecifier[]{
                        exerciseAnalysis.distance.desc(),
                        user.authority.asc()
                };
            default:
                return new OrderSpecifier[] {};
        }
    }
}
