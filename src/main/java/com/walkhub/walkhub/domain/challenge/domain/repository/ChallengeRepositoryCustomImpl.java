package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.*;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.walkhub.walkhub.domain.challenge.domain.QChallenge.challenge;
import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class ChallengeRepositoryCustomImpl implements ChallengeRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<ShowChallengeVO> queryChallengeListForStudent(User userParam) {
        return query
                .select(new QShowChallengeVO(
                        challenge.id.as("challengeId"),
                        challenge.name,
                        challenge.imageUrl,
                        challenge.startAt,
                        challenge.endAt,
                        challenge.goal,
                        challenge.goalScope,
                        challenge.goalType,
                        challenge.award,
                        user.id.as("writerId"),
                        user.name.as("writerName"),
                        user.profileImageUrl.as("profileImageUrl"),
                        getParticipantCountByChallenge()
                ))
                .from(challenge)
                .join(challenge.user, user)
                .leftJoin(challenge.challengeStatuses, challengeStatus)
                .where(
                        (challenge.userScope.eq(UserScope.ALL).or(user.school.eq(userParam.getSchool()))),
                        (challenge.endAt.after(LocalDate.now()))
                )
                .fetch();
    }

    @Override
    public List<ShowChallengeListForTeacherVo> queryChallengeListForTeacher(User userParam, Boolean isProgress) {
        return query
                .select(new QShowChallengeListForTeacherVo(
                        challenge.id,
                        challenge.name,
                        challenge.imageUrl,
                        challenge.startAt,
                        challenge.endAt,
                        challenge.goal,
                        challenge.goalScope,
                        challenge.goalType,
                        user.id,
                        user.name,
                        user.profileImageUrl,
                        getParticipantCountByChallenge()
                ))
                .from(challenge)
                .join(challenge.user, user)
                .where(
                        (challenge.userScope.eq(UserScope.ALL).or(challenge.user.school.eq(userParam.getSchool()))),
                        dateFilter(isProgress)
                )
                .fetch();
    }

    private BooleanExpression dateFilter(Boolean isProgress) {
        LocalDate now = LocalDate.now();

        if (isProgress == null) {
            return null;
        }
        else if (isProgress.equals(true)) {
            return challenge.startAt.before(now).and(challenge.endAt.after(now));
        } else if (isProgress.equals(false)) {
            return challenge.startAt.after(now).or(challenge.endAt.before(now));
        } else {
            return null;
        }
    }

    @Override
    public ChallengeDetailsForStudentVO queryChallengeDetailsForStudent(Challenge challengeParam, User userParam) {
        return query
                .select(new QChallengeDetailsForStudentVO(
                        challenge.name,
                        challenge.content,
                        challenge.userScope,
                        challenge.goal,
                        challenge.goalScope,
                        challenge.goalType,
                        challenge.award,
                        challenge.imageUrl,
                        challenge.startAt,
                        challenge.endAt,
                        challenge.successStandard,
                        user.id.as("writerId"),
                        user.name.as("writerName"),
                        user.profileImageUrl.as("profileImageUrl"),
                        user.eq(userParam),
                        challengeStatus.user.eq(userParam),
                        getParticipantCountByChallenge()
                ))
                .from(challenge)
                .join(challenge.user, user)
                .leftJoin(challengeStatus)
                .on(challengeStatus.challenge.eq(challenge))
                .where(challenge.id.eq(challengeParam.getId()))
                .fetchOne();
    }

    private Expression<Long> getParticipantCountByChallenge() {
        List<ChallengeStatus> challengeStatuses = query
                .selectFrom(challengeStatus)
                .join(challengeStatus.challenge, challenge)
                .fetch();

        Long participantCount = challengeStatuses.size() > 3 ? (long) challengeStatuses.size() - 3 : 0;
        return Expressions.constant(participantCount);
    }

    @Override
    public List<RelatedChallengeParticipantsVO> getRelatedChallengeParticipantsList(Long challengeId, User currentUser) {
        Section currentUserSection = currentUser.hasSection() ? currentUser.getSection() : Section.builder().build();

        return query
                .select(new QRelatedChallengeParticipantsVO(
                        user.id.as("userId"),
                        user.name,
                        user.profileImageUrl
                ))
                .from(user)
                .join(challenge)
                .on(challenge.id.eq(challengeId))
                .join(challengeStatus)
                .on(challengeStatus.user.eq(user),
                        challengeStatus.challenge.eq(challenge))
                .join(section)
                .on(section.eq(user.section))
                .where(
                        user.school.eq(currentUser.getSchool())
                )
                .orderBy(
                        section.grade.subtract(currentUserSection.getGrade()).abs().asc(),
                        section.classNum.subtract(currentUserSection.getClassNum()).abs().asc(),
                        user.school.id.subtract(currentUser.getSchool().getId()).abs().asc()
                )
                .limit(3)
                .fetch();
    }
}
