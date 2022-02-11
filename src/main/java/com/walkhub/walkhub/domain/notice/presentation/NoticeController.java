package com.walkhub.walkhub.domain.notice.presentation;

import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse;
import com.walkhub.walkhub.domain.notice.service.CreateNoticeService;
import com.walkhub.walkhub.domain.notice.service.DeleteNoticeService;
import com.walkhub.walkhub.domain.notice.service.QueryNoticeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeController {

    private final QueryNoticeListService queryNoticeListService;
    private final CreateNoticeService createNoticeService;
    private final DeleteNoticeService deleteNoticeService;

    @GetMapping("/list")
    public QueryNoticeListResponse queryNoticeList(@RequestParam Scope scope) {
        return queryNoticeListService.execute(scope);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNotice(@RequestBody @Valid CreateNoticeRequest request) {
        createNoticeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{notice-id}")
    public void deleteNotice(@PathVariable(name = "notice-id") Long noticeId) {
        deleteNoticeService.execute(noticeId);
    }

}
