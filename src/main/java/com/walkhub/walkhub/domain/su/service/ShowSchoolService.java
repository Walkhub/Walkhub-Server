package com.walkhub.walkhub.domain.su.service;

import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.su.presentation.dto.response.SchoolListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShowSchoolService {

    private final SchoolRepository schoolRepository;

    @Transactional(readOnly = true)
    public SchoolListResponse execute(Pageable page) {
        List<SchoolListResponse.SchoolResponse> schoolList = schoolRepository.findAllBy(page)
                .stream()
                .map(school -> SchoolListResponse.SchoolResponse.builder()
                        .schoolId(school.getId())
                        .name(school.getName())
                        .logoImageUrl(school.getLogoImageUrl())
                        .userCount(school.getUserCount())
                        .createdAt(school.getCreatedAt())
                        .build()
                ).collect(Collectors.toList());

        return new SchoolListResponse(schoolList);
    }
}
