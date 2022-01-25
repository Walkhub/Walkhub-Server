package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.exception.AlreadyJoinedException;
import com.walkhub.walkhub.domain.user.exception.InvalidClassCodeException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JoinGroupService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(String agencyCode, Integer grade, Integer classNum, String requestClassCode) {
        User user = userFacade.getCurrentUser();

        if (user.getGroup() != null) {
            throw AlreadyJoinedException.EXCEPTION;
        }

        Group group = userFacade.getGroupByGroupId(agencyCode, grade, classNum);

        if (!requestClassCode.equals(group.getClassCode())) {
            throw InvalidClassCodeException.EXCEPTION;
        }

        user.setGroup(group);
    }

}
