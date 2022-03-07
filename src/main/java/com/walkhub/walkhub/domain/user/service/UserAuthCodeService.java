package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.UserAuthCode;
import com.walkhub.walkhub.domain.user.domain.repository.UserAuthCodeRepository;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UserAuthCodeRequest;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import com.walkhub.walkhub.infrastructure.sms.coolsms.CoolSms;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAuthCodeService {

    private final CoolSms coolSms;
    private final UserAuthCodeRepository userAuthCodeRepository;
    private final PasswordEncoder passwordEncoder;

    @Async
    public void execute(UserAuthCodeRequest request) {
        String phoneNumber = request.getPhoneNumber();
        String code = RandomCodeUtil.make(5);

        coolSms.sendCode(phoneNumber, code);

        userAuthCodeRepository.save(UserAuthCode.builder()
                .phoneNumber(phoneNumber)
                .code(passwordEncoder.encode(code))
                .build());
    }

}
