package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowParticipatedChallengeVO;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryParticipateChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryParticipateChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryParticipatedChallengeListService {

    private final ChallengeStatusRepository challengeStatusRepository;
    private final UserFacade userFacade;

    public QueryParticipateChallengeListResponse execute() {
        List<ChallengeResponse> challengeResponseList =
                challengeStatusRepository.getParticipatedChallengesByUser(userFacade.getCurrentUser())
                        .stream()
                        .map(this::challengeResponseBuilder)
                        .collect(Collectors.toList());

        return new QueryParticipateChallengeListResponse(challengeResponseList);
    }

    private ChallengeResponse challengeResponseBuilder(ShowParticipatedChallengeVO vo) {
        return ChallengeResponse.builder()
                .id(vo.getChallengeId())
                .name(vo.getName())
                .imageUrl(vo.getImageUrl())
                .startAt(vo.getStartAt())
                .endAt(vo.getEndAt())
                .goal(vo.getGoal())
                .goalScope(vo.getGoalScope())
                .goalType(vo.getGoalType())
                .totalValue(vo.getTotalValue())
                .writer(QueryParticipateChallengeListResponse.Writer.builder()
                        .userId(vo.getChallengeId())
                        .name(vo.getWriterName())
                        .profileImageUrl(vo.getWriterProfileImageUrl())
                        .build())
                .build();
    }
}
