package com.walkhub.walkhub.domain.school.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.school.presentation.dto.response.SchoolDetailsInfoResponse;
import com.walkhub.walkhub.domain.school.presentation.dto.response.SchoolDetailsInfoResponse.DateRankResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SchoolDetailsInfoService {

    private final SchoolRankRepository schoolRankRepository;

    public SchoolDetailsInfoResponse execute(Long schoolId) {
        LocalDate now = LocalDate.now();
        LocalDate createdAt = now.minusDays(now.getDayOfWeek().getValue() - 1L);

        SchoolRank weekSchoolRank = buildWeekOrMonthSchoolRank(schoolId, SchoolDateType.WEEK, createdAt);
        SchoolRank monthSchoolRank = buildWeekOrMonthSchoolRank(schoolId, SchoolDateType.MONTH, createdAt);

        return SchoolDetailsInfoResponse.builder()
                .week(buildDateSchoolRank(weekSchoolRank))
                .month(buildDateSchoolRank(monthSchoolRank))
                .build();
    }

    private SchoolRank buildWeekOrMonthSchoolRank(Long schoolId, SchoolDateType dateType, LocalDate createdAt) {
        return schoolRankRepository
                .findBySchoolIdAndDateTypeAndCreatedAt(schoolId, dateType, createdAt)
                .orElseGet(() -> schoolRankRepository.findBySchoolIdAndDateTypeAndCreatedAt(
                        schoolId, dateType, createdAt.minusWeeks(1))
                .orElseGet(() -> SchoolRank.builder().build()));
    }

    private DateRankResponse buildDateSchoolRank(SchoolRank schoolRank) {
        return DateRankResponse.builder()
                .totalUserCount(schoolRank.getUserCount())
                .date(schoolRank.getCreatedAt())
                .totalWalkCount(schoolRank.getWalkCount())
                .ranking(schoolRank.getRanking())
                .build();
    }

}
