package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.CodeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class VerificationCodeService {

    private final UserFacade userFacade;

    @Transactional
    public CodeResponse execute() {
        User user = userFacade.getCurrentUser();

        String verificationCode = RandomCodeUtil.make(7);

        user.getSchool().setAuthCode(verificationCode);

        return new CodeResponse(verificationCode);
    }
}
