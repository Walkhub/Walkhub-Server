package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.presentation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateChallengeService {

    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;

    public void execute(CreateChallengeRequest request) {

        User user = userFacade.getCurrentUser();

        challengeRepository.save(Challenge.builder()
                .name(request.getName())
                .content(request.getContent())
                .createAt(request.getCreatedAt())
                .endAt(request.getEndAt())
                .goal(request.getGoal())
                .award(request.getAward())
                .scope(request.getScope())
                .user(user)
                .build());
    }

}
