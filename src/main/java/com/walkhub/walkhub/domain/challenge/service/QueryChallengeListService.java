package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeVO;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.Participant;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.Writer;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeListService {

    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;

    public QueryChallengeListResponse execute() {
        User user = userFacade.getCurrentUser();

        List<ChallengeResponse> challengeResponseList = challengeRepository.queryChallenge(user)
                .stream()
                .map(this::challengeResponseBuilder)
                .collect(Collectors.toList());

        return new QueryChallengeListResponse(challengeResponseList);
    }

    private ChallengeResponse challengeResponseBuilder(ShowChallengeVO vo) {
        return ChallengeResponse.builder()
                .id(vo.getChallengeId())
                .name(vo.getName())
                .startAt(vo.getStartAt())
                .endAt(vo.getEndAt())
                .goal(vo.getGoal())
                .goalScope(vo.getGoalScope())
                .goalType(vo.getGoalType())
                .award(vo.getAward())
                .writer(Writer.builder()
                        .userId(vo.getChallengeId())
                        .name(vo.getWriterName())
                        .profileImageUrl(vo.getWriterProfileImageUrl())
                        .build())
                .participantCount(vo.getParticipantCount())
                .participantList(vo.getParticipantList()
                        .stream()
                        .map(participant -> Participant.builder()
                                .userId(participant.getUserId())
                                .name(participant.getName())
                                .profileImageUrl(participant.getProfileImageUrl())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }

}
