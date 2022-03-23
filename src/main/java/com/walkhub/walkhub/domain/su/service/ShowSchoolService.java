package com.walkhub.walkhub.domain.su.service;

import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.su.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ShowSchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolListResponse execute(Pageable page) {
        List<SchoolListResponse.SchoolResponse> schoolList = schoolRepository.findAllBy(page)
                .stream()
                .map(school -> SchoolListResponse.SchoolResponse.builder()
                        .schoolId(school.getId())
                        .name(school.getName())
                        .logoImageUrl(school.getLogoImageUrl())
                        .userCount(school.getUserCount())
                        .build()
                ).collect(Collectors.toList());

        return new SchoolListResponse(schoolList);
    }
}
