package com.walkhub.walkhub.domain.challenge.facade;


import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.exception.ChallengeNotFoundException;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.PersonResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengeFacade {

    private final ChallengeRepository challengeRepository;

    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);
    }

    public PersonResponse personBuilder(Long userId, String name, String profileUrl) {
        return PersonResponse.builder()
                .userId(userId)
                .name(name)
                .profileImageUrl(profileUrl)
                .build();
    }

    public QueryChallengeDetailsForTeacherResponse builderChallenge(User user, Challenge challenge) {
        Section section = challenge.getUser().getSection() != null ?
                challenge.getUser().getSection() : Section.builder().build();

        return QueryChallengeDetailsForTeacherResponse.builder()
                .schoolName(user.getSchool().getName())
                .name(challenge.getName())
                .content(challenge.getContent())
                .imageUrl(challenge.getImageUrl())
                .writer(QueryChallengeDetailsForTeacherResponse.Writer.builder()
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
                .grade(section.getGrade())
                .classNum(section.getClassNum())
                .successStandard(challenge.getSuccessStandard())
                .isMine(user == challenge.getUser())
                .build();
    }
}
