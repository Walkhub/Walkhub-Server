package com.walkhub.walkhub.domain.rank.presentation.dto.request;

import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Scope;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchoolSearchRequest {
	private final String name;
	private final Sort sort;
	private final Scope scope;
	private final SchoolDateType schoolDateType;
}
