package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForStudentVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForStudentResponse;
import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeDetailsForStudentService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;
    private final ChallengeRepository challengeRepository;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    public QueryChallengeDetailsForStudentResponse execute(Long challengeId) {
        Challenge challenge = challengeFacade.getChallengeById(challengeId);
        User user = userFacade.getCurrentUser();

        ChallengeDetailsForStudentVO vo = challengeRepository.queryChallengeDetailsForStudent(challenge, user);
        return buildResponse(challengeId, user, vo);
    }

    private QueryChallengeDetailsForStudentResponse buildResponse(Long challengeId, User user, ChallengeDetailsForStudentVO vo) {
        List<RelatedChallengeParticipantsVO> relatedChallengeParticipantsList =
                challengeRepository.getRelatedChallengeParticipantsList(challengeId, user);

        int totalValue = buildTotalValue(vo.getGoalType(), vo.getStartAt(), vo.getEndAt(), user);

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
                .value(totalValue)
                .writer(challengeFacade.personBuilder(
                        vo.getWriterUserId(), vo.getWriterName(), vo.getWriterProfileImageUrl()
                ))
                .isMine(vo.getIsMine())
                .isParticipated(vo.getIsParticipated() != null && vo.getIsParticipated())
                .participantCount(vo.getParticipantCount())
                .participantList(relatedChallengeParticipantsList
                        .stream()
                        .map(participant -> challengeFacade.personBuilder(
                                participant.getUserId(), participant.getName(), participant.getProfileImageUrl()
                        ))
                        .collect(Collectors.toList()))
                .build();
    }

    private Integer buildTotalValue(GoalType goalType, LocalDate startAt, LocalDate endAt, User user) {
        if (GoalType.WALK == goalType) {
            return exerciseAnalysisRepository.findAllByUserAndDateBetween(user, startAt, endAt)
                    .stream()
                    .mapToInt(ExerciseAnalysis::getWalkCount)
                    .sum();
        } else {
            return exerciseAnalysisRepository.findAllByUserAndDateBetween(user, startAt, endAt)
                    .stream()
                    .mapToInt(ExerciseAnalysis::getDistance)
                    .sum();
        }
    }
}