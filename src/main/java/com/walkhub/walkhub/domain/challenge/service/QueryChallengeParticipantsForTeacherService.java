package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForTeacherResponse;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChallengeParticipantsForTeacherService {
    private final ChallengeFacade challengeFacade;
    private final ChallengeStatusRepository challengeStatusRepository;

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
        Map<Long, List<ChallengeParticipantsVO>> participants =
                challengeStatusRepository.queryChallengeParticipantsList(challenge, successScope).stream()
                        .collect(Collectors.groupingBy(ChallengeParticipantsVO::getUserId));

        return participants.values().stream()
                .map(vo -> buildChallengeParticipantsResponse(vo, challenge))
                .collect(Collectors.toList());
    }

    private QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants buildChallengeParticipantsResponse(
            List<ChallengeParticipantsVO> challengeParticipantsVOs,
            Challenge challenge
    ) {
        List<LocalDate> successDates = challengeParticipantsVOs.stream()
                .map(ChallengeParticipantsVO::getExerciseAnalysesDate)
                .sorted().collect(Collectors.toList());

        ChallengeParticipantsVO participantsVO = challengeParticipantsVOs.stream().findFirst()
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Integer successCount = successDates.size();

        return QueryChallengeParticipantsForTeacherResponse.ChallengeParticipants.builder()
                .userId(participantsVO.getUserId())
                .grade(participantsVO.getGrade())
                .classNum(participantsVO.getClassNum())
                .number(participantsVO.getNumber())
                .name(participantsVO.getName())
                .profileImageUrl(participantsVO.getProfileImageUrl())
                .schoolName(participantsVO.getSchoolName())
                .successDate(successDates)
                .isSuccess(challenge.getSuccessStandard() <= successCount)
                .build();
    }

}
