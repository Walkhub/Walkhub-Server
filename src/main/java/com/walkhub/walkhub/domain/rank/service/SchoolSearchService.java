package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.SchoolListVo;
import com.walkhub.walkhub.domain.rank.presentation.dto.request.SchoolSearchRequest;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse.SchoolResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SchoolSearchService {

    private final SchoolRankRepository schoolRankRepository;

    public SchoolListResponse execute(SchoolSearchRequest request) {
        LocalDate date = LocalDate.now().minusDays(1);

        List<SchoolResponse> schoolRankList = schoolRankRepository
                .getSchoolSearch(request.getSchoolDateType(), date, request.getName(), request.getSort())
                .stream()
                .map(this::schoolResponse)
                .collect(Collectors.toList());

        return new SchoolListResponse(schoolRankList);
    }

    private SchoolResponse schoolResponse(SchoolListVo vo) {
        return SchoolResponse.builder()
                .schoolId(vo.getSchoolId())
                .walkCount(vo.getWalkCount())
                .userCount(vo.getUserCount())
                .schoolName(vo.getSchoolName())
                .ranking(vo.getRanking())
                .logoImageUrl(vo.getLogoImageUrl())
                .build();
    }
}
