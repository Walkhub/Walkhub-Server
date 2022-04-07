package com.walkhub.walkhub.domain.challenge.presenstation;

import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.ChallengeParticipantRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.UpdateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.*;
import com.walkhub.walkhub.domain.challenge.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/challenges")
@RestController
public class ChallengeController {

    private final CreateChallengeService createChallengeService;
    private final UpdateChallengeService updateChallengeService;
    private final RemoveChallengeService removeChallengeService;
    private final QueryChallengeListForStudentService queryChallengeListForStudentService;
    private final QueryChallengeDetailsForStudentService queryChallengeDetailsForStudentService;
    private final ParticipateChallengeService participateChallengeService;
    private final QueryParticipatedChallengeListService queryParticipatedChallengeListService;
    private final QueryChallengeDetailsForTeacherService queryChallengeDetailsForTeacherService;
    private final QueryChallengeParticipantListService queryChallengeParticipantListService;
    private final QueryChallengeListForTeacherService queryChallengeListForTeacherService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createChallenge(@RequestBody @Valid CreateChallengeRequest request) {
        createChallengeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{challenge-id}")
    public void updateChallenge(@PathVariable("challenge-id") Long id,
                                @RequestBody @Valid UpdateChallengeRequest request) {
        updateChallengeService.execute(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{challenge-id}")
    public void removeChallenge(@PathVariable("challenge-id") Long id) {
        removeChallengeService.execute(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{challenge-id}")
    public void participateChallenge(@PathVariable("challenge-id") Long challengeId) {
        participateChallengeService.execute(challengeId);
    }

    @GetMapping("/app/lists")
    public QueryChallengeListForStudentResponse queryChallengeListForStudent() {
        return queryChallengeListForStudentService.execute();
    }

    @GetMapping("/app/{challenge-id}")
    public QueryChallengeDetailsForStudentResponse queryChallengeDetailsForStudent(@PathVariable("challenge-id") Long challengeId) {
        return queryChallengeDetailsForStudentService.execute(challengeId);
    }

    @GetMapping("/web/{challenge-id}")
    public QueryChallengeDetailsForTeacherResponse queryChallengeDetailsForTeacher(@PathVariable("challenge-id") Long challengeId) {
        return queryChallengeDetailsForTeacherService.execute(challengeId);
    }

    @GetMapping("/participated")
    public QueryParticipateChallengeListResponse queryParticipatedChallengeList() {
        return queryParticipatedChallengeListService.execute();
    }

    @GetMapping("/{challenge-id}/progress")
    public QueryChallengeParticipantListResponse queryChallengeParticipantList(@PathVariable("challenge-id") Long id,
                                                                          @Valid ChallengeParticipantRequest request) {
        return queryChallengeParticipantListService.execute(id, request);
    }

    @GetMapping("/lists/teachers")
    public QueryChallengeListForTeacherResponse queryChallengeListForTeacher(@RequestParam LocalDate date) {
        return queryChallengeListForTeacherService.execute(date);
    }
}
