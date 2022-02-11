package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisCacheRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserRankListResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
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

    public UserRankListResponse execute(String scope, String dateType) {
        User user = userFacade.getCurrentUser();
        Section section = user.getSection();
        LocalDate date = LocalDate.now();
        if (dateType.equals("DAY")) {
            AtomicInteger rankCount = new AtomicInteger();
            return UserRankListResponse.builder()
                    .myRank(UserRankListResponse.UserRankResponse.builder()
                            .userId(user.getId())
                            .name(user.getName())
                            .grade(section.getGrade())
                            .classNum(section.getClassNum())
                            .ranking(Math.toIntExact(exerciseAnalysisCacheRepository.getUserTodayRank(user.getId())))
                            .profileImageUrl(user.getProfileImageUrl())
                            .walkCount(exerciseAnalysisRepository.findWalkCountByUserId(user.getId()))
                            .build()
                    )
                    .rankList(exerciseAnalysisCacheRepository.getUserIdsByRankTop100()
                            .stream()
                            .map(userFacade::getUserById)
                            .map(users -> UserRankListResponse.UserRankResponse.builder()
                                    .userId(users.getId())
                                    .name(users.getName())
                                    .grade(users.getSection().getGrade())
                                    .classNum(users.getSection().getClassNum())
                                    .ranking(rankCount.incrementAndGet())
                                    .profileImageUrl(users.getProfileImageUrl())
                                    .walkCount(exerciseAnalysisRepository.findWalkCountByUserId(users.getId()))
                                    .build()
                            ).collect(Collectors.toList())
                    ).build();
        } else {
            if (scope.equals("ALL")) {
                return UserRankListResponse.builder()
                        .myRank(userRankRepository.getMyRankByUserId(user.getId(), null, dateType, date))
                        .rankList(userRankRepository.getUserRankListBySchoolId(user.getSchool().getId(), null, dateType, date))
                        .build();
            } else if (scope.equals("CLASS")) {
                return UserRankListResponse.builder()
                        .myRank(userRankRepository.getMyRankByUserId(user.getId(), user.getSection().getClassNum(), dateType, date))
                        .rankList(userRankRepository.getUserRankListBySchoolId(user.getSchool().getId(), user.getSection().getClassNum(), dateType, date))
                        .build();
            }
        }
        return null;
    }
}
