package com.walkhub.walkhub.domain.rank.facade;

import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.OurSchoolUserRankListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserRankFacade {

    public List<OurSchoolUserRankListResponse.UserRankResponse> buildWeekOrMonthUsersRankResponse(List<UserRankVO> userRanks) {
        return userRanks.stream()
                .map(userRank -> OurSchoolUserRankListResponse.UserRankResponse.builder()
                        .userId(userRank.getUserId())
                        .name(userRank.getName())
                        .ranking(userRank.getRanking())
                        .profileImageUrl(userRank.getProfileImageUrl())
                        .walkCount(userRank.getWalkCount())
                        .build()
                ).collect(Collectors.toList());
    }

}
