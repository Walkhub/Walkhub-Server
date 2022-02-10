package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;
import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatusId;
import com.walkhub.walkhub.domain.user.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeStatusRepository extends CrudRepository<ChallengeStatus, ChallengeStatusId> {
	List<ChallengeStatus> findAllByUser(User user);
}
