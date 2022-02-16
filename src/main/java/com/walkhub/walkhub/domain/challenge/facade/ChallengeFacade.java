package com.walkhub.walkhub.domain.challenge.facade;


import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.challenge.domain.repository.ChallengeRepository;
import com.walkhub.walkhub.domain.challenge.domain.repository.vo.ShowChallengeVO;
import com.walkhub.walkhub.domain.challenge.exception.ChallengeNotFoundException;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.ChallengeResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse.Writer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengeFacade {

    private final ChallengeRepository challengeRepository;

    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);
    }

    public ChallengeResponse challengeResponseBuilder(ShowChallengeVO vo) {
        return ChallengeResponse.builder()
                .challengeId(vo.getChallengeId())
                .name(vo.getName())
                .startAt(vo.getStartAt())
                .endAt(vo.getEndAt())
                .imageUrl(vo.getImageUrl())
                .userScope(vo.getUserScope())
                .goalScope(vo.getGoalScope())
                .goalType(vo.getGoalType())
                .writer(Writer.builder()
                        .userId(vo.getChallengeId())
                        .name(vo.getWriterName())
                        .profileImageUrl(vo.getProfileImageUrl())
                        .build())
                .build();
    }
}
