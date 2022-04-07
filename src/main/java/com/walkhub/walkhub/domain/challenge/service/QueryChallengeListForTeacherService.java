package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeListForTeacherVo;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListForTeacherResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChallengeListForTeacherService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;
    private final ChallengeRepository challengeRepository;

    public QueryChallengeListForTeacherResponse execute(Boolean isProgress) {
        User user = userFacade.getCurrentUser();

        List<ShowChallengeListForTeacherVo> challengeList = challengeRepository.queryChallengeListForTeacher(user, isProgress);

        return new QueryChallengeListForTeacherResponse(challengeList.stream()
                .map(showChallengeListForTeacherVo ->
                        QueryChallengeListForTeacherResponse.ChallengeResponse.builder()
                                .id(showChallengeListForTeacherVo.getChallengeId())
                                .name(showChallengeListForTeacherVo.getName())
                                .imageUrl(showChallengeListForTeacherVo.getImageUrl())
                                .startAt(showChallengeListForTeacherVo.getStartAt())
                                .endAt(showChallengeListForTeacherVo.getEndAt())
                                .goal(showChallengeListForTeacherVo.getGoal())
                                .goalScope(showChallengeListForTeacherVo.getGoalScope())
                                .goalType(showChallengeListForTeacherVo.getGoalType())
                                .award(showChallengeListForTeacherVo.getAward())
                                .isProgress(showChallengeListForTeacherVo.getIsProgress())
                                .writer(challengeFacade.personBuilder(
                                        showChallengeListForTeacherVo.getWriterId(), showChallengeListForTeacherVo.getWriterName(), showChallengeListForTeacherVo.getWriterProfileImageUrl()
                                ))
                                .participantCount(showChallengeListForTeacherVo.getParticipantCount())
                                .build()
                ).collect(Collectors.toList()));
    }

}
