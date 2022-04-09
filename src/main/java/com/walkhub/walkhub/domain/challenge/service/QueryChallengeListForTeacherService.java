package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeListForTeacherVo;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListForTeacherResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListForTeacherResponse.ChallengeResponse;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeListForTeacherService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;
    private final ChallengeRepository challengeRepository;

    public QueryChallengeListForTeacherResponse execute(Boolean isProgress) {
        User user = userFacade.getCurrentUser();
        List<ChallengeResponse> challengeList = challengeRepository.queryChallengeListForTeacher(user, isProgress)
                .stream()
                .map(this::challengeResponseBuilder)
                .collect(Collectors.toList());

        return new QueryChallengeListForTeacherResponse(challengeList);
    }

    private ChallengeResponse challengeResponseBuilder(ShowChallengeListForTeacherVo vo) {
        return ChallengeResponse.builder()
                .id(vo.getChallengeId())
                .name(vo.getName())
                .imageUrl(vo.getImageUrl())
                .startAt(vo.getStartAt())
                .endAt(vo.getEndAt())
                .goal(vo.getGoal())
                .goalScope(vo.getGoalScope())
                .goalType(vo.getGoalType())
                .writer(challengeFacade.personBuilder(
                        vo.getWriterId(),
                        vo.getWriterProfileImageUrl(),
                        vo.getWriterProfileImageUrl()
                ))
                .participantCount(vo.getParticipantCount())
                .build();
    }
}
