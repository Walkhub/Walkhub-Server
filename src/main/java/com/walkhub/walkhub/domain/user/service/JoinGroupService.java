package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.GroupRepository;
import com.walkhub.walkhub.domain.user.exception.AlreadyJoinedException;
import com.walkhub.walkhub.domain.user.exception.GroupNotFoundException;
import com.walkhub.walkhub.domain.user.exception.InvalidClassCodeException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JoinGroupService {

    private final UserFacade userFacade;
    private final GroupRepository groupRepository;

    @Transactional
    public void execute(String agencyCode, Integer grade, Integer classNum, String reqClassCode) {
        User user = userFacade.getCurrentUser();

        if (userFacade.isAlreadyJoined(user.getGroup())) {
            throw AlreadyJoinedException.EXCEPTION;
        }

        Group group = groupRepository.findBySchoolAgencyCodeAndGradeAndClassNum(agencyCode, grade, classNum)
                .orElseThrow(() -> GroupNotFoundException.EXCEPTION);

        if (!reqClassCode.equals(group.getClassCode())) {
            throw InvalidClassCodeException.EXCEPTION;
        }

        user.setGroup(group);
    }

}
