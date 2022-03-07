package com.walkhub.walkhub.domain.su.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.su.exception.SchoolRootUserNotFoundException;
import com.walkhub.walkhub.domain.su.presentation.dto.response.UpdateSchoolPasswordResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class UpdateSchoolPasswordService {
    private final UserRepository userRepository;
    private final SchoolFacade schoolFacade;

    @Transactional
    public UpdateSchoolPasswordResponse execute(Long schoolId) {
        School school = schoolFacade.getSchoolById(schoolId);

        User user = userRepository.findBySchoolAndAuthority(school, Authority.ROOT)
                .orElseThrow(() -> SchoolRootUserNotFoundException.EXCEPTION);

        String updatedPassword = user.updateRootUserPassword();

        return UpdateSchoolPasswordResponse.builder()
                .password(updatedPassword)
                .build();
    }
}
