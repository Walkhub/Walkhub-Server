package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.School;
import com.walkhub.walkhub.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

	@Query("select c from Challenge c where c.scope = com.walkhub.walkhub.global.enums.Scope.ALL or "
		+ "c.user.school = :school and (c.scope = com.walkhub.walkhub.global.enums.Scope.SCH or "
		+ "(c.scope = com.walkhub.walkhub.global.enums.Scope.CLS and c.user.group = :group))")
	List<Challenge> findAllByScope(School school, Group group);
}
