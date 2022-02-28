package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.facade.UserRankFacade;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.OtherSchoolUserRankListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.OurSchoolUserRankListResponse;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryUserRankListService {

    private final UserRankRepository userRankRepository;
    private final UserRankFacade userRankFacade;

    @Transactional(readOnly = true)
    public OtherSchoolUserRankListResponse execute(Long schoolId, DateType dateType) {
        LocalDate now = LocalDate.now();
        List<UserRankVO> usersWeekOrMonthRank = userRankRepository.getUserRankListBySchoolId(schoolId, null, null, dateType, now);
        List<OurSchoolUserRankListResponse.UserRankResponse> rankList = userRankFacade.buildWeekOrMonthUsersRankResponse(usersWeekOrMonthRank);

        return new OtherSchoolUserRankListResponse(rankList);
    }

}
