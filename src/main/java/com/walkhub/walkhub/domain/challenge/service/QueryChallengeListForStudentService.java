package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeVO;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListForStudentResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListForStudentResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeListForStudentService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;
    private final ChallengeRepository challengeRepository;

    public QueryChallengeListForStudentResponse execute() {
        User user = userFacade.getCurrentUser();

        List<ChallengeResponse> challengeList = challengeRepository.queryChallengeListForStudent(user)
                .stream()
                .map(challenge -> this.challengeResponseBuilder(user, challenge))
                .collect(Collectors.toList());

        return new QueryChallengeListForStudentResponse(challengeList);
    }

    private ChallengeResponse challengeResponseBuilder(User user, ShowChallengeVO vo) {
        Long challengeId = vo.getChallengeId();

        List<RelatedChallengeParticipantsVO> relatedChallengeParticipantsList =
                challengeRepository.getRelatedChallengeParticipantsList(challengeId, user);

        return ChallengeResponse.builder()
                .id(vo.getChallengeId())
                .name(vo.getName())
                .imageUrl(vo.getImageUrl())
                .startAt(vo.getStartAt())
                .endAt(vo.getEndAt())
                .goal(vo.getGoal())
                .goalScope(vo.getGoalScope())
                .goalType(vo.getGoalType())
                .award(vo.getAward())
                .writer(challengeFacade.personBuilder(
                        vo.getWriterId(), vo.getWriterName(), vo.getWriterProfileImageUrl()
                ))
                .participantCount(vo.getParticipantCount())
                .participantList(relatedChallengeParticipantsList
                        .stream()
                        .map(participant -> challengeFacade.personBuilder(
                                participant.getUserId(), participant.getName(), participant.getProfileImageUrl()
                        ))
                        .collect(Collectors.toList())
                )
                .build();
    }
}
