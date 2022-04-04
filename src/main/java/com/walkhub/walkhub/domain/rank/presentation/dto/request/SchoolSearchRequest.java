package com.walkhub.walkhub.domain.rank.presentation.dto.request;

import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Scope;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchoolSearchRequest {
	private final String name;

	@NotNull(message = "sort는 Null일 수 없습니다.")
	private final Sort sort;

	@NotNull(message = "scope는 Null일 수 없습니다.")
	private final Scope scope;

	@NotNull(message = "schoolDateType는 Null일 수 없습니다.")
	private final SchoolDateType schoolDateType;
}
