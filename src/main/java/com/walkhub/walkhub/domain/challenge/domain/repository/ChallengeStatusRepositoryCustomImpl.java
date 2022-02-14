package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.QRelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.RelatedChallengeParticipantsVO;
import com.walkhub.walkhub.domain.school.domain.School;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
import static com.walkhub.walkhub.domain.user.domain.QSection.section;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class ChallengeStatusRepositoryCustomImpl implements ChallengeStatusRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Integer getParticipantsListByChallengeId(Long challengeId) {
        List<ChallengeStatus> participantsList = queryFactory
                .select(challengeStatus)
                .from(challengeStatus)
                .where(challengeStatus.challenge.id.eq(challengeId))
                .fetch();
        return participantsList.size();
    }

    @Override
    public List<RelatedChallengeParticipantsVO> getRelatedChallengeParticipantsList(Long challengeId, School school, Integer grade, Integer classNum) {
        return queryFactory
                .select(new QRelatedChallengeParticipantsVO(
                        user.id.as("userId"),
                        user.name,
                        user.profileImageUrl
                ))
                .from(user)
                .join(challengeStatus)
                .on(challengeStatus.user.eq(user))
                .join(section)
                .on(section.id.eq(user.section.id))
                .where(
                        challengeStatus.challenge.id.eq(challengeId),
                        user.school.eq(school)
                )
                .orderBy(
                        section.grade.subtract(grade).abs().asc(),
                        section.classNum.subtract(classNum).abs().asc(),
                        user.school.id.subtract(school.getId()).abs().asc()
                )
                .limit(3)
                .fetch();
    }
}
