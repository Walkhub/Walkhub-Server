package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForTeacherVO;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.QueryChallengeProgressRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse.UserChallengeProgressResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse.Writer;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeDetailsForTeacherService {

    private final ChallengeStatusRepository challengeStatusRepository;
    private final ChallengeFacade challengeFacade;

    public QueryChallengeDetailsForTeacherResponse execute(Long challengeId, QueryChallengeProgressRequest request) {
        Challenge challenge = challengeFacade.getChallengeById(challengeId);

        List<ChallengeDetailsForTeacherVO> challengeDetailsForTeacherVO = challengeStatusRepository.queryChallengeProgress(
                challenge,
                request.getParticipantsScope(),
                request.getParticipantsOrder(),
                request.getSuccessScope(),
                request.getPage()
        );

        List<UserChallengeProgressResponse> challengeProgressResponses =
                challengeDetailsForTeacherVO.stream()
                        .map(this::buildUserChallengeProgressResponse)
                        .collect(Collectors.toList());

        User challengeCreator = challenge.getUser();

        return QueryChallengeDetailsForTeacherResponse.builder()
                .participantList(challengeProgressResponses)
                .award(challenge.getAward())
                .classNum(challengeCreator.hasSection() ? challengeCreator.getSection().getClassNum() : null)
                .content(challenge.getContent())
                .participantCount((long) challenge.getChallengeStatuses().size())
                .endAt(challenge.getEndAt())
                .goal(challenge.getGoal())
                .goalScope(challenge.getGoalScope())
                .goalType(challenge.getGoalType())
                .grade(challengeCreator.hasSection() ? challengeCreator.getSection().getGrade() : null)
                .imageUrl(challenge.getImageUrl())
                .name(challenge.getName())
                .startAt(challenge.getStartAt())
                .successStandard(challenge.getSuccessStandard())
                .writer(Writer.builder()
                        .userId(challengeCreator.getId())
                        .name(challengeCreator.getName())
                        .profileImageUrl(challengeCreator.getProfileImageUrl())
                        .build())
                .name(challenge.getName())
                .build();
    }

    private UserChallengeProgressResponse buildUserChallengeProgressResponse(ChallengeDetailsForTeacherVO vo) {
        if (vo.getUserId() == null) {
            return null;
        }
        return UserChallengeProgressResponse.builder()
                .classNum(vo.getClassNum())
                .progress(vo.getProgress() == null ? 0 : vo.getProgress())
                .grade(vo.getGrade())
                .isSuccess(vo.getIsSuccess())
                .number(vo.getNumber())
                .profileImageUrl(vo.getProfileImageUrl())
                .schoolName(vo.getSchoolName())
                .successDate(vo.getSuccessDate())
                .totalWalkCount(vo.getTotalWalkCount())
                .userId(vo.getUserId())
                .name(vo.getUserName())
                .build();
    }
}
