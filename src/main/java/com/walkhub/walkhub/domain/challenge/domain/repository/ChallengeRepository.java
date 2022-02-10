package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.Section;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

	@Query("select c from Challenge c where c.user.school = :school")
	List<Challenge> findAllBySchool(@Param("school") School school);
}
