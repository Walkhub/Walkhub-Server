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

        SchoolRank weekSchoolRank = buildWeekSchoolRank(schoolId, startDateOfLastWeek);
        SchoolRank monthSchoolRank = buildMonthSchoolRank(schoolId, createAt, now);

        return SchoolDetailsInfoResponse.builder()
                .week(buildDateSchoolRank(weekSchoolRank))
                .month(buildDateSchoolRank(monthSchoolRank))
                .build();
    }

    private SchoolRank buildWeekSchoolRank(Long schoolId, LocalDate now) {
        return schoolRankRepository
                .findBySchoolIdAndDateTypeAndCreatedAt(schoolId, DateType.WEEK.toString(), now)
                .orElseGet(() -> schoolRankRepository.findBySchoolIdAndDateTypeAndCreatedAt(
                        schoolId, DateType.WEEK.toString(), now.minusWeeks(1))
                .orElseGet(() -> SchoolRank.builder().build()));
    }

    private SchoolRank buildMonthSchoolRank(Long schoolId, LocalDate createAt, LocalDate now) {
        return schoolRankRepository
                .findBySchoolIdAndDateTypeAndCreatedAtBetween(
                        schoolId, DateType.MONTH.toString(), createAt, now)
                .orElseGet(() -> schoolRankRepository.findBySchoolIdAndDateTypeAndCreatedAtBetween(
                        schoolId, DateType.MONTH.toString(), createAt.minusWeeks(1), now)
                .orElseGet(() -> SchoolRank.builder().build()));
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
