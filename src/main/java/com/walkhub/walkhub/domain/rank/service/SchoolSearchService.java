package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
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

    public SchoolListResponse execute(String name, SchoolDateType dateType) {
        List<SchoolResponse> schoolResponseList = schoolRankRepository
                .findAllByDateTypeAndNameContainingAndCreatedAtBetweenOrderByRankingAsc(dateType.toString(), name,
                        LocalDate
                                .now().minusWeeks(1), LocalDate.now())
                .stream()
                .map(this::schoolResponseBuilder)
                .collect(Collectors.toList());

        return new SchoolListResponse(schoolResponseList);
    }

    private SchoolResponse schoolResponseBuilder(SchoolRank schoolRank) {
        return SchoolResponse.builder()
                .schoolId(schoolRank.getSchoolId())
                .logoImageUrl(schoolRank.getLogoImageUrl())
                .schoolName(schoolRank.getName())
                .ranking(schoolRank.getRanking())
                .walkCount(schoolRank.getWalkCount())
                .build();
    }
}
