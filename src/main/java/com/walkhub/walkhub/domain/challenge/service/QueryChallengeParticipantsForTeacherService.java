package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForTeacherResponse;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChallengeParticipantsForTeacherService {

    private final ChallengeFacade challengeFacade;
    private final ChallengeStatusRepository challengeStatusRepository;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    @Transactional(readOnly = true)
    public QueryChallengeParticipantsForTeacherResponse execute(Long challengeId, SuccessScope successScope) {
        Challenge challenge = challengeFacade.getChallengeById(challengeId);

        return new QueryChallengeParticipantsForTeacherResponse(
                queryChallengeParticipantsForTeacherResponseBuilder(challenge, successScope)
        );
    }

    private List<QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants> queryChallengeParticipantsForTeacherResponseBuilder(
            Challenge challenge, SuccessScope successScope
    ) {
        return challengeStatusRepository.queryChallengeParticipantsList(challenge, successScope)
                .stream()
                .map(vo -> QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants.builder()
                        .userId(vo.getUserId())
                        .grade(vo.getGrade())
                        .classNum(vo.getClassNum())
                        .number(vo.getNumber())
                        .name(vo.getName())
                        .profileImageUrl(vo.getProfileImageUrl())
                        .schoolName(vo.getSchoolName())
                        .successDate(exerciseAnalysisRepository.querySuccessDateList(vo.getUserId(), challenge))
                        .isSuccess(vo.getIsSuccess())
                        .build())
                .collect(Collectors.toList());
    }

}
