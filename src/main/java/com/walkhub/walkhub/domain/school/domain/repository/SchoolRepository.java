package com.walkhub.walkhub.domain.school.domain.repository;

import com.walkhub.walkhub.domain.school.domain.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findAllByNameContaining(String name);
    Page<School> findAllBy(Pageable pageable);
}
