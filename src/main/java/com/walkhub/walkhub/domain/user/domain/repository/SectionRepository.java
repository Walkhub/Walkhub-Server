package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByClassCodeAndSchool(String classCode, School school);

    Optional<Section> findBySchoolAndGradeAndClassNum(School school, Integer grade, Integer classNum);

    Optional<Section> findByClassCode(String classCode);
}
