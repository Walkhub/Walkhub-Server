package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.global.enums.DateType;

import java.time.LocalDate;
import java.util.List;

public interface UserRankRepositoryCustom {
    UserRankVO getMyRankByUserId(Long userId, Integer classNum, DateType dateType, LocalDate date);
    List<UserRankVO> getUserRankListBySchoolId(Long schoolId, Integer classNum, DateType dateType, LocalDate date);
}