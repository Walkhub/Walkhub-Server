package com.walkhub.walkhub.domain.challenge.presentation;

import com.walkhub.walkhub.domain.challenge.presentation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presentation.dto.request.UpdateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presentation.dto.response.ChallengeParticipantsListResponse;
import com.walkhub.walkhub.domain.challenge.service.CreateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.ParticipateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeParticipantsListService;
import com.walkhub.walkhub.domain.challenge.service.UpdateChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/challenges")
@RestController
public class ChallengeController {

    private final CreateChallengeService createChallengeService;
    private final UpdateChallengeService updateChallengeService;
    private final ParticipateChallengeService participateChallengeService;
    private final QueryChallengeParticipantsListService queryChallengeParticipantsListService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createChallenge(@RequestBody @Valid CreateChallengeRequest request) {
        createChallengeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{challenge-id}")
    public void updateChallenge(@PathVariable("challenge-id") UpdateChallengeRequest request) {
        updateChallengeService.execute(request);
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

}
