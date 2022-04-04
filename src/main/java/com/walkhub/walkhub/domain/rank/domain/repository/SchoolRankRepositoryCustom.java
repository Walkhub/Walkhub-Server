package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.repository.vo.SchoolListVo;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Scope;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
import java.util.List;

public interface SchoolRankRepositoryCustom {
	List<SchoolListVo> getSchoolListAndSearch(String name, Sort sort, Scope scope, SchoolDateType schoolDateType);
}
