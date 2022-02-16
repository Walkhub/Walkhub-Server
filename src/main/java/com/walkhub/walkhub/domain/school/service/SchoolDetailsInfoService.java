package com.walkhub.walkhub.domain.school.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.facade.SchoolFacade;
import com.walkhub.walkhub.domain.school.presentation.dto.response.SchoolDetailsInfoResponse;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class SchoolDetailsInfoService {

    private final SchoolRankRepository schoolRankRepository;
    private final SchoolFacade schoolFacade;

    @Transactional(readOnly = true)
    public SchoolDetailsInfoResponse execute(Long schoolId) {
        School school = schoolFacade.getSchoolById(schoolId);

        LocalDate now = LocalDate.now();
        LocalDate weekCratedAt = now.minusWeeks(1);
        LocalDate monthCreatedAt = now.minusMonths(1);
        SchoolRank weekSchoolRanks = schoolRankRepository.findBySchoolIdAndDateTypeAndCreatedAtBetween(
                schoolId, DateType.WEEK.toString(), weekCratedAt, now
        );
        SchoolRank monthSchoolRanks = schoolRankRepository.findBySchoolIdAndDateTypeAndCreatedAtBetween(
                schoolId, DateType.MONTH.toString(), monthCreatedAt, now
        );

        return SchoolDetailsInfoResponse.builder()
                .userCount(school.getUserCount())
                .weekTotalWalkCount(weekSchoolRanks.getWalkCount())
                .monthTotalWalkCount(monthSchoolRanks.getWalkCount())
                .weekRanking(weekSchoolRanks.getRanking())
                .monthRanking(monthSchoolRanks.getRanking())
                .build();
    }

}
