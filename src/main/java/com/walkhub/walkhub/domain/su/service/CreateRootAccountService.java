package com.walkhub.walkhub.domain.su.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.su.exception.SchoolRootUserExistsException;
import com.walkhub.walkhub.domain.su.presentation.dto.response.RootAccountResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CreateRootAccountService {

    private final SchoolFacade schoolFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RootAccountResponse execute(Long schoolId) {
        String password = RandomCodeUtil.make(8);
        School school = schoolFacade.getSchoolById(schoolId);
        String suAccount = school.getName() + "_admin";

        if (userRepository.findBySchoolAndAuthority(school, Authority.ROOT).isPresent()) {
            throw SchoolRootUserExistsException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .accountId(suAccount)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROOT)
                .name(suAccount)
                .school(school)
                .isMeasuring(false)
                .sex(Sex.X)
                .build());

        return RootAccountResponse.builder()
                .accountId(suAccount)
                .password(password)
                .build();
    }
}
