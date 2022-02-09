package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.LocationRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.ExerciseListResponse;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.ExerciseListResponse.ExerciseResponse;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryExerciseListService {

	private final ExerciseRepository exerciseRepository;
	private final LocationRepository locationRepository;
	private final UserFacade userFacade;


	public ExerciseListResponse execute() {
		List<ExerciseResponse> exerciseList = exerciseRepository.findByUser(userFacade.getCurrentUser()).stream()
			.map(locationRepository::findTop1ByExerciseOrderByOrderDesc)
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
