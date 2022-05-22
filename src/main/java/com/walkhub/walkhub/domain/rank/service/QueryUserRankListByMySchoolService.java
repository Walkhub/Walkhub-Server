package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisCacheRepository;
import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisDto;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.domain.rank.facade.UserRankFacade;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListByMySchoolResponse;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListByMySchoolResponse.UserRankResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryUserRankListByMySchoolService {
    private final UserRankRepository userRankRepository;
    private final ExerciseAnalysisCacheRepository exerciseAnalysisCacheRepository;
    private final UserFacade userFacade;
    private final UserRankFacade userRankFacade;

    public UserRankListByMySchoolResponse execute(UserRankScope scope, DateType dateType) {
        User user = userFacade.getCurrentUser();
        LocalDate date = LocalDate.now();
        UserRankListByMySchoolResponse userRankListResponse = null;

        if (DateType.DAY.equals(dateType)) {
            userRankListResponse = buildDayRankResponse(user);
        } else if (UserRankScope.SCHOOL.equals(scope)) {
            userRankListResponse = buildWeekOrMonthRankResponse(user, null, null, dateType, date);
        } else if (UserRankScope.CLASS.equals(scope)) {
            Section userSection = user.hasSection() ? user.getSection() : Section.builder().build();
            userRankListResponse = buildWeekOrMonthRankResponse(user, userSection.getGrade(),
                    userSection.getClassNum(), dateType, date);
        }

        return userRankListResponse;
    }

    private UserRankListByMySchoolResponse buildDayRankResponse(User user) {
        UserRankResponse myRank = buildDayMyRank(user);
        List<UserRankResponse> userRankList = new ArrayList<>();
        List<ExerciseAnalysisDto> usersDayRank =
                exerciseAnalysisCacheRepository.getUserIdsByRankTop100(user.getUserSchoolId());

        for (ExerciseAnalysisDto dayRank : usersDayRank) {
            userRankList.add(buildDayUsersRank(dayRank));
        }

        return UserRankListByMySchoolResponse.builder()
                .isJoinedClass(user.hasSection())
                .myRanking(myRank)
                .rankList(userRankList)
                .build();
    }

    private UserRankListByMySchoolResponse buildWeekOrMonthRankResponse(User user, Integer grade, Integer classNum,
                                                                        DateType dateType, LocalDate date) {

        UserRankResponse myRank = buildWeekOrMonthMyRank(user, grade, classNum, dateType, date);
        List<UserRankVO> usersWeekOrMonthRank =
                userRankRepository.getUserRankListBySchoolId(user.getUserSchoolId(), grade, classNum, dateType, date);
        List<UserRankResponse> userRankList =
                userRankFacade.buildWeekOrMonthUsersRankResponseWithIsMeasuring(usersWeekOrMonthRank);

        return UserRankListByMySchoolResponse.builder()
                .isJoinedClass(user.hasSection())
                .myRanking(myRank)
                .rankList(userRankList)
                .build();
    }

    private UserRankResponse buildDayMyRank(User user) {
        ExerciseAnalysisDto exerciseAnalysisDto =
                exerciseAnalysisCacheRepository.getUserTodayRank(user.getUserSchoolId(), user.getId());
        if (exerciseAnalysisDto == null) {
            exerciseAnalysisDto = ExerciseAnalysisDto.builder()
                    .ranking(0)
                    .walkCount(0)
                    .build();
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
                .isMeasuring(user.getIsMeasuring())
                .build();
    }

    private UserRankResponse buildWeekOrMonthMyRank(User user, Integer grade, Integer classNum, DateType dateType,
                                                    LocalDate date) {

        UserRankVO myRank = userRankRepository.getMyRankByUserId(user.getId(), grade, classNum, dateType, date);
        if (myRank == null) {
            return UserRankResponse.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .ranking(0)
                    .profileImageUrl(user.getProfileImageUrl())
                    .walkCount(0)
                    .isMeasuring(user.getIsMeasuring())
                    .build();
        }

        return UserRankResponse.builder()
                .userId(myRank.getUserId())
                .name(myRank.getName())
                .ranking(myRank.getRanking())
                .profileImageUrl(myRank.getProfileImageUrl())
                .walkCount(myRank.getWalkCount())
                .isMeasuring(userRankFacade.isMeasuringByUserId(user.getId()))
                .build();
    }

}
