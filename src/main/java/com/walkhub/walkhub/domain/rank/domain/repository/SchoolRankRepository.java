package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.SchoolRankId;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SchoolRankRepository extends CrudRepository<SchoolRank, SchoolRankId> {

    Optional<SchoolRank> findBySchoolIdAndDateTypeAndCreatedAt(Long schoolId, SchoolDateType dateType, LocalDate createdAt);

    List<SchoolRank> findAllByDateTypeAndCreatedAtAndNameContainingOrderByName(SchoolDateType dateType, LocalDate createdAt, @NotNull @Size(max = 20) String name);

    List<SchoolRank> findAllByDateTypeAndCreatedAtAndNameContainingOrderByRanking(SchoolDateType dateType, LocalDate createdAt, @NotNull @Size(max = 20) String name);

    List<SchoolRank> findAllByDateTypeAndCreatedAtOrderByName(SchoolDateType dateType, LocalDate createdAt);

    List<SchoolRank> findAllByDateTypeAndCreatedAtOrderByRanking(SchoolDateType dateType, LocalDate createdAt);

}
