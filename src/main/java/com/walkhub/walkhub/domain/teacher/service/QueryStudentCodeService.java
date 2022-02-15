package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.DetailsClassResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.DetailsClassResponse.TeacherResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.DetailsClassResponse.UserListResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import com.walkhub.walkhub.domain.user.facade.SectionFacade;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryStudentCodeService {

    private final SectionFacade sectionFacade;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    @Transactional(readOnly = true)
    public DetailsClassResponse execute(Long sectionId) {
        Section section = sectionFacade.getSectionById(sectionId);

        List<UserListResponse> result =
                section.getUsers()
                        .stream()
                        .filter(user -> user.getAuthority() == Authority.STUDENT)
                        .map(this::buildUserListResponse)
                        .collect(Collectors.toList());

        User teacher = section.getUsers().stream()
                .filter(user -> user.getAuthority() == Authority.TEACHER)
                .findFirst()
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        TeacherResponse teacherResponse = TeacherResponse.builder()
                .userId(teacher.getId())
                .name(teacher.getName())
                .profileImageUrl(teacher.getProfileImageUrl())
                .build();

        return DetailsClassResponse.builder()
                .teacher(teacherResponse)
                .classCode(section.getClassCode())
                .userList(result)
                .build();
    }

    private UserListResponse buildUserListResponse(User user) {
        Integer walkCount = exerciseAnalysisRepository.findByUserAndDate(user, LocalDate.now())
                .map(ExerciseAnalysis::getWalkCount)
                .orElse(0);
        return UserListResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(walkCount)
                .build();
    }
}
