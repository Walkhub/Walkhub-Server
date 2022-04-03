package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.SchoolRankId;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SchoolRankRepository extends CrudRepository<SchoolRank, SchoolRankId> {
    Optional<SchoolRank> findBySchoolIdAndDateTypeAndCreatedAtBetween(
            Long schoolId, String dateType, LocalDate startAt, LocalDate endAt
    );

    List<SchoolRank> findAllByDateTypeAndNameContainingAndCreatedAtBetweenOrderByRankingAsc(
            String dateType, String name, LocalDate startAt, LocalDate endAt
    );
}
