package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse.Writer;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeDetailsForTeacherService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;

    public QueryChallengeDetailsForTeacherResponse execute(Long challengeId) {
        User user = userFacade.getCurrentUser();

        Challenge challenge = challengeFacade.getChallengeById(challengeId);
        User challengeCreator = challenge.getUser();
        Section section = challengeCreator.hasSection() ? challengeCreator.getSection() : Section.builder().build();

        return QueryChallengeDetailsForTeacherResponse.builder()
                .name(challenge.getName())
                .content(challenge.getContent())
                .imageUrl(challenge.getImageUrl())
                .writer(Writer.builder()
                        .userId(challengeCreator.getId())
                        .name(challengeCreator.getName())
                        .profileImageUrl(challengeCreator.getProfileImageUrl())
                        .build())
                .award(challenge.getAward())
                .startAt(challenge.getStartAt())
                .endAt(challenge.getEndAt())
                .goal(challenge.getGoal())
                .goalScope(challenge.getGoalScope())
                .goalType(challenge.getGoalType())
                .classNum(section.getClassNum())
                .grade(section.getGrade())
                .successStandard(challenge.getSuccessStandard())
                .isMine(challenge.getUser() == user)
                .build();
    }
}
