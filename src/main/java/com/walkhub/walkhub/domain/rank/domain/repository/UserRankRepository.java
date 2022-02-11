package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.UserRankInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRankRepository extends JpaRepository<UserRankInfo, String>, UserRankRepositoryCustom {
    List<UserRankInfo> findTop100ByNameContainsAndAgencyCode(String name, String agencyCode);
    List<UserRankInfo> findTop100ByNameContainsAndAgencyCodeAndClassNumAndGrade(String name, String agencyCode, Integer grade, Integer classNum);
}
