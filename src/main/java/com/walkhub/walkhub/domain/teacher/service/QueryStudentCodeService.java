package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.DetailsClassResponse;
import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.GroupFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
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

    private final UserFacade userFacade;
    private final GroupFacade groupFacade;
    private final UserRepository userRepository;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    @Transactional(readOnly = true)
    public DetailsClassResponse execute() {
        User teacher = userFacade.getCurrentUser();
        Group group = groupFacade.getGroup(teacher.getGroup().getId());

        List<DetailsClassResponse.UserListResponse> result =
                userRepository.findAllByGroupAndAuthority(group, Authority.STUDENT)
                        .stream()
                        .map(this::buildUserListResponse)
                        .collect(Collectors.toList());

        return new DetailsClassResponse(group.getClassCode(), result);
    }

    private DetailsClassResponse.UserListResponse buildUserListResponse(User user) {
        Integer walkCount = exerciseAnalysisRepository.findByUserAndDate(user, LocalDate.now())
                .map(ExerciseAnalysis::getWalkCount)
                .orElse(0);
        return DetailsClassResponse.UserListResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(walkCount)
                .build();
    }
}
