package com.walkhub.walkhub.domain.su.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.su.presentation.dto.response.CreateRootAccountResponse;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateRootAccountService {

    private final SchoolFacade schoolFacade;

    public CreateRootAccountResponse execute(Long schoolId) {
        String password = RandomString.make(8);

        School school = schoolFacade.getById(schoolId);

        return new CreateRootAccountResponse(school.getName(), password);
    }
}
