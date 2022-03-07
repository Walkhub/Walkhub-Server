package com.walkhub.walkhub.domain.su.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.su.presentation.dto.response.CreateRootAccountResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@WalkhubService
public class CreateRootAccountService {

    private final SchoolFacade schoolFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateRootAccountResponse execute(Long schoolId) {
        String password = RandomCodeUtil.make(8);
        School school = schoolFacade.getSchoolById(schoolId);
        String suAccount = school.getName() + "_admin";

        userRepository.save(User.builder()
                .accountId(suAccount)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROOT)
                .name(suAccount)
                .school(school)
                .isMeasuring(false)
                .sex(Sex.X)
                .build());

        return new CreateRootAccountResponse(school.getName(), password);
    }
}
