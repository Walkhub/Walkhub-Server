package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsResponse.Writer;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.UserScope;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryChallengeDetailsService {

	private final ChallengeFacade challengeFacade;
	private final UserFacade userFacade;

	@Transactional(readOnly = true)
	public QueryChallengeDetailsResponse execute(Long challengeId) {

		Challenge challenge = challengeFacade.getChallengeById(challengeId);
		User user = userFacade.getCurrentUser();
		User writer = challenge.getUser();

		if (!challenge.getUserScope().equals(UserScope.ALL) && !user.getSchool().equals(writer.getSchool())) {
			throw InvalidRoleException.EXCEPTION;
		}

		Boolean isMine = challenge.getChallengeStatuses()
			.stream()
			.anyMatch(challengeStatus -> challengeStatus.getUser().equals(user));

		return QueryChallengeDetailsResponse.builder()
			.name(challenge.getName())
			.content(challenge.getContent())
			.userScope(challenge.getUserScope())
			.goal(challenge.getGoal())
			.goalScope(challenge.getGoalScope())
			.goalType(challenge.getGoalType())
			.award(challenge.getAward())
			.imageUrl(challenge.getImageUrl())
			.startAt(challenge.getStartAt())
			.endAt(challenge.getEndAt())
			.participantCount((long) challenge.getChallengeStatuses().size())
			.isMine(isMine)
			.isParticipated(user.equals(writer))
			.successStandard(challenge.getSuccessStandard())
			.writer(Writer.builder()
				.userId(writer.getId())
				.name(writer.getName())
				.profileImageUrl(writer.getProfileImageUrl())
				.build())
			.build();
	}
}