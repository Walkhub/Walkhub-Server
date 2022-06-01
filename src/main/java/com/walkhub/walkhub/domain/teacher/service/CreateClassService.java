package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.teacher.exception.AlreadyCreatedException;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.CreateClassRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.CodeResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CreateClassService {

    private final UserFacade userFacade;
    private final SectionRepository sectionRepository;

    @Transactional
    public CodeResponse execute(CreateClassRequest request) {
        User user = userFacade.getCurrentUser();
        School userSchool = user.getSchool();
        Integer grade = request.getGrade();
        Integer classNum = request.getClassNum();
        String classCode = RandomCodeUtil.makeNumber(7);
        Section section = Section.builder()
                .grade(grade)
                .classNum(classNum)
                .school(userSchool)
                .classCode(classCode)
                .build();

        if (user.hasSection() || sectionRepository.findBySchoolAndGradeAndClassNum(userSchool, grade, classNum).isPresent()) {
            throw AlreadyCreatedException.EXCEPTION;
        }

        Section savedSection = sectionRepository.save(section);
        user.setSection(savedSection);

        return new CodeResponse(classCode);
    }

}
