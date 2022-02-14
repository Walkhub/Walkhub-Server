package com.walkhub.walkhub.domain.su.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.su.presentation.dto.response.CreateRootAccountResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateRootAccountService {

    private final SchoolFacade schoolFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateRootAccountResponse execute(Long schoolId) {
        String password = RandomString.make(8);
        School school = schoolFacade.getSchoolById(schoolId);

        userRepository.save(User.builder()
                .accountId(school.getName())
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROOT)
                .name(school.getName())
                .school(school)
                .isMeasuring(false)
                .sex(Sex.X)
                .build());

        return new CreateRootAccountResponse(school.getName(), password);
    }
}
