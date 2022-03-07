package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.UserRank;
import com.walkhub.walkhub.global.enums.DateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRankRepository extends JpaRepository<UserRank, Long>, UserRankRepositoryCustom {
    List<UserRank> findAllBySchoolIdAndNameContainingAndDateType(Long schoolId, String name, DateType dateType);

    Integer countAllByUserIdAndRankingAndDateType(Long userId, Integer ranking, DateType dateType);
}
