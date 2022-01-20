package com.walkhub.walkhub.domain.notice.presentation;

import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse;
import com.walkhub.walkhub.domain.notice.service.QueryNoticeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeController {

	private final QueryNoticeListService queryNoticeListService;

	@GetMapping("/list")
	public QueryNoticeListResponse queryNoticeList(@RequestParam Scope scope) {
		return queryNoticeListService.execute(scope);
	}

}
