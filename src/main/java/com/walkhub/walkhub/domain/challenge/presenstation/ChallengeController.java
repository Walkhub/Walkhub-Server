package com.walkhub.walkhub.domain.challenge.presenstation;

import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.ChallengeParticipantRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.UpdateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.ChallengeResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForStudentResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeDetailsForTeacherResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListForStudentResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListForTeacherResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeParticipantListResponse;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryParticipateChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.service.CreateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.ParticipateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeDetailsForStudentService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeDetailsForTeacherService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeListForStudentService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeListForTeacherService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeParticipantListService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ChallengeResponse createChallenge(@RequestBody @Valid CreateChallengeRequest request) {
        return createChallengeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{challenge-id}")
    public ChallengeResponse updateChallenge(@PathVariable("challenge-id") Long id,
                                             @RequestBody @Valid UpdateChallengeRequest request) {
        return updateChallengeService.execute(id, request);
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

    @GetMapping("/web/lists")
    public QueryChallengeListForTeacherResponse queryChallengeListForTeacher(@RequestParam(required = false) Boolean isProgress) {
        return queryChallengeListForTeacherService.execute(isProgress);
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

}
