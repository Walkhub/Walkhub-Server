package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.LocationRepository;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserDetailsResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserDetailsResponse.ExerciseResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryUserDetailsService {

	private final UserFacade userFacade;
	private final LocationRepository locationRepository;
	private final ExerciseAnalysisRepository exerciseAnalysisRepository;

	@Transactional(readOnly = true)
	public QueryUserDetailsResponse execute(Long userId, LocalDate startAt, LocalDate endAt) {
		User user = userFacade.getUserById(userId);

		List<ExerciseResponse> exerciseList = user.getExerciseList()
			.stream()
			.map(locationRepository::findTop1ByExerciseOrderBySequenceDesc)
			.map(location -> ExerciseResponse.builder()
				.imageUrl(location.getExercise().getImageUrl())
				.startAt(location.getExercise().getCreatedAt())
				.latitude(location.getLatitude())
				.longitude(location.getLongitude())
				.build())
			.collect(Collectors.toList());

		List<Integer> walkCountList = exerciseAnalysisRepository.findAllByDateBetween(startAt, endAt)
			.stream()
			.map(ExerciseAnalysis::getWalkCount)
			.collect(Collectors.toList());

		return QueryUserDetailsResponse.builder()
			.userId(user.getId())
			.name(user.getName())
			.profileImageUrl(user.getProfileImageUrl())
			.grade(user.getSection().getGrade())
			.classNum(user.getSection().getClassNum())
			.number(user.getNumber())
			.walkCountList(walkCountList)
			.exerciseList(exerciseList)
			.build();
	}
}
