package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.SchoolRankId;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface SchoolRankRepository extends CrudRepository<SchoolRank, SchoolRankId> {
    SchoolRank findBySchoolIdAndDateTypeAndCreatedAtBetween(
            Long schoolId, String dateType, LocalDate createdAt, LocalDate now
    );
    List<SchoolRank> findAllByDateTypeAndCreatedAtBetweenOrderByRankingAsc(
            String dateType, LocalDate createdAt, LocalDate now
    );
}
