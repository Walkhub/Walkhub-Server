package com.walkhub.walkhub.domain.challenge.presenstation;

import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.QueryChallengeProgressRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.UpdateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantsForStudentResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeProgressResponse;
import com.walkhub.walkhub.domain.challenge.service.CreateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.ParticipateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeDetailsService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeListService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeParticipantsForStudentService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeProgressService;
import com.walkhub.walkhub.domain.challenge.service.QueryClosesChallengeService;
import com.walkhub.walkhub.domain.challenge.service.QueryParticipatedChallengeListService;
import com.walkhub.walkhub.domain.challenge.service.RemoveChallengeService;
import com.walkhub.walkhub.domain.challenge.service.UpdateChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/challenges")
@RestController
public class ChallengeController {

    private final RemoveChallengeService removeChallengeService;
    private final CreateChallengeService createChallengeService;
    private final QueryChallengeListService queryChallengeListService;
    private final QueryClosesChallengeService queryClosesChallengeService;
    private final UpdateChallengeService updateChallengeService;
    private final QueryChallengeDetailsService queryChallengeDetailsService;
    private final ParticipateChallengeService participateChallengeService;
    private final QueryParticipatedChallengeListService queryParticipatedChallengeListService;
    private final QueryChallengeParticipantsForStudentService queryChallengeParticipantsForStudentService;
    private final QueryChallengeProgressService challengeProgressService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{challenge-id}")
    public void removeChallenge(@PathVariable("challenge-id") Long id) {
        removeChallengeService.execute(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createChallenge(@RequestBody @Valid CreateChallengeRequest request) {
        createChallengeService.execute(request);
    }

    @GetMapping("/lists/students")
    public QueryChallengeListResponse queryChallengeList() {
        return queryChallengeListService.execute();
    }

    @GetMapping("/lists/closed")
    public QueryChallengeListResponse queryClosedChallengeList() {
        return queryClosesChallengeService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{challenge-id}")
    public void updateChallenge(@PathVariable("challenge-id") Long id,
                                @RequestBody @Valid UpdateChallengeRequest request) {
        updateChallengeService.execute(id, request);
    }

    @GetMapping("/{challenge-id}")
    public QueryChallengeDetailsResponse queryChallengeDetails(@PathVariable("challenge-id") Long challengeId) {
        return queryChallengeDetailsService.execute(challengeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{challenge-id}")
    public void participateChallenge(@PathVariable("challenge-id") Long challengeId) {
        participateChallengeService.execute(challengeId);
    }

    @GetMapping("/participated")
    public QueryChallengeListResponse queryParticipatedChallengeList() {
        return queryParticipatedChallengeListService.execute();
    }

    @GetMapping("/{challenge-id}/participants/students")
    public QueryChallengeParticipantsForStudentResponse queryChallengeParticipantsForStudent(@PathVariable("challenge-id") Long challengeId) {
        return queryChallengeParticipantsForStudentService.execute(challengeId);
    }

    @GetMapping("/{challenge-id}/progress")
    public QueryChallengeProgressResponse queryChallengeProgress(@PathVariable("challenge-id") Long challengeId,
                                                                 QueryChallengeProgressRequest request) {
        return challengeProgressService.execute(challengeId, request);
    }
}
