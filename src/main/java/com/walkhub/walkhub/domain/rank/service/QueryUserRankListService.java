package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.facade.UserRankFacade;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse.UserRankResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryUserRankListService {

    private final UserRankRepository userRankRepository;
    private final UserRankFacade userRankFacade;

    public UserRankListResponse execute(Long schoolId, DateType dateType) {
        LocalDate now = LocalDate.now();
        List<UserRankVO> usersWeekOrMonthRank =
                userRankRepository.getUserRankListBySchoolId(schoolId, null, null, dateType, now, null);
        List<UserRankResponse> rankList = userRankFacade.buildWeekOrMonthUsersRankResponse(usersWeekOrMonthRank);

        return UserRankListResponse.builder()
                .rankList(rankList)
                .build();
    }

}
