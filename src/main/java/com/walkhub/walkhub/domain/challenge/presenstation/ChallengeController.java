package com.walkhub.walkhub.domain.challenge.presenstation;

import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeListService;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsResponse;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeDetailsService;
import com.walkhub.walkhub.domain.challenge.service.RemoveChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/challenges")
@RestController
public class ChallengeController {

    private final RemoveChallengeService removeChallengeService;
    private final QueryChallengeListService queryChallengeListService;
    private final QueryChallengeDetailsService queryChallengeDetailsService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{challenge-id}")
    public void removeChallenge(@PathVariable(name = "challenge-id") Long id) {
        removeChallengeService.execute(id);
    }

    @GetMapping("/lists")
    public QueryChallengeListResponse queryChallengeList() {
        return queryChallengeListService.execute();
    }

    @GetMapping("/{challenge-id}")
    public QueryChallengeDetailsResponse queryChallengeDetails(@PathVariable("challenge-id") Long challengeId) {
        return queryChallengeDetailsService.execute(challengeId);
    }

}
