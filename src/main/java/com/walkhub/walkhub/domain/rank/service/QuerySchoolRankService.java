package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.domain.type.SchoolDateType;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.MySchoolResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.SchoolResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class QuerySchoolRankService {

	private final SchoolRankRepository schoolRankRepository;
	private final UserFacade userFacade;

	public SchoolRankResponse execute(SchoolDateType dateType) {
		User user = userFacade.getCurrentUser();

		MySchoolResponse mySchoolResponse = schoolRankRepository.
			findBySchoolIdAndDateTypeAndCreatedAtBetween(user.getSchool().getId(), dateType.toString(), LocalDate.now().minusWeeks(1), LocalDate.now())
			.map(schoolRank -> mySchoolResponseBuilder(schoolRank, user))
			.orElse(null);

		List<SchoolResponse> schoolResponseList = schoolRankRepository
			.findAllByDateTypeAndCreatedAtBetweenOrderByRankingAsc(dateType.toString(), LocalDate.now().minusWeeks(1), LocalDate.now())
			.stream()
			.map(this::schoolResponseBuilder)
			.collect(Collectors.toList());

		return new SchoolRankResponse(mySchoolResponse, schoolResponseList);
	}

	private MySchoolResponse mySchoolResponseBuilder(SchoolRank schoolRank, User user) {
		Section section = user.hasSection() ? user.getSection() : Section.builder().build();

		return MySchoolResponse.builder()
			.schoolId(schoolRank.getSchoolId())
			.name(schoolRank.getName())
			.logoImageUrl(schoolRank.getLogoImageUrl())
			.grade(section.getGrade())
			.classNum(section.getClassNum())
			.build();
	}

	private SchoolResponse schoolResponseBuilder(SchoolRank schoolRank) {
		return SchoolResponse.builder()
			.schoolId(schoolRank.getSchoolId())
			.name(schoolRank.getName())
			.ranking(schoolRank.getRanking())
			.studentCount(schoolRank.getUserCount())
			.logoImageUrl(schoolRank.getLogoImageUrl())
			.walkCount(schoolRank.getWalkCount())
			.build();
	}
}
