package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.exercise.facade.ExerciseAnalysisFacade;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserDetailsResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.domain.repository.vo.UserDetailsVO;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryUserDetailsService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final ExerciseAnalysisFacade exerciseAnalysisFacade;

    public QueryUserDetailsResponse execute(Long userId, LocalDate startAt, LocalDate endAt) {
        User user = userFacade.getUserById(userId);
        UserDetailsVO vo = userRepository.queryUserDetails(userId, startAt, endAt);

        List<Integer> walkCountList = exerciseAnalysisFacade.getWalkCountList(user, startAt, endAt);

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
