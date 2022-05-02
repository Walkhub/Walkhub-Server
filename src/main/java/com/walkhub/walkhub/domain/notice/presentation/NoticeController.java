package com.walkhub.walkhub.domain.notice.presentation;

import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.walkhub.walkhub.domain.notice.presentation.dto.request.ModifyNoticeRequest;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse;
import com.walkhub.walkhub.domain.notice.service.CreateNoticeService;
import com.walkhub.walkhub.domain.notice.service.DeleteNoticeService;
import com.walkhub.walkhub.domain.notice.service.ModifyNoticeService;
import com.walkhub.walkhub.domain.notice.service.QueryNoticeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeController {

    private final QueryNoticeListService queryNoticeListService;
    private final CreateNoticeService createNoticeService;
    private final DeleteNoticeService deleteNoticeService;
    private final ModifyNoticeService modifyNoticeService;

    @GetMapping("/list")
    public QueryNoticeListResponse queryNoticeList(@RequestParam Scope scope, @RequestParam(required = false) Integer page) {
        return queryNoticeListService.execute(scope, page);
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{notice-id}")
    public void modifyNotice(@PathVariable(name = "notice-id") Long noticeId, @RequestBody @Valid ModifyNoticeRequest request) {
        modifyNoticeService.execute(noticeId, request);
    }
}
