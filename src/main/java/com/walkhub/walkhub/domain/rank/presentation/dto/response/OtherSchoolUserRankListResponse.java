package com.walkhub.walkhub.domain.rank.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.walkhub.walkhub.domain.rank.presentation.dto.response.OurSchoolUserRankListResponse.UserRankResponse;

@Getter
@AllArgsConstructor
public class OtherSchoolUserRankListResponse {

    private final List<UserRankResponse> rankList;

}
