package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.VerificationCodeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VerificationCodeService {

    private final UserFacade userFacade;

    @Transactional
    public VerificationCodeResponse execute() {
        User user = userFacade.getCurrentUser();

        String verificationCode = RandomCodeUtil.make(7);

        user.getSchool().setAuthCode(verificationCode);

        return new VerificationCodeResponse(verificationCode);
    }
}
