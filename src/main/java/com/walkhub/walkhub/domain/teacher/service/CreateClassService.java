package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.teacher.exception.AlreadyCreatedException;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.CreateClassRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassCodeResponse;
import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.GroupRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateClassService {

    private final UserFacade userFacade;
    private final GroupRepository groupRepository;

    @Transactional
    public ClassCodeResponse execute(CreateClassRequest request) {
        User user = userFacade.getCurrentUser();
        School userSchool = user.getSchool();
        Integer grade = request.getGrade();
        Integer classNum = request.getClassNum();
        String classCode = RandomCodeUtil.make(7);
        Group group = Group.builder()
                .grade(grade)
                .classNum(classNum)
                .school(userSchool)
                .classCode(classCode)
                .build();

        try {
            groupRepository.save(group);
            user.setGroup(group);
        } catch (DataIntegrityViolationException e) {
            throw AlreadyCreatedException.EXCEPTION;
        }

        return new ClassCodeResponse(classCode);
    }

}
