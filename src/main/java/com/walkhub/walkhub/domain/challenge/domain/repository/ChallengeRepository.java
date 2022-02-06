package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

//	@Query("select c from Challenge c where c.scope = com.walkhub.walkhub.global.enums.Scope.ALL or "
//		+ "c.user.school = :school and (c.scope = com.walkhub.walkhub.global.enums.Scope.SCH or "
//		+ "(c.scope = com.walkhub.walkhub.global.enums.Scope.CLS and c.user.group = :group))")
//	List<Challenge> findAllByScope(@Param("school") School school, @Param("group") Group group);
//
//	@Query("select c from Challenge c join fetch c.user join fetch c.challengeStatuses where c.id = :challengeId")
//	Optional<Challenge> findChallengeById(@Param("challengeId") Long challengeId);
}
