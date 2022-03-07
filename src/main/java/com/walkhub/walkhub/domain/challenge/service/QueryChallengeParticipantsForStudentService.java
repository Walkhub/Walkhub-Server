package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForStudentResponse;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@WalkhubService
public class QueryChallengeParticipantsForStudentService {
    private final ChallengeStatusRepository challengeStatusRepository;
    private final UserFacade userFacade;

    public QueryChallengeParticipantsForStudentResponse execute(Long challengeId) {
        User user = userFacade.getCurrentUser();
        School school = user.getSchool();
        Section section = user.getSection();

        return QueryChallengeParticipantsForStudentResponse.builder()
                .participantCount(challengeStatusRepository.getParticipantsCountByChallengeId(challengeId))
                .relatedChallengeParticipantList(challengeStatusRepository.getRelatedChallengeParticipantsList(challengeId, school, section.getGrade(), section.getClassNum())
                        .stream().map(participants -> QueryChallengeParticipantsForStudentResponse.RelatedChallengeParticipants.builder()
                                .userId(participants.getUserId())
                                .name(participants.getName())
                                .profileImageUrl(participants.getProfileImageUrl())
                                .build()
                        ).collect(Collectors.toList())
                ).build();
    }
}
