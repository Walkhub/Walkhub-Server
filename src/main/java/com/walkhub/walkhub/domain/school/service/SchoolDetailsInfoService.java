package com.walkhub.walkhub.domain.school.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.school.presentation.dto.response.SchoolDetailsInfoResponse;
import com.walkhub.walkhub.domain.school.presentation.dto.response.SchoolDetailsInfoResponse.DateRankResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SchoolDetailsInfoService {

    private final SchoolRankRepository schoolRankRepository;

    public SchoolDetailsInfoResponse execute(Long schoolId) {
        LocalDate now = LocalDate.now();
        LocalDate createAt = now.minusWeeks(1);
        LocalDate startDateOfLastWeek = now.minusDays(now.getDayOfWeek().getValue() - 1);

        SchoolRank weekSchoolRank = schoolRankRepository
                .findByDateTypeAndCreatedAt(DateType.WEEK.toString(), startDateOfLastWeek)
                .orElseGet(() -> schoolRankRepository.findByDateTypeAndCreatedAt(
                            DateType.WEEK.toString(), startDateOfLastWeek.minusWeeks(1))
                .orElseGet(() -> SchoolRank.builder().build()));

        SchoolRank monthSchoolRank = schoolRankRepository
                .findBySchoolIdAndDateTypeAndCreatedAtBetween(
                        schoolId, DateType.MONTH.toString(), createAt, now)
                .orElseGet(() -> SchoolRank.builder().build());

        return SchoolDetailsInfoResponse.builder()
                .week(buildDateSchoolRank(weekSchoolRank))
                .month(buildDateSchoolRank(monthSchoolRank))
                .build();
    }

    private DateRankResponse buildDateSchoolRank(SchoolRank schoolRank) {
        return DateRankResponse.builder()
                .userCount(schoolRank.getUserCount())
                .date(schoolRank.getCreatedAt())
                .totalWalkCount(schoolRank.getWalkCount())
                .ranking(schoolRank.getRanking())
                .build();
    }

}
