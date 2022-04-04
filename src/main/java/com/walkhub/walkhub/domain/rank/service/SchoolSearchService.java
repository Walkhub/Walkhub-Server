package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.SchoolListVo;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.domain.type.Scope;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
import com.walkhub.walkhub.domain.rank.presentation.dto.request.SchoolSearchRequest;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse.SchoolResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SchoolSearchService {

    private final SchoolRankRepository schoolRankRepository;

    public SchoolListResponse execute(SchoolSearchRequest request) {
        List<SchoolListVo> schoolResponseList = schoolRankRepository.getSchoolListAndSearch(
            request.getName(),
            request.getSort(),
            request.getScope(),
            request.getSchoolDateType()
        );

        return new SchoolListResponse(
            schoolResponseList
                .stream()
                .map(this::schoolResponseBuilder)
                .collect(Collectors.toList())
            );
    }

    private SchoolResponse schoolResponseBuilder(SchoolListVo schoolListVo) {
        return SchoolResponse.builder()
            .schoolId(schoolListVo.getSchoolId())
            .walkCount(schoolListVo.getWalkCount())
            .userCount(schoolListVo.getUserCount())
            .schoolName(schoolListVo.getSchoolName())
            .ranking(schoolListVo.getRanking())
            .logoImageUrl(schoolListVo.getLogoImageUrl())
            .build();
    }
}
