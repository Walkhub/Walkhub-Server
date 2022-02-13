package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.type.SuccessScope;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.walkhub.walkhub.domain.challenge.domain.QChallenge.challenge;
import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
import static com.walkhub.walkhub.domain.exercise.domain.QExerciseAnalysis.exerciseAnalysis;
import static com.walkhub.walkhub.domain.school.domain.QSchool.school;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class CustomChallengeRepositoryImpl implements CustomChallengeRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChallengeParticipantsVO> queryChallengeParticipantsList(Long challengeId, SuccessScope successScope) {
        return jpaQueryFactory
                .selectFrom(user)
                .join(user.school, school)
                .leftJoin(user.section, section)
                .join(user.exerciseAnalyses, exerciseAnalysis)
                .join(user.challengeStatuses, challengeStatus)
                .join(challengeStatus.challenge, challenge)
                .where(
                        challenge.id.eq(challengeId),
                        successScopeFilter(successScope),
                        exerciseAnalysis.date.between(challenge.startAt, challenge.endAt)
                )
                .orderBy(user.name.asc(), user.id.asc())
                .transform(groupBy(user.name, user.id)
                        .list(new QChallengeParticipantsVO(
                                user.id.as("userId"),
                                user.section.grade,
                                user.section.classNum,
                                user.number,
                                user.name,
                                user.profileImageUrl,
                                user.school.name.as("schoolName"),
                                challengeStatus.successCount.goe(challenge.successStandard).as("isSuccess"),
                                GroupBy.list(exerciseAnalysis.date))
                        )
                );
    }

    private BooleanExpression successScopeFilter(SuccessScope successScope) {
        switch (successScope) {
            case TRUE: {
                return challengeStatus.successCount.goe(challenge.successStandard);
            }
            case FALSE: {
                return challengeStatus.successCount.lt(challenge.successStandard);
            }
            default: {
                return null;
            }
        }
    }

}
