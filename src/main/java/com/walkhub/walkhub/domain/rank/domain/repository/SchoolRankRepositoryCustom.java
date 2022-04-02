package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Scope;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse.SchoolResponse;
import java.util.List;

public interface SchoolRankRepositoryCustom {
	List<SchoolResponse> getSchoolListAndSearch(String name, Sort sort, Scope scope, SchoolDateType schoolDateType);
}
