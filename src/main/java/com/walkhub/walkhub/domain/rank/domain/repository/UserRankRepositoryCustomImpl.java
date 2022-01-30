package com.walkhub.walkhub.domain.rank.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.rank.domain.QUserRankInfo;
import com.walkhub.walkhub.domain.rank.domain.UserRankInfo;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.QUserRankListResponse_UserRankResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.walkhub.walkhub.domain.rank.domain.QUserRankInfo.*;

@RequiredArgsConstructor
public class UserRankRepositoryCustomImpl implements UserRankRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public UserRankInfo getMyRankByAccountId(String accountId) {
        return queryFactory
                .select(new QUserRankListResponse_UserRankResponse(

                ))
                .from(userRankInfo)
                .where(userRankInfo.accountId.eq(accountId))
                .fetchOne();
    }

    @Override
    public List<UserRankInfo> getUserRankListByAgencyCodeAndClassNum(String agencyCode, String classNum, String dateType) {
        /*return queryFactory
                .select(userRankInfo)
                .where(userRankInfo.agencyCode.eq(agencyCode))*/
        return null;
    }
}
