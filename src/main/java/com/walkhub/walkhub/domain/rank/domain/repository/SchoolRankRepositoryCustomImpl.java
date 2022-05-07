package com.walkhub.walkhub.domain.rank.domain.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.QSchoolListVo;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.SchoolListVo;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.rank.domain.QSchoolRank.schoolRank;

@RequiredArgsConstructor
public class SchoolRankRepositoryCustomImpl implements SchoolRankRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SchoolListVo> getSchoolSearch(Sort sort, SchoolDateType dateType, LocalDate createdAt, String name) {
        return queryFactory
                .select(new QSchoolListVo(
                        schoolRank.schoolId,
                        schoolRank.name,
                        schoolRank.ranking,
                        schoolRank.logoImageUrl,
                        schoolRank.walkCount,
                        schoolRank.userCount
                ))
                .from(schoolRank)
                .where(
                        dateTypeEq(dateType),
                        nameEq(name),
                        createdAtEq(createdAt)
                )
                .orderBy(schoolRankSort(sort))
                .fetch();
    }

    private BooleanExpression dateTypeEq(SchoolDateType dateType) {
        return dateType != null ? schoolRank.dateType.eq(dateType) : null;
    }

    private BooleanExpression nameEq(String name) {
        return name != null ? schoolRank.name.contains(name) : null;
    }

    private BooleanExpression createdAtEq(LocalDate date) {
        return date != null ? schoolRank.createdAt.eq(date) : null;
    }

    private OrderSpecifier<?> schoolRankSort(Sort sort) {
        if (Sort.NAME == sort) {
            return new OrderSpecifier<>(Order.ASC, schoolRank.name);
        } else {
            return new OrderSpecifier<>(Order.ASC, schoolRank.ranking);
        }
    }
}
