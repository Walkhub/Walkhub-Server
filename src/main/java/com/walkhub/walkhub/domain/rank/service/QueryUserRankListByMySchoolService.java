package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisCacheRepository;
import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisDto;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.domain.rank.facade.UserRankFacade;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
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

    public UserRankListResponse execute(UserRankScope scope, DateType dateType) {
        User user = userFacade.getCurrentUser();
        LocalDate date = LocalDate.now();
        UserRankListResponse.UserRankResponse myRank = null;
        List<UserRankListResponse.UserRankResponse> userRankList = new ArrayList<>();
        if (dateType.equals(DateType.DAY)) {
            myRank = buildDayMyRankResponse(user);
            List<ExerciseAnalysisDto> usersDayRank = exerciseAnalysisCacheRepository.getUserIdsByRankTop100();
            for (ExerciseAnalysisDto users : usersDayRank) {
                userRankList.add(buildDayUsersRankResponse(users));
            }
        } else if (scope.equals(UserRankScope.ALL)) {
            myRank = buildWeekOrMonthMyRankResponse(user.getId(), null, null, dateType, date);
            List<UserRankVO> usersWeekOrMonthRank = userRankRepository.getUserRankListBySchoolId(user.getSchool().getId(), user.getSection().getGrade(), null, dateType, date);
            userRankList = userRankFacade.buildWeekOrMonthUsersRankResponse(usersWeekOrMonthRank);
        } else if (scope.equals(UserRankScope.CLASS)) {
            myRank = buildWeekOrMonthMyRankResponse(user.getId(), user.getSection().getGrade(), user.getSection().getClassNum(), dateType, date);
            List<UserRankVO> usersWeekOrMonthRank = userRankRepository.getUserRankListBySchoolId(user.getSchool().getId(), user.getSection().getGrade(), user.getSection().getClassNum(), dateType, date);
            userRankList = userRankFacade.buildWeekOrMonthUsersRankResponse(usersWeekOrMonthRank);
        }
        return UserRankListResponse.builder()
                .myRank(myRank)
                .rankList(userRankList)
                .build();
    }

    private UserRankListResponse.UserRankResponse buildDayMyRankResponse(User user) {
        ExerciseAnalysisDto exerciseAnalysisDto = exerciseAnalysisCacheRepository.getUserTodayRank(user.getId());
        if (exerciseAnalysisDto == null) {
            return null;
        }
        return UserRankListResponse.UserRankResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .grade(user.getSection().getGrade())
                .classNum(user.getSection().getClassNum())
                .ranking(exerciseAnalysisDto.getRanking())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(exerciseAnalysisDto.getWalkCount())
                .build();
    }

    private UserRankListResponse.UserRankResponse buildDayUsersRankResponse(ExerciseAnalysisDto dayRank) {
        User user = userFacade.getUserById(dayRank.getUserId());
        return UserRankListResponse.UserRankResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .grade(user.getSection().getGrade())
                .classNum(user.getSection().getClassNum())
                .ranking(dayRank.getRanking())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(dayRank.getWalkCount())
                .build();
    }

    private UserRankListResponse.UserRankResponse buildWeekOrMonthMyRankResponse(Long userId, Integer grade, Integer classNum, DateType dateType, LocalDate date) {
        UserRankVO myRank = userRankRepository.getMyRankByUserId(userId, grade, classNum, dateType, date);
        if (myRank == null) {
            return null;
        }
        return UserRankListResponse.UserRankResponse.builder()
                .userId(myRank.getUserId())
                .name(myRank.getName())
                .grade(myRank.getGrade())
                .classNum(myRank.getClassNum())
                .ranking(myRank.getRanking())
                .profileImageUrl(myRank.getProfileImageUrl())
                .walkCount(myRank.getWalkCount())
                .build();
    }
}
