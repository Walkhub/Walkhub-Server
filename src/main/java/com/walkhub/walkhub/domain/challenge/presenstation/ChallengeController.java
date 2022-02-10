package com.walkhub.walkhub.domain.challenge.presenstation;

import com.walkhub.walkhub.domain.challenge.presenstation.dto.request.CreateChallengeRequest;
import com.walkhub.walkhub.domain.challenge.presenstation.dto.response.QueryChallengeListResponse;
import com.walkhub.walkhub.domain.challenge.service.CreateChallengeService;
import com.walkhub.walkhub.domain.challenge.service.QueryChallengeListService;
import com.walkhub.walkhub.domain.challenge.service.RemoveChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{challenge-id}")
    public void removeChallenge(@PathVariable(name = "challenge-id") Long id) {
        removeChallengeService.execute(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createChallenge(@RequestBody @Valid CreateChallengeRequest request) {
        createChallengeService.execute(request);
    }

    @GetMapping("lists")
    public QueryChallengeListResponse queryChallengeList() {
        return queryChallengeListService.execute();
    }
}
