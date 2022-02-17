package com.walkhub.walkhub.domain.notice.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QQueryNoticeListResponse_NoticeResponse;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QQueryNoticeListResponse_Writer;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse.NoticeResponse;
import com.walkhub.walkhub.domain.school.domain.School;
import java.util.List;
import lombok.RequiredArgsConstructor;

import static com.walkhub.walkhub.domain.notice.domain.QNotice.notice;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;
import static com.walkhub.walkhub.domain.school.domain.QSchool.school;

@RequiredArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom{
	private final JPAQueryFactory queryFactory;

	@Override
	public List<NoticeResponse> queryNoticeByScopeAndPage(Scope scope, Integer page, School userSchool) {
		long size = 3;
		return queryFactory
			.select(new QQueryNoticeListResponse_NoticeResponse(
					notice.id,
					notice.title,
					notice.content,
					notice.createdAt,
					new QQueryNoticeListResponse_Writer(
						user.id,
						user.name,
						user.profileImageUrl
					))
				)
			.from(notice)
			.join(notice.user, user)
			.join(user.school, school)
			.where(
				notice.scope.eq(scope),
				scopeFilter(scope, userSchool)
			)
			.offset((long) page * size)
			.limit(size)
			.orderBy(notice.createdAt.desc())
			.fetch();
	}

	private BooleanExpression scopeFilter(Scope scope, School userSchool) {
		return scope.equals(Scope.SCHOOL) ? school.eq(userSchool) : null;
	}
}
