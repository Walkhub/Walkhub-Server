package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.SchoolRankId;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SchoolRankRepository extends CrudRepository<SchoolRank, SchoolRankId>, SchoolRankRepositoryCustom {

    Optional<SchoolRank> findBySchoolIdAndDateTypeAndCreatedAt(Long schoolId, String dateType, LocalDate createAt);
}
