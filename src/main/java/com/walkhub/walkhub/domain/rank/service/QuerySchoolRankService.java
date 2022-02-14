package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
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
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuerySchoolRankService {

	private final SchoolRankRepository schoolRankRepository;
	private final UserFacade userFacade;

	@Transactional(readOnly = true)
	public SchoolRankResponse execute(SchoolDateType dateType) {
		LocalDate localDate = LocalDate.now();
		if (dateType.equals(SchoolDateType.MONTH)) {
			localDate = localDate.minusMonths(1);
		} else {
			localDate = localDate.minusWeeks(1);
		}

		User user = userFacade.getCurrentUser();
		SchoolRank mySchoolRank = schoolRankRepository.
			findBySchoolIdAndDateTypeAndCreatedAtBetween(user.getSchool().getId(), dateType.toString(), localDate, LocalDate.now());

		MySchoolResponse mySchoolResponse = MySchoolResponse.builder()
			.schoolId(mySchoolRank.getSchoolId())
			.name(mySchoolRank.getName())
			.logoImageUrl(mySchoolRank.getLogoImageUrl())
			.grade(user.getSection().getGrade())
			.classNum(user.getSection().getClassNum())
			.build();

		List<SchoolResponse> schoolResponseList = schoolRankRepository
			.findAllByDateTypeAndCreatedAtBetweenOrderByRankingAsc(dateType.toString(), localDate, LocalDate.now())
			.stream()
			.map(schoolRank -> SchoolResponse.builder()
				.schoolId(schoolRank.getSchoolId())
				.name(schoolRank.getName())
				.ranking(schoolRank.getRanking())
				.studentCount(schoolRank.getUserCount())
				.logoImageUrl(schoolRank.getLogoImageUrl())
				.walkCount(schoolRank.getWalkCount())
				.build())
			.collect(Collectors.toList());

		return new SchoolRankResponse(mySchoolResponse, schoolResponseList);
	}
}
