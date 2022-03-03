package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisCacheRepository;
import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisDto;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.domain.rank.facade.UserRankFacade;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse.UserRankResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryUserRankListByMySchoolService {
    private final UserRankRepository userRankRepository;
    private final ExerciseAnalysisCacheRepository exerciseAnalysisCacheRepository;
    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final UserRankFacade userRankFacade;

    @Transactional(readOnly = true)
    public UserRankListResponse execute(UserRankScope scope, DateType dateType) {
        User user = userFacade.getCurrentUser();
        LocalDate date = LocalDate.now();
        UserRankListResponse userRankListResponse = null;

        if (dateType.equals(DateType.DAY)) {
            userRankListResponse = buildDayRankResponse(user);
        } else if (scope.equals(UserRankScope.SCHOOL)) {
            userRankListResponse = buildWeekOrMonthRankResponse(user, null, null, dateType, date);
        } else if (scope.equals(UserRankScope.CLASS)) {
            Section userSection = user.hasSection() ? user.getSection() : Section.builder().build();
            userRankListResponse = buildWeekOrMonthRankResponse(user, userSection.getGrade(), userSection.getClassNum(), dateType, date);
        }

        return userRankListResponse;
    }

    private UserRankListResponse buildDayRankResponse(User user) {

        UserRankResponse myRank = buildDayMyRank(user);
        List<UserRankResponse> userRankList = new ArrayList<>();
        List<ExerciseAnalysisDto> usersDayRank = exerciseAnalysisCacheRepository.getUserIdsByRankTop100(user.getSchool().getId());

        List<Long> userIds = usersDayRank.stream()
                .map(ExerciseAnalysisDto::getUserId)
                .collect(Collectors.toList());

        userRepository.findAllByIdIn(userIds);
        for (ExerciseAnalysisDto users : usersDayRank) {
            userRankList.add(buildDayUsersRank(users));
        }

        return UserRankListResponse.builder()
                .myRanking(myRank)
                .rankList(userRankList)
                .build();
    }

    private UserRankListResponse buildWeekOrMonthRankResponse(User user, Integer grade, Integer classNum, DateType dateType, LocalDate date) {

        UserRankResponse myRank = buildWeekOrMonthMyRank(user.getId(), grade, classNum, dateType, date);
        List<UserRankVO> usersWeekOrMonthRank = userRankRepository.getUserRankListBySchoolId(user.getSchool().getId(), grade, classNum, dateType, date);
        List<UserRankResponse> userRankList = userRankFacade.buildWeekOrMonthUsersRankResponse(usersWeekOrMonthRank);

        return UserRankListResponse.builder()
                .myRanking(myRank)
                .rankList(userRankList)
                .build();
    }

    private UserRankResponse buildDayMyRank(User user) {
        ExerciseAnalysisDto exerciseAnalysisDto = exerciseAnalysisCacheRepository.getUserTodayRank(user.getSchool().getId(), user.getId());
        if (exerciseAnalysisDto == null) {
            return null;
        }

        return UserRankResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .ranking(exerciseAnalysisDto.getRanking())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(exerciseAnalysisDto.getWalkCount())
                .isMeasuring(userRankFacade.isMeasuringByUserId(user.getId()))
                .build();
    }

    private UserRankResponse buildDayUsersRank(ExerciseAnalysisDto dayRank) {
        User user = userFacade.getUserById(dayRank.getUserId());

        return UserRankResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .ranking(dayRank.getRanking())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(dayRank.getWalkCount())
                .isMeasuring(userRankFacade.isMeasuringByUserId(user.getId()))
                .build();
    }

    private UserRankResponse buildWeekOrMonthMyRank(Long userId, Integer grade, Integer classNum, DateType dateType, LocalDate date) {
        UserRankVO myRank = userRankRepository.getMyRankByUserId(userId, grade, classNum, dateType, date);
        if (myRank == null) {
            return null;
        }

        return UserRankResponse.builder()
                .userId(myRank.getUserId())
                .name(myRank.getName())
                .ranking(myRank.getRanking())
                .profileImageUrl(myRank.getProfileImageUrl())
                .walkCount(myRank.getWalkCount())
                .isMeasuring(userRankFacade.isMeasuringByUserId(userId))
                .build();
    }

}
