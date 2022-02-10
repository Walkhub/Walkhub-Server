package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

	@Query("select c from Challenge c where c.userScope = com.walkhub.walkhub.global.enums.UserScope.ALL or "
		+ "c.user.school = :school and (c.userScope = com.walkhub.walkhub.global.enums.UserScope.SCHOOL or "
		+ "c.user.group.grade = :grade and (c.userScope = com.walkhub.walkhub.global.enums.UserScope.GRADE or "
		+ "c.user.group = :group and (c.userScope = com.walkhub.walkhub.global.enums.UserScope.CLASS)))")
	List<Challenge> findAllByScope(@Param("school") School school, @Param("grade") Integer grade, @Param("group") Group group);

//	@Query("select c from Challenge c join fetch c.user join fetch c.challengeStatuses where c.id = :challengeId")
//	Optional<Challenge> findChallengeById(@Param("challengeId") Long challengeId);

}
