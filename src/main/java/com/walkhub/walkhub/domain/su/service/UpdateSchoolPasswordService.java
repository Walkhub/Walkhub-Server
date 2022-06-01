package com.walkhub.walkhub.domain.su.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.su.exception.SchoolRootUserNotFoundException;
import com.walkhub.walkhub.domain.su.presentation.dto.response.RootAccountResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class UpdateSchoolPasswordService {

    private final SchoolFacade schoolFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RootAccountResponse execute(Long schoolId) {
        School school = schoolFacade.getSchoolById(schoolId);

        User user = userRepository.findBySchoolAndAuthority(school, Authority.ROOT)
                .orElseThrow(() -> SchoolRootUserNotFoundException.EXCEPTION);

        String updatedPassword = RandomCodeUtil.makeString(8);
        user.updateRootUserPassword(passwordEncoder.encode(updatedPassword));

        return RootAccountResponse.builder()
                .accountId(user.getAccountId())
                .password(updatedPassword)
                .build();
    }
}
