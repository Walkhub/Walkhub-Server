package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisCacheRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.vo.UserRankVO;
import com.walkhub.walkhub.domain.rank.domain.type.UserRankScope;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryUserRankListService {
    private final UserRankRepository userRankRepository;
    private final ExerciseAnalysisCacheRepository exerciseAnalysisCacheRepository;
    private final UserFacade userFacade;
    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    public UserRankListResponse execute(UserRankScope scope, DateType dateType) {
        User user = userFacade.getCurrentUser();
        Section section = user.getSection();
        LocalDate date = LocalDate.now();
        if (dateType.equals(DateType.DAY)) {
            AtomicInteger rankCount = new AtomicInteger();
            return UserRankListResponse.builder()
                    .myRank(UserRankListResponse.UserRankResponse.builder()
                            .userId(user.getId())
                            .name(user.getName())
                            .grade(section.getGrade())
                            .classNum(section.getClassNum())
                            .ranking(Math.toIntExact(exerciseAnalysisCacheRepository.getUserTodayRank(user.getId()).getRanking()))
                            .profileImageUrl(user.getProfileImageUrl())
                            .walkCount(exerciseAnalysisRepository.findByUserIdAndDate(user.getId(), LocalDate.now()))
                            .build()
                    )
                    .rankList(exerciseAnalysisCacheRepository.getUserIdsByRankTop100()
                            .stream()
                            .map(userIds -> userFacade.getUserById(userIds.getUserId()))
                            .map(users -> UserRankListResponse.UserRankResponse.builder()
                                    .userId(users.getId())
                                    .name(users.getName())
                                    .grade(users.getSection().getGrade())
                                    .classNum(users.getSection().getClassNum())
                                    .ranking(rankCount.incrementAndGet())
                                    .profileImageUrl(users.getProfileImageUrl())
                                    .walkCount(exerciseAnalysisRepository.findByUserIdAndDate(users.getId(), LocalDate.now()))
                                    .build()
                            ).collect(Collectors.toList())
                    ).build();
        } else if (scope.equals(UserRankScope.ALL)) {
            UserRankVO userRankVO = userRankRepository.getMyRankByUserId(user.getId(), null, dateType, date);
            return UserRankListResponse.builder()
                    .myRank(UserRankListResponse.UserRankResponse.builder()
                            .userId(userRankVO.getUserId())
                            .name(userRankVO.getName())
                            .grade(userRankVO.getGrade())
                            .classNum(userRankVO.getClassNum())
                            .ranking(userRankVO.getRanking())
                            .profileImageUrl(userRankVO.getProfileImageUrl())
                            .walkCount(userRankVO.getWalkCount())
                            .build()
                    )
                    .rankList(userRankRepository.getUserRankListBySchoolId(user.getSchool().getId(), null, dateType, date)
                            .stream().map(vo -> UserRankListResponse.UserRankResponse.builder()
                                    .userId(vo.getUserId())
                                    .name(vo.getName())
                                    .grade(vo.getGrade())
                                    .classNum(vo.getClassNum())
                                    .ranking(vo.getRanking())
                                    .profileImageUrl(vo.getProfileImageUrl())
                                    .walkCount(vo.getWalkCount())
                                    .build()
                            ).collect(Collectors.toList())
                    )
                    .build();
        } else if (scope.equals(UserRankScope.CLASS)) {
            UserRankVO userRankVO = userRankRepository.getMyRankByUserId(user.getId(), user.getSection().getClassNum(), dateType, date);
            return UserRankListResponse.builder()
                    .myRank(UserRankListResponse.UserRankResponse.builder()
                            .userId(userRankVO.getUserId())
                            .name(userRankVO.getName())
                            .grade(userRankVO.getGrade())
                            .classNum(userRankVO.getClassNum())
                            .ranking(userRankVO.getRanking())
                            .profileImageUrl(userRankVO.getProfileImageUrl())
                            .walkCount(userRankVO.getWalkCount())
                            .build())
                    .rankList(userRankRepository.getUserRankListBySchoolId(user.getSchool().getId(), user.getSection().getClassNum(), dateType, date)
                            .stream().map(vo -> UserRankListResponse.UserRankResponse.builder()
                                    .userId(vo.getUserId())
                                    .name(vo.getName())
                                    .grade(vo.getGrade())
                                    .classNum(vo.getClassNum())
                                    .ranking(vo.getRanking())
                                    .profileImageUrl(vo.getProfileImageUrl())
                                    .walkCount(vo.getWalkCount())
                                    .build()
                            ).collect(Collectors.toList())
                    )
                    .build();
        }
        return null;
    }
}
