package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForTeacherResponse;
import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChallengeParticipantsForTeacherService {

    private final ChallengeFacade challengeFacade;
    private final ChallengeStatusRepository challengeStatusRepository;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    @Transactional(readOnly = true)
    public QueryChallengeParticipantsForTeacherResponse execute(Long challengeId, Boolean isSuccess) {
        Challenge challenge = challengeFacade.getChallengeById(challengeId);

        List<QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants> response = challengeStatusRepository.findAllByChallenge(challenge)
                .stream()
                .map(challengeStatus -> queryChallengeParticipantsForTeacherResponseBuilder(challenge, challengeStatus))
                .filter(challengeParticipants -> challengeParticipants.getIsSuccess() == isSuccess)
                .collect(Collectors.toList());

        return new QueryChallengeParticipantsForTeacherResponse(response);
    }

    private QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants queryChallengeParticipantsForTeacherResponseBuilder(Challenge challenge, ChallengeStatus challengeStatus) {
        User user = challengeStatus.getUser();
        Section userSection = user.getSection();

        List<LocalDate> successDateList = exerciseAnalysisRepository.findAllByUser(user)
                .stream()
                .filter(e -> e.getWalkCount() > challenge.getGoal())
                .map(ExerciseAnalysis::getDate)
                .collect(Collectors.toList());

        return QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants.builder()
                .userId(user.getId())
                .grade(userSection.getGrade())
                .classNum(userSection.getGrade())
                .number(user.getNumber())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .schoolName(user.getSchool().getName())
                .successDate(successDateList)
                .isSuccess(challengeStatus.getSuccessCount() > challenge.getSuccessStandard())
                .build();
    }

}
