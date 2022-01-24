package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.GroupId;
import com.walkhub.walkhub.domain.user.domain.School;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.GroupRepository;
import com.walkhub.walkhub.domain.teacher.exception.AlreadyCreatedException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.CreateClassRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.CreateClassResponse;
import com.walkhub.walkhub.global.utils.code.CreateRandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateClassService {

    private final UserFacade userFacade;
    private final GroupRepository groupRepository;

    @Transactional
    public CreateClassResponse execute(CreateClassRequest request) {
        User user = userFacade.getCurrentUser();
        School userSchool = user.getSchool();
        String agencyCode = userSchool.getAgencyCode();
        Integer grade = request.getGrade();
        Integer classNum = request.getClassNum();

        if (groupRepository.findById(new GroupId(grade, classNum, agencyCode)).isPresent()) {
            throw AlreadyCreatedException.EXCEPTION;
        }

        String classCode = CreateRandomCodeUtil.random();
        Group group = Group.builder()
                .grade(grade)
                .classNum(classNum)
                .school(userSchool)
                .classCode(classCode)
                .build();

        groupRepository.save(group);
        user.setGroup(group);

        return new CreateClassResponse(classCode);
    }

}
