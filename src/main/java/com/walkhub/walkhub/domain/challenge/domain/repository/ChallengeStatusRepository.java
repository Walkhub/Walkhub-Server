package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatusId;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeStatusRepository extends CrudRepository<ChallengeStatus, ChallengeStatusId> {
//    Optional<ChallengeStatus> findByUserAndChallenge(User user, Challenge challenge);
//    List<ChallengeStatus> findByChallengeId(Long challengeId);
}
