package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryParticipatedChallengeListService {

	private final ChallengeStatusRepository challengeStatusRepository;
	private final UserFacade userFacade;
	private final ChallengeFacade challengeFacade;

	@Transactional(readOnly = true)
	public QueryChallengeListResponse execute() {

		List<ChallengeResponse> challengeResponseList = challengeStatusRepository.findAllByUser(userFacade.getCurrentUser())
			.stream()
			.map(ChallengeStatus::getChallenge)
			.map(challengeFacade::challengeResponseBuilder)
			.collect(Collectors.toList());

		return new QueryChallengeListResponse(challengeResponseList);
	}
}
