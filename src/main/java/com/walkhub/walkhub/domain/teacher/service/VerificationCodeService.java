package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.domain.TeacherAuthCode;
import com.walkhub.walkhub.domain.teacher.domain.repository.TeacherAuthCodeRepository;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.VerificationCodeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VerificationCodeService {

    private final UserFacade userFacade;
    TeacherAuthCodeRepository teacherAuthCodeRepository;

    public VerificationCodeResponse execute() {
        User user = userFacade.getCurrentUser();

        String verificationCode = RandomCodeUtil.make(7);

        teacherAuthCodeRepository.save(TeacherAuthCode.builder()
                .school(user.getSchool().getAgencyCode())
                .authCode(verificationCode)
                .build());

        return new VerificationCodeResponse(verificationCode);
    }
}
