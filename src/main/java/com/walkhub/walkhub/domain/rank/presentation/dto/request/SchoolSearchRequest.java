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

	@NotNull
	private final Sort sort;

	@NotNull
	private final Scope scope;

	@NotNull
	private final SchoolDateType schoolDateType;
}
