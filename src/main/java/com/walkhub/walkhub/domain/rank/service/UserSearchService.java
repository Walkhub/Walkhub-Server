package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisCacheRepositoryImpl;
import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisDto;
import com.walkhub.walkhub.domain.rank.domain.UserRank;
import com.walkhub.walkhub.domain.rank.domain.repository.UserRankRepository;
import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.global.enums.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserSearchService {

    private final UserRankRepository userRankRepository;
    private final ExerciseAnalysisCacheRepositoryImpl exerciseAnalysisCacheRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserListResponse execute(Long schoolId, String name, DateType dateType) {
        List<UserListResponse.UserSearchResponse> result;

        if (DateType.DAY.equals(dateType)) {
            result = userRepository.findAllBySchoolIdAndNameContaining(schoolId, name)
                    .stream().map(this::buildDayUserSearchResponse)
                    .collect(Collectors.toList());
        } else {
            result = userRankRepository.findAllBySchoolIdAndNameContainingAndDateType(schoolId, name, dateType.toString())
                    .stream().map(this::buildUserSearchResponse)
                    .collect(Collectors.toList());
        }

        return new UserListResponse(result);
    }

    private UserListResponse.UserSearchResponse buildDayUserSearchResponse(User user) {
        ExerciseAnalysisDto exerciseAnalysisDto = exerciseAnalysisCacheRepository
                .getUserTodayRank(user.getId());

        return UserListResponse.UserSearchResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .rank(exerciseAnalysisDto.getRanking())
                .grade(user.getSection().getGrade())
                .classNum(user.getSection().getClassNum())
                .profileImageUrl(user.getProfileImageUrl())
                .walkCount(exerciseAnalysisDto.getWalkCount())
                .build();
    }

    private UserListResponse.UserSearchResponse buildUserSearchResponse(UserRank userRank) {
        return UserListResponse.UserSearchResponse.builder()
                .userId(userRank.getUserId())
                .name(userRank.getName())
                .rank(userRank.getRanking())
                .grade(userRank.getGrade())
                .classNum(userRank.getClassNum())
                .profileImageUrl(userRank.getProfileImageUrl())
                .walkCount(userRank.getWalkCount())
                .build();
    }
}
