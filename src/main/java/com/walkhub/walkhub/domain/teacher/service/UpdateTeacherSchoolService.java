package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.UpdateTeacherSchoolRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateTeacherSchoolService {

    private final UserFacade userFacade;
    private final SchoolFacade schoolFacade;
    private final ChallengeRepository challengeRepository;
    private final SectionRepository sectionRepository;

    @Transactional
    public void execute(UpdateTeacherSchoolRequest request) {
        User user = userFacade.getCurrentUser();
        School school = schoolFacade.getSchoolByAuthCode(request.getAuthCode());
        List<Challenge> challenges = challengeRepository.findAllByUser(user);

        user.getSchool().minusUserCount();
        school.addUserCount();

        challenges.forEach(Challenge::setUserNull);
        user.setSchool(school);

        if (user.hasSection()) {
            sectionRepository.delete(user.getSection());
        }

        challengeRepository.deleteAllByUserAndEndAtAfter(user, LocalDate.now());
    }
}
