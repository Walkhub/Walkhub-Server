package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.SchoolRankId;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SchoolRankRepository extends CrudRepository<SchoolRank, SchoolRankId>, SchoolRankRepositoryCustom {

    Optional<SchoolRank> findBySchoolIdAndDateTypeAndCreatedAt(Long schoolId, SchoolDateType dateType, LocalDate createdAt);

}
