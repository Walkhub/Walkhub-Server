package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.domain.SchoolRank;
import com.walkhub.walkhub.domain.rank.domain.repository.SchoolRankRepository;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.MySchoolResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryMySchoolRankService {

    private final SchoolRankRepository schoolRankRepository;
    private final UserFacade userFacade;

    public SchoolRankResponse execute(Long schoolId) {
        User user = userFacade.getCurrentUser();
        LocalDate now = LocalDate.now();

        MySchoolResponse mySchoolResponse = schoolRankRepository.findAllBySchoolId(schoolId)
                .map(schoolRank -> mySchoolResponseBuilder(schoolRank, user))
                .orElse(null);

        return new SchoolRankResponse(mySchoolResponse);
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
}
