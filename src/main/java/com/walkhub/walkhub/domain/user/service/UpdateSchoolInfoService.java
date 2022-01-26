package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.exception.SchoolNotFoundException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateSchoolInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateSchoolInfoService {

    private final UserFacade userFacade;
    private final SchoolRepository schoolRepository;

    @Transactional
    public void execute(UpdateSchoolInfoRequest request) {
        User user = userFacade.getCurrentUser();

        School school = schoolRepository.findById(request.getAgencyCode())
                .orElseThrow(() -> SchoolNotFoundException.EXCEPTION);

        user.setSchool(school);
    }
}
