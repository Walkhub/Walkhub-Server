package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.type.Sort;
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
        List<SchoolRank> schoolList;
        LocalDate date = LocalDate.now().minusDays(1);

        if (request.getName() != null) {
            if (Sort.NAME == request.getSort()) {
                schoolList = schoolRankRepository
                        .findAllByDateTypeAndCreatedAtAndNameContainingOrderByName(
                                request.getSchoolDateType(),
                                date,
                                request.getName()
                        );
            } else {
                schoolList = schoolRankRepository
                        .findAllByDateTypeAndCreatedAtAndNameContainingOrderByRanking(
                                request.getSchoolDateType(),
                                date,
                                request.getName()
                        );
            }
        } else {
            if (Sort.NAME == request.getSort()) {
                schoolList = schoolRankRepository
                        .findAllByDateTypeAndCreatedAtOrderByName(request.getSchoolDateType(), date);
            } else {
                schoolList = schoolRankRepository
                        .findAllByDateTypeAndCreatedAtOrderByRanking(request.getSchoolDateType(), date);
            }
        }

        List<SchoolResponse> schoolResponse = schoolList.stream()
                .map(this::schoolResponse)
                .collect(Collectors.toList());

        return new SchoolListResponse(schoolResponse);
    }

    private SchoolResponse schoolResponse(SchoolRank schoolRank) {
        return SchoolResponse.builder()
                .schoolId(schoolRank.getSchoolId())
                .walkCount(schoolRank.getWalkCount())
                .userCount(schoolRank.getUserCount())
                .schoolName(schoolRank.getName())
                .ranking(schoolRank.getRanking())
                .logoImageUrl(schoolRank.getLogoImageUrl())
                .build();
    }
}
