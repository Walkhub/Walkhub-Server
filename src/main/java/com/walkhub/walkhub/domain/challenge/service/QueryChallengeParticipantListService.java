package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ChallengeDetailsForTeacherVO;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.ChallengeParticipantRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantListResponse.QueryChallengeParticipantResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryChallengeParticipantListService {

    private final ChallengeFacade challengeFacade;
    private final ChallengeStatusRepository challengeStatusRepository;

    public QueryChallengeParticipantListResponse execute(Long id, ChallengeParticipantRequest request) {

        Challenge challenge = challengeFacade.getChallengeById(id);
        List<ChallengeDetailsForTeacherVO> challengeParticipantList;

        long participantCount = 0;
        int totalPage = 0;

        if (request.getPage() == null) {
            challengeParticipantList = challengeStatusRepository.queryChallengeProgress(challenge, request.getName(),
                    request.getUserScope(), request.getSort(), request.getGrade(),
                    request.getClassNum());
        } else {
            Page<ChallengeDetailsForTeacherVO> pageChallengeParticipantList =
                    challengeStatusRepository.queryChallengeProgress(challenge, request.getName(),
                            request.getUserScope(), request.getSort(), request.getGrade(),
                            request.getClassNum(), request.getPage());

            challengeParticipantList = pageChallengeParticipantList.getContent();
            totalPage = pageChallengeParticipantList.getTotalPages();
            participantCount = pageChallengeParticipantList.getTotalElements();
        }

        List<QueryChallengeParticipantResponse> responseList = challengeParticipantList.stream()
                .map(this::builderChallengeParticipantResponse)
                .collect(Collectors.toList());

        return new QueryChallengeParticipantListResponse(totalPage, participantCount, responseList);
    }

    private QueryChallengeParticipantResponse builderChallengeParticipantResponse(ChallengeDetailsForTeacherVO vo) {
        return QueryChallengeParticipantResponse.builder()
                .userId(vo.getUserId())
                .name(vo.getUserName())
                .grade(vo.getGrade())
                .classNum(vo.getClassNum())
                .number(vo.getNumber())
                .schoolName(vo.getSchoolName())
                .profileImageUrl(vo.getProfileImageUrl())
                .totalWalkCount(vo.getTotalValue() == null ? 0 : vo.getTotalValue())
                .progress(vo.getProgress() == null ? 0 : vo.getProgress())
                .isSuccess(vo.getIsSuccess())
                .successDate(vo.getSuccessDate())
                .build();
    }
}
