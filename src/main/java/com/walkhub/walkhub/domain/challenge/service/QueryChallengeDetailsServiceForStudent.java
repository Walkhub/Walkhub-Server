package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsResponse.Writer;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.UserScope;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeDetailsServiceForStudent {

    private final ChallengeFacade challengeFacade;
    private final UserFacade userFacade;

    public QueryChallengeDetailsResponse execute(Long challengeId) {

        Challenge challenge = challengeFacade.getChallengeById(challengeId);
        User user = userFacade.getCurrentUser();
        User writer = challenge.getUser();

        if (!challenge.getUserScope().equals(UserScope.ALL) && !user.getSchool().equals(writer.getSchool())) {
            throw InvalidRoleException.EXCEPTION;
        }

        School writerSchool = writer.hasSchool() ? writer.getSchool() : School.builder().build();
        Section wrtierSection = writer.hasSection() ? writer.getSection() : Section.builder().build();

        Boolean isMine = challenge.getChallengeStatuses()
                .stream()
                .anyMatch(challengeStatus -> challengeStatus.getUser().equals(user));

        return QueryChallengeDetailsResponse.builder()
                .name(challenge.getName())
                .content(challenge.getContent())
                .userScope(challenge.getUserScope())
                .goal(challenge.getGoal())
                .goalScope(challenge.getGoalScope())
                .goalType(challenge.getGoalType())
                .award(challenge.getAward())
                .imageUrl(challenge.getImageUrl())
                .startAt(challenge.getStartAt())
                .endAt(challenge.getEndAt())
                .participantCount((long) challenge.getChallengeStatuses().size())
                .isMine(isMine)
                .isParticipated(user.equals(writer))
                .successStandard(challenge.getSuccessStandard())
                .writer(Writer.builder()
                        .userId(writer.getId())
                        .name(writer.getName())
                        .profileImageUrl(writer.getProfileImageUrl())
                        .authority(writer.getAuthority())
                        .schoolName(writerSchool.getName())
                        .classNum(wrtierSection.getClassNum())
                        .grade(wrtierSection.getGrade())
                        .build())
                .build();
    }
}