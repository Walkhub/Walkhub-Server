package com.walkhub.walkhub.domain.rank.facade;

import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListByMySchoolResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserRankFacade {

    private final UserFacade userFacade;

    public List<UserRankListResponse.UserRankResponse> buildWeekOrMonthUsersRankResponse(List<UserRankVO> userRanks) {
        return userRanks.stream()
            .map(userRank -> UserRankListResponse.UserRankResponse.builder()
                .userId(userRank.getUserId())
                .name(userRank.getName())
                .ranking(userRank.getRanking())
                .profileImageUrl(userRank.getProfileImageUrl())
                .walkCount(userRank.getWalkCount())
                .build()
            ).collect(Collectors.toList());
    }

    public List<UserRankListByMySchoolResponse.UserRankResponse> buildWeekOrMonthUsersRankResponseWithIsMeasuring(List<UserRankVO> userRanks) {
        return userRanks.stream()
                .map(userRank -> UserRankListByMySchoolResponse.UserRankResponse.builder()
                        .userId(userRank.getUserId())
                        .name(userRank.getName())
                        .ranking(userRank.getRanking())
                        .profileImageUrl(userRank.getProfileImageUrl())
                        .walkCount(userRank.getWalkCount())
                        .isMeasuring(isMeasuringByUserId(userRank.getUserId()))
                        .build()
                ).collect(Collectors.toList());
    }

    public Boolean isMeasuringByUserId(Long userId) {
        return userFacade.getUserById(userId).getIsMeasuring();
    }

}
