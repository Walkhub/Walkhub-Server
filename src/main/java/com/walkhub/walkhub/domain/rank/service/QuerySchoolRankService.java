package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.type.DateType;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.MySchoolResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.SchoolResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuerySchoolRankService {

	private final SchoolRankRepository schoolRankRepository;
	private final UserFacade userFacade;

	public SchoolRankResponse execute(DateType dateType) {
		LocalDate localDate = LocalDate.now();
		switch (dateType) {
			case WEEK: localDate = localDate.minusWeeks(1);
			break;
			case MONTH: localDate = localDate.minusMonths(1);
			break;
		}

		User user = userFacade.getCurrentUser();
		SchoolRank schoolRank = schoolRankRepository.
			findBySchoolIdAndDateTypeAndCreatedAtBetween(user.getSchool().getId(), dateType.toString(), localDate, LocalDate.now());

		MySchoolResponse mySchoolResponse = MySchoolResponse.builder()
			.schoolId(schoolRank.getSchoolId())
			.name(schoolRank.getName())
			.logoImageUrl(schoolRank.getLogoImageUrl())
			.walkCount(schoolRank.getWalkCount())
			.grade(user.getSection().getGrade())
			.classNum(user.getSection().getClassNum())
			.build();

		List<SchoolResponse> schoolResponseList = schoolRankRepository
			.findAllByDateTypeAndCreatedAtBetweenOrderByRankingDesc(dateType.toString(), localDate, LocalDate.now())
			.stream()
			.map(schoolRank2 -> SchoolResponse.builder()
				.schoolId(schoolRank2.getSchoolId())
				.name(schoolRank2.getName())
				.rank(schoolRank2.getRanking())
				.studentCount(schoolRank2.getUserCount())
				.logoImageUrl(schoolRank2.getLogoImageUrl())
				.walkCount(schoolRank2.getWalkCount())
				.build())
			.collect(Collectors.toList());

		return new SchoolRankResponse(mySchoolResponse, schoolResponseList);
	}
}
