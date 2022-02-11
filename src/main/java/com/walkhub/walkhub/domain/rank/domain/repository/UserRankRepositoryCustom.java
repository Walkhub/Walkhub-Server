package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.UserRank;

import java.time.LocalDate;
import java.util.List;

public interface UserRankRepositoryCustom {
    UserRank getMyRankByAccountId(Long accountId, Integer classNum, String dateType, LocalDate date);
    List<UserRank> getUserRankListBySchoolId(Long schoolId, Integer classNum, String dateType, LocalDate date);
}
