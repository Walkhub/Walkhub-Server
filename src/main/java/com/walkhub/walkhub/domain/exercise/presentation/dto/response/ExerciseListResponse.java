package com.walkhub.walkhub.domain.exercise.presentation.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class ExerciseListResponse {

	private final List<ExerciseResponse> exerciseList;

	@Getter
	@Builder
	public static class ExerciseResponse {
		private final Long exerciseId;
		private final String imageUrl;

		@DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:SS")
		private final LocalDateTime startAt;
		private final BigDecimal latitude;
		private final BigDecimal longitude;
	}
}
