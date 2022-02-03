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
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryChallengeDetailService {

	private final UserFacade userFacade;
	private final ChallengeRepository challengeRepository;
	private final ChallengeStatusRepository challengeStatusRepository;

	@Transactional(readOnly = true)
	public QueryChallengeDetailResponse execute(Long id) {

		User user = userFacade.getCurrentUser();

		Challenge challenge = challengeRepository.findChallengeById(id)
			.filter(c -> roleFilter(c, c.getUser(), user))
			.orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);

		User writer = challenge.getUser();

		return QueryChallengeDetailResponse.builder()
			.name(challenge.getName())
			.content(challenge.getContent())
			.goal(challenge.getGoal())
			.award(challenge.getAward())
			.imageUrl(challenge.getImageUrl())
			.startAt(challenge.getCreateAt())
			.endAt(challenge.getEndAt())
			.scope(challenge.getScope())
			.participantCount((long) challenge.getChallengeStatuses().size())
			.isMine(challenge.getChallengeStatuses()
				.stream()
				.anyMatch(challengeStatus -> challengeStatus.getUser().equals(user)))
			.writer(Writer.builder()
				.id(writer.getId())
				.name(writer.getName())
				.profileImageUrl(writer.getProfileImageUrl())
				.build())
			.build();
	}

	private boolean roleFilter(Challenge challenge, User writer, User user) {
		if (!challenge.getScope().equals(Scope.ALL) && !writer.getSchool().equals(user.getSchool())) {
			throw InvalidRoleException.EXCEPTION;
		}
		return true;
	}
}
