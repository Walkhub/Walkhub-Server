package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QShowChallengeVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeVO;
import com.walkhub.walkhub.domain.user.domain.QUser;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.challenge.domain.QChallenge.challenge;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class ChallengeRepositoryCustomImpl implements ChallengeRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<ShowChallengeVO> queryChallenge(User userParam) {
        return query
                .select(showChallengeVO())
                .from(challenge)
                .join(challenge.user, user)
                .where(
                        (challenge.userScope.eq(UserScope.ALL).or(challenge.user.school.eq(userParam.getSchool()))),
                        (challenge.endAt.after(LocalDate.now()))
                )
                .fetch();
    }

    @Override
    public List<ShowChallengeVO> queryClosedChallenge(User userParam) {
        return query
                .select(showChallengeVO())
                .from(challenge)
                .join(challenge.user, user)
                .where(
                        (challenge.userScope.eq(UserScope.ALL).or(challenge.user.school.eq(userParam.getSchool()))),
                        (challenge.endAt.before(LocalDate.now()))
                )
                .fetch();
    }

    private QShowChallengeVO showChallengeVO() {
        return new QShowChallengeVO(
                challenge.id.as("challengeId"),
                challenge.name,
                challenge.startAt,
                challenge.endAt,
                challenge.imageUrl,
                challenge.userScope,
                challenge.goalScope,
                challenge.goalType,
                user.id.as("writerId"),
                user.name.as("writerName"),
                user.profileImageUrl.as("profileImageUrl")
        );
    }
}
