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
public class DetailsClassService {

    private final SectionFacade sectionFacade;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    @Transactional(readOnly = true)
    public DetailsClassResponse execute(Long sectionId) {
        Section section = sectionFacade.getSectionById(sectionId);

        User teacher = section.getUsers().stream()
                .filter(user -> user.getAuthority() == Authority.TEACHER)
                .findFirst()
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        TeacherResponse teacherResponse = TeacherResponse.builder()
                .userId(teacher.getId())
                .name(teacher.getName())
                .profileImageUrl(teacher.getProfileImageUrl())
                .build();

        List<UserListResponse> userListResponses = section.getUsers()
                .stream()
                .filter(user -> user.getAuthority() == Authority.USER)
                .map(this::buildUserListResponse)
                .collect(Collectors.toList());

        return DetailsClassResponse.builder()
                .classCode(section.getClassCode())
                .teacher(teacherResponse)
                .userList(userListResponses)
                .build();
    }

    private UserListResponse buildUserListResponse(User user) {
        LocalDate startAt = LocalDate.now().minusDays(7);
        LocalDate endAt = LocalDate.now();
        List<ExerciseAnalysis> exerciseAnalyses = exerciseAnalysisRepository.findAllByUserAndDateBetween(user, startAt, endAt);

        return UserListResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .number(user.getNumber())
                .averageWalkCount((int) exerciseAnalyses.stream().mapToInt(ExerciseAnalysis::getWalkCount).average().orElse(0))
                .totalWalkCount(exerciseAnalyses.stream().mapToInt(ExerciseAnalysis::getWalkCount).sum())
                .averageDistance((int) exerciseAnalyses.stream().mapToInt(ExerciseAnalysis::getDistance).average().orElse(0))
                .totalDistance(exerciseAnalyses.stream().mapToInt(ExerciseAnalysis::getDistance).sum())
                .build();
    }
}
