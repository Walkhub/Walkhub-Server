package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryParticipatedChallengeListService {

    private final ChallengeStatusRepository challengeStatusRepository;
    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;

    public QueryChallengeListResponse execute() {

        List<ChallengeResponse> challengeResponseList =
                challengeStatusRepository.getAllChallengesByUser(userFacade.getCurrentUser())
                        .stream()
                        .map(challengeFacade::challengeResponseBuilder)
                        .collect(Collectors.toList());


        return new QueryChallengeListResponse(challengeResponseList);
    }
}
