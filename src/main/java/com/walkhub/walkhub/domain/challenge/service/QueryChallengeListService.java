package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChallengeListService {

    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;
    private final ChallengeFacade challengeFacade;

    @Transactional(readOnly = true)
    public QueryChallengeListResponse execute() {
        User user = userFacade.getCurrentUser();

        List<ChallengeResponse> challengeResponseList = challengeRepository.queryChallenge(user)
                .stream()
                .map(challengeFacade::challengeResponseBuilder)
                .collect(Collectors.toList());

        return new QueryChallengeListResponse(challengeResponseList);
    }

}
