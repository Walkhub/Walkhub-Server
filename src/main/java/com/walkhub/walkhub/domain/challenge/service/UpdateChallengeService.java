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
        challengeFacade.getById(id);

        if (!Authority.TCHR.equals(user.getAuthority())) {
            throw InvalidRoleException.EXCEPTION;
        }

        return ChallengeResponse.builder()
                .name(request.getName())
                .imageUrl(request.getImageUrl())
                .content(request.getContent())
                .createAt(request.getCreatedAt())
                .endAt(request.getEndAt())
                .award(request.getAward())
                .scope(request.getScope())
                .goal(request.getGoal())
                .build();
    }

}
