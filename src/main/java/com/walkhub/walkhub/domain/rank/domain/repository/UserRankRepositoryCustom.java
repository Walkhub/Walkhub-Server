package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.UserRankInfo;

import java.util.List;

public interface UserRankRepositoryCustom {
    UserRankInfo getMyRankByAccountId(String accountId);
    List<UserRankInfo> getUserRankListByAgencyCodeAndClassNum(String agencyCode, String classNum, String dateType);
}
