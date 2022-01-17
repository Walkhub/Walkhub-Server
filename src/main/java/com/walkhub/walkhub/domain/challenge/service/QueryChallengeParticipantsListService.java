package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeStatusRepository;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.ChallengeParticipantsElement;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.ChallengeParticipantsListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChallengeParticipantsListService {

    private final UserFacade userFacade;

    private final ChallengeStatusRepository challengeStatusRepository;

    @Transactional(readOnly = true)
    public ChallengeParticipantsListResponse execute(Long challengeId) {
        User user = userFacade.getCurrentUser();

        List<ChallengeParticipantsElement> result = challengeStatusRepository.findByChallengeId(challengeId)
                .stream()
                .filter(authority -> !user.getAuthority().equals(Authority.USER))
                .map(challenge -> {
                    User participant = challenge.getUser();
                    return ChallengeParticipantsElement.builder()
                            .id(participant.getId())
                            .name(participant.getName())
                            .gcn(participant.)
                            .profileImageUrl(participant.getProfileImageUrl())
                            .build();
                })
                .collect(Collectors.toList());

        return new ChallengeParticipantsListResponse(result);
    }

}
