package com.walkhub.walkhub.domain.challenge.presentation;

import com.walkhub.walkhub.domain.challenge.presentation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.QueryChallengeDetailResponse;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.ChallengeParticipantsListResponse;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/challenges")
@RestController
public class ChallengeController {

    private final CreateChallengeService createChallengeService;
    private final QueryChallengeListService queryChallengeListService;
    private final QueryChallengeDetailService queryChallengeDetailService;
    private final ParticipateChallengeService participateChallengeService;
    private final QueryChallengeParticipantsListService queryChallengeParticipantsListService;
    private final RemoveChallengeService removeChallengeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createChallenge(@RequestBody @Valid CreateChallengeRequest request) {
        createChallengeService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{challenge-id}")
    public void participateChallenge(@PathVariable("challenge-id") Long id) {
        participateChallengeService.execute(id);
    }

    @GetMapping("/{challenge-id}/participants")
    public ChallengeParticipantsListResponse queryChallengeParticipantsList(@PathVariable("challenge-id") Long id) {
        return queryChallengeParticipantsListService.execute(id);
    }

    @GetMapping("/lists")
    public QueryChallengeListResponse queryChallengeList() {
        return queryChallengeListService.execute();
    }

    @GetMapping("/{challenge-id}")
    public QueryChallengeDetailResponse queryChallengeDetail(@PathVariable("challenge-id") Long id) {
        return queryChallengeDetailService.execute(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{challenge-id}")
    public void reomoveChallenge(@PathVariable("challenge-id") Long id) {
        removeChallengeService.execute(id);
    }
}
