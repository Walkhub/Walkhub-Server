package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.exception.ChallengeNotFoundException;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.QueryChallengeDetailResponse;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.QueryChallengeDetailResponse.Writer;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Scope;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryChallengeDetailService {

	private final UserFacade userFacade;
	private final ChallengeRepository challengeRepository;
	private final ChallengeStatusRepository challengeStatusRepository;

	public QueryChallengeDetailResponse execute(Long id) {

		User user = userFacade.getCurrentUser();

		Challenge challenge = challengeRepository.findById(id)
			.filter(c -> roleFilter(c, user))
			.orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);

		User writer = challenge.getUser();
		Long participantCount = challengeStatusRepository.countByChallengeId(id);
		Boolean isMine = challengeStatusRepository.findByChallengeIdAndUserId(id, user.getId()).isPresent();

		return QueryChallengeDetailResponse.builder()
			.name(challenge.getName())
			.content(challenge.getContent())
			.goal(challenge.getGoal())
			.award(challenge.getAward())
			.imageUrl(challenge.getImageUrl())
			.startAt(challenge.getCreateAt())
			.endAt(challenge.getEndAt())
			.scope(challenge.getScope())
			.participantCount(participantCount)
			.isMine(isMine)
			.writer(Writer.builder()
				.id(writer.getId())
				.name(writer.getName())
				.profileImageUrl(writer.getProfileImageUrl())
				.build())
			.build();
	}

	private boolean roleFilter(Challenge challenge, User user) {
		if (!challenge.getScope().equals(Scope.ALL) && !challenge.getUser().getSchool().equals(user.getSchool())) {
			throw InvalidRoleException.EXCEPTION;
		}
		return true;
	}
}
