package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.LocationRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.ExerciseListResponse;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.ExerciseListResponse.ExerciseResponse;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryExerciseListService {

	private final ExerciseRepository exerciseRepository;
	private final LocationRepository locationRepository;
	private final UserFacade userFacade;

	public ExerciseListResponse execute() {
		List<ExerciseResponse> exerciseList = exerciseRepository.findAllByUser(userFacade.getCurrentUser()).stream()
			.map(locationRepository::findTop1ByExerciseOrderBySequenceDesc)
			.map(location -> ExerciseResponse.builder()
				.exerciseId(location.getExercise().getId())
				.imageUrl(location.getExercise().getImageUrl())
				.startAt(location.getExercise().getCreatedAt())
				.latitude(location.getLatitude())
				.longitude(location.getLongitude())
				.build())
			.collect(Collectors.toList());

		return new ExerciseListResponse(exerciseList);
	}
}
