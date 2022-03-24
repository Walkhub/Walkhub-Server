package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class UpdateUserInfoService {

    private final UserFacade userFacade;
    private final ChallengeStatusRepository challengeStatusRepository;
    private final SchoolFacade schoolFacade;


    @Transactional
    public void execute(UpdateUserInfoRequest request) {
        User user = userFacade.getCurrentUser();
        School school = schoolFacade.getSchoolById(request.getSchoolId());
        user.updateUser(request);

        if (!school.equals(user.getSchool())) {
            user.setSection(null);
            user.setSchool(school);
            user.getSchool().minusUserCount();
            school.addUserCount();
            challengeStatusRepository.deleteNotOverChallengeStatusByUserId(user.getId());
        }


    }

}
