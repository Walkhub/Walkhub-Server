package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.facade.ChallengeFacade;
import com.walkhub.walkhub.domain.challenge.presentation.dto.request.UpdateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.ChallengeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateChallengeService {

    private final UserFacade userFacade;
    private final ChallengeFacade challengeFacade;

    @Transactional
    public ChallengeResponse execute(Long id, UpdateChallengeRequest request) {
        User user = userFacade.getCurrentUser();
        Challenge challenge = challengeFacade.getById(id);

        if (!Authority.TCHR.equals(user.getAuthority())) {
            throw InvalidRoleException.EXCEPTION;
        }

        return ChallengeResponse.builder()
                .name(challenge.getName())
                .imageUrl(challenge.getImageUrl())
                .content(challenge.getContent())
                .createAt(challenge.getCreateAt())
                .endAt(challenge.getEndAt())
                .award(challenge.getAward())
                .scope(challenge.getScope())
                .goal(challenge.getGoal())
                .build();
    }

}
