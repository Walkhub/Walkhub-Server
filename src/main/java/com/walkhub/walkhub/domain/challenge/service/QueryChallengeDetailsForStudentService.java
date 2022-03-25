package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForStudentVO;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForStudentResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForStudentResponse.Participant;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForStudentResponse.Writer;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeDetailsForStudentService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;
    private final ChallengeRepository challengeRepository;

    public QueryChallengeDetailsForStudentResponse execute(Long challengeId) {
        Challenge challenge = challengeFacade.getChallengeById(challengeId);
        User user = userFacade.getCurrentUser();

        ChallengeDetailsForStudentVO vo = challengeRepository.queryChallengeDetails(challenge, user);
        return buildResponse(vo);
    }

    private QueryChallengeDetailsForStudentResponse buildResponse(ChallengeDetailsForStudentVO vo) {
        return QueryChallengeDetailsForStudentResponse.builder()
                .name(vo.getName())
                .content(vo.getContent())
                .userScope(vo.getUserScope())
                .goal(vo.getGoal())
                .goalScope(vo.getGoalScope())
                .goalType(vo.getGoalType())
                .award(vo.getAward())
                .imageUrl(vo.getImageUrl())
                .startAt(vo.getStartAt())
                .endAt(vo.getEndAt())
                .successStandard(vo.getSuccessStandard())
                .writer(Writer.builder()
                        .userId(vo.getWriterUserId())
                        .name(vo.getWriterName())
                        .profileImageUrl(vo.getWriterProfileImageUrl())
                        .build())
                .isMine(vo.getIsMine())
                .isParticipated(vo.getIsParticipated())
                .participantCount(vo.getParticipantCount())
                .participantList(vo.getParticipantList()
                        .stream()
                        .map(participant -> Participant.builder()
                                .userId(participant.getUserId())
                                .name(participant.getName())
                                .profileImageUrl(participant.getProfileImageUrl())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}