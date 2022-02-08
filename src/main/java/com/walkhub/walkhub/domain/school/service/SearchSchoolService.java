package com.walkhub.walkhub.domain.school.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.school.presentation.dto.response.SearchSchoolListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchSchoolService {

    private final SchoolRepository schoolRepository;

    public SearchSchoolListResponse execute(String name) {
        List<SearchSchoolListResponse.SearchSchoolResponse> result =
                schoolRepository.findAllByNameContaining(name)
                        .stream()
                        .map(this::buildSearchSchoolResponse)
                        .collect(Collectors.toList());

        return new SearchSchoolListResponse(result);
    }

    private SearchSchoolListResponse.SearchSchoolResponse buildSearchSchoolResponse(School school) {
        return SearchSchoolListResponse.SearchSchoolResponse.builder()
                .agencyCode(school.getAgencyCode())
                .schoolName(school.getName())
                .logoImageUrl(school.getLogoImageUrl())
                .build();
    }
}