package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse.Writer;
import static com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse.builder;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CreateChallengeService {

    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;

    @Transactional
    public QueryChallengeDetailsForTeacherResponse execute(CreateChallengeRequest request) {
        User user = userFacade.getCurrentUser();
        UserScope userScope = user.getAuthority() == Authority.SU ? UserScope.ALL : request.getUserScope();

        Challenge challenge = Challenge.builder()
                .user(user)
                .name(request.getName())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .award(request.getAward())
                .userScope(userScope)
                .goal(request.getGoal())
                .goalType(request.getGoalType())
                .goalScope(request.getGoalScope())
                .successStandard(request.getSuccessStandard())
                .build();
        challengeRepository.save(challenge);

        return builderChallenge(user, challenge);
    }

    private QueryChallengeDetailsForTeacherResponse builderChallenge(User user, Challenge challenge) {
        Section section = user.hasSection() ? user.getSection() : Section.builder().build();

        return builder()
                .schoolName(user.getSchool().getName())
                .name(challenge.getName())
                .content(challenge.getContent())
                .imageUrl(challenge.getImageUrl())
                .writer(Writer.builder()
                        .userId(user.getId())
                        .name(user.getName())
                        .profileImageUrl(user.getProfileImageUrl())
                        .build())
                .award(challenge.getAward())
                .startAt(challenge.getStartAt())
                .endAt(challenge.getEndAt())
                .goal(challenge.getGoal())
                .goalScope(challenge.getGoalScope())
                .goalType(challenge.getGoalType())
                .userScope(challenge.getUserScope())
                .classNum(section.getClassNum())
                .grade(section.getGrade())
                .successStandard(challenge.getSuccessStandard())
                .isMine(true)
                .build();
    }
}
