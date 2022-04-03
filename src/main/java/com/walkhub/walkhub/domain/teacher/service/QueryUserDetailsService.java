package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserDetailsResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.domain.repository.vo.UserDetailsVO;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryUserDetailsService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    public QueryUserDetailsResponse execute(Long userId, LocalDate startAt, LocalDate endAt) {
        User user = userFacade.getUserById(userId);

        UserDetailsVO vo = userRepository.queryUserDetails(userId, startAt, endAt);

        List<Integer> walkCountList = exerciseAnalysisRepository.findAllByUserAndDateBetween(user, startAt, endAt)
                .stream()
                .map(ExerciseAnalysis::getWalkCount)
                .collect(Collectors.toList());

        return QueryUserDetailsResponse.builder()
                .userId(vo.getUserId())
                .name(vo.getName())
                .profileImageUrl(vo.getProfileImageUrl())
                .grade(vo.getGrade())
                .classNum(vo.getClassNum())
                .number(vo.getNumber())
                .walkCountList(walkCountList)
                .averageWalkCount(vo.getAverageWalkCount())
                .totalWalkCount(vo.getTotalWalkCount())
                .averageDistance(vo.getAverageDistance())
                .totalDistance(vo.getTotalDistance())
                .build();
    }
}
