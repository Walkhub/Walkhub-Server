package com.walkhub.walkhub.domain.rank.domain.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.QSchoolListVo;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.SchoolListVo;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Scope;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
import java.util.List;
import lombok.RequiredArgsConstructor;

import static com.walkhub.walkhub.domain.rank.domain.QSchoolRank.schoolRank;

@RequiredArgsConstructor
public class SchoolRankRepositoryCustomImpl implements SchoolRankRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<SchoolListVo> getSchoolListAndSearch(String name, Sort sort, Scope scope,
		SchoolDateType schoolDateType) {
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
				schoolRank.dateType.eq(schoolDateType.name()),
				nameLike(name)
			)
			.orderBy(sort(sort))
			.fetch();
	}

	private BooleanExpression scopeFilter(Scope scope) {
		//보류
		return null;
	}

	private BooleanExpression nameLike(String name) {
		return name != null ? schoolRank.name.contains(name) : null;
	}

	private OrderSpecifier<?> sort(Sort sort) {
		switch (sort) {
			case RANK:
				return schoolRank.ranking.asc();
			case NAME:
				return schoolRank.name.asc();
			default:
				return null;
		}
	}
}
