package com.walkhub.walkhub.domain.school.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.school.exception.AgencyCodeNotMatchException;
import com.walkhub.walkhub.domain.school.presentation.dto.request.SchoolLogoRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.exception.SchoolNotFoundException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SchoolLogoSettingService {

    private final UserFacade userFacade;
    private final SchoolRepository schoolRepository;

    @Transactional
    public void execute(String agencyCode, SchoolLogoRequest request) {
        User user = userFacade.getCurrentUser();

        School school = schoolRepository.findByAgencyCode(agencyCode)
                .orElseThrow(() -> SchoolNotFoundException.EXCEPTION);

        if (!user.getSchool().equals(school)) {
            throw AgencyCodeNotMatchException.EXCEPTION;
        }

        school.setLogoImage(request.getImageUrl());
    }
}
