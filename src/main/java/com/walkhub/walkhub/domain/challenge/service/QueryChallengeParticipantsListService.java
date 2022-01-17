package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.ChallengeParticipantsElement;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.ChallengeParticipantsListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChallengeParticipantsListService {

    private final ChallengeStatusRepository challengeStatusRepository;

    @Transactional(readOnly = true)
    public ChallengeParticipantsListResponse execute(Long challengeId) {
        List<ChallengeParticipantsElement> result = challengeStatusRepository.findByChallengeId(challengeId)
                .stream()
                .map(challenge -> {
                    User participant = challenge.getUser();
                    return ChallengeParticipantsElement.builder()
                            .id(participant.getId())
                            .name(participant.getName())
                            // 학번 대신 추가
                            .profileImageUrl(participant.getProfileImageUrl())
                            .build();
                })
                .collect(Collectors.toList());

        return new ChallengeParticipantsListResponse(result);
    }

}
