package com.walkhub.walkhub.domain.school.facade;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.user.exception.SchoolNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SchoolFacade {

    private final SchoolRepository schoolRepository;

    public School getSchoolById(Long schoolId) {
        return schoolRepository.findById(schoolId)
                .orElseThrow(() -> SchoolNotFoundException.EXCEPTION);
    }

    public School getSchoolByAuthCode(String authCode) {
        return schoolRepository.findByAuthCode(authCode)
                .orElseThrow(() -> SchoolNotFoundException.EXCEPTION);
    }
}
