package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.SchoolRankId;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SchoolRankRepository extends CrudRepository<SchoolRank, SchoolRankId> {

    Optional<SchoolRank> findBySchoolIdAndDateTypeAndCreatedAt(Long schoolId, String dateType, LocalDate createAt);

    List<SchoolRank> findAllByDateTypeAndCreatedAtAndNameContainingOrderByName(String dateType, LocalDate createAt, String name);

    List<SchoolRank> findAllByDateTypeAndCreatedAtAndNameContainingOrderByRanking(String dateType, LocalDate createAt, String name);
}
