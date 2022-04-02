package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Scope;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse.SchoolResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SchoolSearchService {

    private final SchoolRankRepository schoolRankRepository;

    public SchoolListResponse execute(String name, Sort sort, Scope scope, SchoolDateType schoolDateType) {
        List<SchoolResponse> schoolResponseList = schoolRankRepository.getSchoolListAndSearch(
            name, sort, scope, schoolDateType
        );

        return new SchoolListResponse(schoolResponseList);
    }
}
