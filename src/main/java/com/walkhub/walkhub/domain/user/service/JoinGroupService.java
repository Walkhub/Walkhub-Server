package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.school.exception.AgencyCodeNotMatchException;
import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.exception.ClassCodeNotMatchException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.JoinGroupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JoinGroupService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(String agencyCode, Integer grade, Integer classNum, JoinGroupRequest request) {
        User user = userFacade.getCurrentUser();

        Group group = userFacade.getGroupByGroupId(agencyCode, grade, classNum);

        if (!group.getClassCode().equals(request.getClassCode())) {
            throw ClassCodeNotMatchException.EXCEPTION;
        }

        if (!user.getSchool().equals(group.getSchool())) {
            throw AgencyCodeNotMatchException.EXCEPTION;
        }

        user.setGroup(group);
        user.setNumber(request.getNumber());
    }

}
