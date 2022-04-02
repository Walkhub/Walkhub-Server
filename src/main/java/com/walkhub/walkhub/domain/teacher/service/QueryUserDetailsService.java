package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserDetailsResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.domain.repository.vo.UserDetailsVO;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryUserDetailsService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public QueryUserDetailsResponse execute(Long userId, LocalDate startAt, LocalDate endAt) {
        User user = userFacade.getUserById(userId);
        Section section = user.hasSection() ? user.getSection() : Section.builder().build();

        UserDetailsVO vo = userRepository.queryUserDetails(userId, startAt, endAt);

        return QueryUserDetailsResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .grade(section.getGrade())
                .classNum(section.getClassNum())
                .number(user.getNumber())
                .walkCountList(vo.getWalkCountList())
                .averageWalkCount(vo.getAverageWalkCount())
                .totalWalkCount(vo.getTotalWalkCount())
                .averageDistance(vo.getAverageDistance())
                .totalDistance(vo.getTotalDistance())
                .build();
    }
}
