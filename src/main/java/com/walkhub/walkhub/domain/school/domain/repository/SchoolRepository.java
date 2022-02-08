package com.walkhub.walkhub.domain.school.domain.repository;

import com.walkhub.walkhub.domain.school.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findAllByNameContaining(String name);
    Optional<School> findByAgencyCode(String agencyCode);
}
