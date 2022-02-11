package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatusId;
import com.walkhub.walkhub.domain.user.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChallengeStatusRepository extends CrudRepository<ChallengeStatus, ChallengeStatusId> {
    Optional<ChallengeStatus> findByChallengeAndUser(Challenge challenge, User user);
    List<ChallengeStatus> findAllByUser(User user);
    List<ChallengeStatus> findAllByChallenge(Challenge challenge);
}
