package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.walkhub.walkhub.domain.challenge.domain.QChallenge.challenge;
import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class CustomChallengeRepositoryImpl implements CustomChallengeRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChallengeParticipantsVO> queryChallengeParticipantsList(Challenge challenge, SuccessScope successScope) {
        return jpaQueryFactory
                .select(new QChallengeParticipantsVO(
                        user.id.as("userId"),
                        user.section.grade,
                        user.section.classNum,
                        user.number,
                        user.name,
                        user.profileImageUrl,
                        user.school.name.as("schoolName"),
                        challengeStatus.successCount.gt(challenge.getSuccessStandard()).as("isSuccess")
                ))
                .from(user)
                .join(exerciseAnalysis)
                .on(exerciseAnalysis.user.eq(user))
                .join(challengeStatus)
                .on(
                        challengeStatus.challenge.eq(challenge),
                        challengeStatus.user.eq(user)
                )
                .where(successScopeFilter(successScope))
                .fetch();
    }

    // 성공 스코프에 따른 필터링
    private BooleanExpression successScopeFilter(SuccessScope successScope) {
        switch (successScope) {
            case TRUE: {
                return challenge.successStandard.lt(challengeStatus.successCount);
            }
            case FALSE: {
                return challenge.successStandard.gt(challengeStatus.successCount);
            }
            default:
                return null;
        }
    }

}
