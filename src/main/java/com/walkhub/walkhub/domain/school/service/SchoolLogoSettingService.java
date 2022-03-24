package com.walkhub.walkhub.domain.school.service;

import com.walkhub.walkhub.domain.school.presentation.dto.request.SchoolLogoRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SchoolLogoSettingService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(SchoolLogoRequest request) {
        User user = userFacade.getCurrentUser();

        user.getSchool().setLogoImage(request.getImageUrl());
    }
}
