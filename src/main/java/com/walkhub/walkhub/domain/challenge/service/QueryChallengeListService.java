package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.Writer;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryChallengeListService {

	private final ChallengeRepository challengeRepository;
	private final UserFacade userFacade;

	@Transactional(readOnly = true)
	public QueryChallengeListResponse execute() {

		User user = userFacade.getCurrentUser();

		List<ChallengeResponse> challengeResponseList = challengeRepository
			.findAllByScope(user.getSchool(), user.getSection().getGrade(), user.getSection())
			.stream()
			.map(challenge -> ChallengeResponse.builder()
				.id(challenge.getId())
				.name(challenge.getName())
				.startAt(challenge.getStartAt())
				.endAt(challenge.getEndAt())
				.imageUrl(challenge.getImageUrl())
				.userScope(challenge.getUserScope())
				.goalScope(challenge.getGoalScope())
				.goalType(challenge.getGoalType())
				.writer(Writer.builder()
					.userId(challenge.getUser().getId())
					.name(challenge.getUser().getName())
					.profileImageUrl(challenge.getUser().getProfileImageUrl())
					.build())
				.build())
			.collect(Collectors.toList());

		return new QueryChallengeListResponse(challengeResponseList);
	}
}
