package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.QueryChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Scope;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryChallengeListService {

	private final UserFacade userFacade;
	private final ChallengeRepository challengeRepository;

	@Transactional(readOnly = true)
	public QueryChallengeListResponse execute() {

		User user = userFacade.getCurrentUser();

		List<ChallengeResponse> challengeList = challengeRepository.findAllByScope(user.getSchool(), user.getGroup())
			.stream()
			.map(challenge -> ChallengeResponse.builder()
				.id(challenge.getId())
				.name(challenge.getName())
				.imageUrl(challenge.getImageUrl())
				.scope(challenge.getScope())
				.startAt(challenge.getCreateAt())
				.endAt(challenge.getEndAt())
				.build())
			.collect(Collectors.toList());

		return new QueryChallengeListResponse(challengeList);
	}
}
