package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;

import java.time.LocalDate;
import java.util.List;

public interface UserRankRepositoryCustom {
    UserRankListResponse.UserRankResponse getMyRankByUserId(Long userId, Integer classNum, String dateType, LocalDate date);
    List<UserRankListResponse.UserRankResponse> getUserRankListBySchoolId(Long schoolId, Integer classNum, String dateType, LocalDate date);
}
