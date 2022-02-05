package com.walkhub.walkhub.domain.rank.domain.repository;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.SchoolRankId;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRankRepository extends CrudRepository<SchoolRank, SchoolRankId> {
}
