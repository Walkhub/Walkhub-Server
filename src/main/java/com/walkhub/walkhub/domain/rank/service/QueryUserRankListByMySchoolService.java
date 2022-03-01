package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisCacheRepository;
import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisDto;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.domain.rank.facade.UserRankFacade;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.OurSchoolUserRankListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryUserRankListByMySchoolService {
    private final UserRankRepository userRankRepository;
    private final ExerciseAnalysisCacheRepository exerciseAnalysisCacheRepository;
    private final UserFacade userFacade;
    private final UserRankFacade userRankFacade;

    public OurSchoolUserRankListResponse execute(UserRankScope scope, DateType dateType) {
        User user = userFacade.getCurrentUser();
        LocalDate date = LocalDate.now();
        OurSchoolUserRankListResponse ourSchoolUserRankListResponse = null;

        if (dateType.equals(DateType.DAY)) {
            ourSchoolUserRankListResponse = buildDayRankResponse(user);
        } else if (scope.equals(UserRankScope.SCHOOL)) {
            ourSchoolUserRankListResponse = buildWeekOrMonthRankResponse(user, null, null, dateType, date);
        } else if (scope.equals(UserRankScope.CLASS)) {
            ourSchoolUserRankListResponse = buildWeekOrMonthRankResponse(user, user.getSection().getGrade(), user.getSection().getClassNum(), dateType, date);
        }

        return ourSchoolUserRankListResponse;
    }

    private OurSchoolUserRankListResponse buildDayRankResponse(User user) {
        OurSchoolUserRankListResponse.UserRankResponse myRank;
        List<OurSchoolUserRankListResponse.UserRankResponse> userRankList = new ArrayList<>();

        myRank = buildDayMyRank(user);

        List<ExerciseAnalysisDto> usersDayRank = exerciseAnalysisCacheRepository.getUserIdsByRankTop100(user.getSchool().getId());
        for (ExerciseAnalysisDto users : usersDayRank) {
            userRankList.add(buildDayUsersRank(users));
        }

        return OurSchoolUserRankListResponse.builder()
                .myRanking(myRank)
                .rankList(userRankList)
                .build();
    }

    private OurSchoolUserRankListResponse buildWeekOrMonthRankResponse(User user, Integer grade, Integer classNum, DateType dateType, LocalDate date) {
        OurSchoolUserRankListResponse.UserRankResponse myRank;
        List<OurSchoolUserRankListResponse.UserRankResponse> userRankList;

        myRank = buildWeekOrMonthMyRank(user.getId(), grade, classNum, dateType, date);

        List<UserRankVO> usersWeekOrMonthRank = userRankRepository.getUserRankListBySchoolId(user.getSchool().getId(), grade, classNum, dateType, date);
        userRankList = userRankFacade.buildWeekOrMonthUsersRankResponse(usersWeekOrMonthRank);

        return OurSchoolUserRankListResponse.builder()
                .myRanking(myRank)
                .rankList(userRankList)
                .build();
    }

    private OurSchoolUserRankListResponse.UserRankResponse buildDayMyRank(User user) {
        ExerciseAnalysisDto exerciseAnalysisDto = exerciseAnalysisCacheRepository.getUserTodayRank(user.getSchool().getId(), user.getId());
        if (exerciseAnalysisDto == null) {
            return null;
        }

        return OurSchoolUserRankListResponse.UserRankResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .ranking(exerciseAnalysisDto.getRanking())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(exerciseAnalysisDto.getWalkCount())
                .build();
    }

    private OurSchoolUserRankListResponse.UserRankResponse buildDayUsersRank(ExerciseAnalysisDto dayRank) {
        User user = userFacade.getUserById(dayRank.getUserId());

        return OurSchoolUserRankListResponse.UserRankResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .ranking(dayRank.getRanking())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(dayRank.getWalkCount())
                .build();
    }

    private OurSchoolUserRankListResponse.UserRankResponse buildWeekOrMonthMyRank(Long userId, Integer grade, Integer classNum, DateType dateType, LocalDate date) {
        UserRankVO myRank = userRankRepository.getMyRankByUserId(userId, grade, classNum, dateType, date);
        if (myRank == null) {
            return null;
        }

        return OurSchoolUserRankListResponse.UserRankResponse.builder()
                .userId(myRank.getUserId())
                .name(myRank.getName())
                .ranking(myRank.getRanking())
                .profileImageUrl(myRank.getProfileImageUrl())
                .walkCount(myRank.getWalkCount())
                .build();
    }
}
