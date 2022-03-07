package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UpdateSchoolInfoRequest;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class UpdateSchoolInfoService {

    private final UserFacade userFacade;
    private final ChallengeStatusRepository challengeStatusRepository;
    private final SchoolFacade schoolFacade;

    @Transactional
    public void execute(UpdateSchoolInfoRequest request) {
        User user = userFacade.getCurrentUser();
        School school = schoolFacade.getSchoolById(request.getSchoolId());

        user.setSection(null);
        user.setSchool(school);
        user.getSchool().minusUserCount();
        school.addUserCount();


        challengeStatusRepository.deleteNotOverChallengeStatusByUserId(user.getId());
    }
}
