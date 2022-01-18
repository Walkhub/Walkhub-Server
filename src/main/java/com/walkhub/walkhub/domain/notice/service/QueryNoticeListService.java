package com.walkhub.walkhub.domain.notice.service;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.notice.domain.repository.NoticeRepository;
import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse.NoticeResponse;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse.Writer;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryNoticeListService {

	private final NoticeRepository noticeRepository;
	private final UserFacade userFacade;

	@Transactional(readOnly=true)
	public QueryNoticeListResponse execute() {

		User user = userFacade.getCurrentUser();

		List<NoticeResponse> noticeResponseList = noticeRepository.findAllBy(user.getGroup().getSchool(), user.getAuthority())
			.stream()
			.map(this::noticeResponseBuilder)
			.collect(Collectors.toList());

		return new QueryNoticeListResponse(noticeResponseList);
	}

	private NoticeResponse noticeResponseBuilder(Notice notice) {
		return NoticeResponse.builder()
			.id(notice.getId())
			.title(notice.getTitle())
			.content(notice.getContent())
			.createdAt(notice.getCreatedAt())
			.writer(Writer.builder()
				.id(notice.getUser().getId())
				.name(notice.getUser().getName())
				.profileImageUrl(notice.getUser().getProfileImageUrl())
				.build())
			.build();
	}

}
