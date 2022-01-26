package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.School;
import com.walkhub.walkhub.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

	@Query("select c from Challenge c where c.scope = com.walkhub.walkhub.global.enums.Scope.ALL or "
		+ "c.user.school = :school and (c.scope = com.walkhub.walkhub.global.enums.Scope.SCH or "
		+ "(c.scope = com.walkhub.walkhub.global.enums.Scope.CLS and c.user.group = :group))")
	List<Challenge> findAllByScope(School school, Group group);
	@Query("select c from Challenge c join fetch c.user join fetch c.challengeStatuses where c.id = :challengeId")
	Optional<Challenge> findChallengeById(@Param("challengeId") Long challengeId);
}
