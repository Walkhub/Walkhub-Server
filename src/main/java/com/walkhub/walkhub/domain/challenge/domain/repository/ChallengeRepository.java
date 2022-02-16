package com.walkhub.walkhub.domain.challenge.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.school.domain.School;

import java.time.LocalDate;
import java.util.List;

import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeRepository extends CrudRepository<Challenge, Long>, ChallengeRepositoryCustom {

	@Query("select c from Challenge c where c.user.school = :school")
	List<Challenge> findAllBySchool(@Param("school") School school);

	List<Challenge> findAllByUser(User user);

	void deleteAllByUserAndEndAtAfter(User user, LocalDate now);

}
