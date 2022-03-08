package com.walkhub.walkhub.domain.challenge.service;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class CreateChallengeService {

    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;

    @Transactional
    public void execute(CreateChallengeRequest request) {
        User user = userFacade.getCurrentUser();
        UserScope userScope = user.getAuthority() == Authority.SU ? UserScope.ALL : request.getUserScope();

        challengeRepository.save(Challenge.builder()
                .user(user)
                .name(request.getName())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .award(request.getAward())
                .userScope(userScope)
                .goal(request.getGoal())
                .goalType(request.getGoalType())
                .goalScope(request.getGoalScope())
                .successStandard(request.getSuccessStandard())
                .build());
    }
}
