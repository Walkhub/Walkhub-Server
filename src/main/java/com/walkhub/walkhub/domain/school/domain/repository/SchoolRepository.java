package com.walkhub.walkhub.domain.school.domain.repository;

import com.walkhub.walkhub.domain.school.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, String> {
}
