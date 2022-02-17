package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolListResponse.SchoolResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchoolSearchService {

	private final SchoolRankRepository schoolRankRepository;

	public SchoolListResponse execute(String name, SchoolDateType dateType) {
		List<SchoolResponse> schoolResponseList = schoolRankRepository
			.findAllByDateTypeAndNameContainingAndCreatedAtBetweenOrderByRankingAsc(dateType.toString(), name,
				LocalDate
					.now().minusWeeks(1), LocalDate.now())
			.stream()
			.map(schoolRank -> SchoolResponse.builder()
				.schoolId(schoolRank.getSchoolId())
				.logoImageUrl(schoolRank.getLogoImageUrl())
				.schoolName(schoolRank.getName())
				.ranking(schoolRank.getRanking())
				.walkCount(schoolRank.getWalkCount())
				.build())
			.collect(Collectors.toList());

		return new SchoolListResponse(schoolResponseList);
	}
}