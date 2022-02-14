package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;

import java.time.LocalDate;
import java.util.List;

public interface UserRankRepositoryCustom {
    UserRankVO getMyRankByUserId(Long userId, Integer classNum, String dateType, LocalDate date);
    List<UserRankVO> getUserRankListBySchoolId(Long schoolId, Integer classNum, String dateType, LocalDate date);
}
