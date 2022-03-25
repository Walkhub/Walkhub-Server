package com.walkhub.walkhub.domain.challenge.presenstation;

import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.QueryChallengeProgressRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.UpdateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForStudentResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryParticipateChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.service.CreateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.ParticipateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeDetailsForStudentService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeListService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeDetailsForTeacherService;
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
    private final UpdateChallengeService updateChallengeService;
    private final QueryChallengeDetailsForStudentService queryChallengeDetailsForStudentService;
    private final ParticipateChallengeService participateChallengeService;
    private final QueryParticipatedChallengeListService queryParticipatedChallengeListService;
    private final QueryChallengeDetailsForTeacherService challengeProgressService;

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{challenge-id}")
    public void updateChallenge(@PathVariable("challenge-id") Long id,
                                @RequestBody @Valid UpdateChallengeRequest request) {
        updateChallengeService.execute(id, request);
    }

    @GetMapping("/{challenge-id}")
    public QueryChallengeDetailsForStudentResponse queryChallengeDetails(@PathVariable("challenge-id") Long challengeId) {
        return queryChallengeDetailsForStudentService.execute(challengeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{challenge-id}")
    public void participateChallenge(@PathVariable("challenge-id") Long challengeId) {
        participateChallengeService.execute(challengeId);
    }

    @GetMapping("/participated")
    public QueryParticipateChallengeListResponse queryParticipatedChallengeList() {
        return queryParticipatedChallengeListService.execute();
    }

    @GetMapping("/{challenge-id}/progress")
    public QueryChallengeDetailsForTeacherResponse queryChallengeProgress(@PathVariable("challenge-id") Long challengeId,
                                                                          QueryChallengeProgressRequest request) {
        return challengeProgressService.execute(challengeId, request);
    }
}
