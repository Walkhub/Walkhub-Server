package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.repository.vo.SchoolListVo;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;

import java.time.LocalDate;
import java.util.List;

public interface SchoolRankRepositoryCustom {

    List<SchoolListVo> getSchoolSearch(SchoolDateType dateType, LocalDate createdAt, String name, Sort sort);
}
