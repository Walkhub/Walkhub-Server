package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QQueryChallengeParticipantsForStudentResponse_RelatedChallengeParticipants;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForStudentResponse;
import com.walkhub.walkhub.domain.user.domain.QUser;
import com.walkhub.walkhub.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.walkhub.walkhub.domain.challenge.domain.QChallengeStatus.challengeStatus;
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
    public List<QueryChallengeParticipantsForStudentResponse.RelatedChallengeParticipants> getRelatedChallengeParticipantsList(Long challengeId) {
        return queryFactory
                .select(new QQueryChallengeParticipantsForStudentResponse_RelatedChallengeParticipants(
                        user.id.as("userId"),
                        user.name,
                        user.profileImageUrl
                ))
                .from(user)
                .join(challengeStatus)
                .on(challengeStatus.user.eq(user))
                .where(challengeStatus.challenge.id.eq(challengeId))
                .orderBy()
                .limit(3)
                .fetch();

    }
}
