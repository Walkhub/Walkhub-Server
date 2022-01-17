package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRankRepository extends JpaRepository<UserRank, String> {
    List<UserRank> findTop100ByNameContainsAndAgencyCode(String name, String agencyCode);
    List<UserRank> findTop100ByNameContainsAndAgencyCodeAndClassNumAndGrade(String name, String agencyCode, Integer grade, Integer classNum);
}
