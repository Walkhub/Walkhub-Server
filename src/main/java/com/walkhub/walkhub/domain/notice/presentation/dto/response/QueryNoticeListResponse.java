package com.walkhub.walkhub.domain.notice.presentation.dto.response;


import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryNoticeListResponse {

	public List<NoticeResponse> noticeList;

	@Getter
	@Builder
	public static class NoticeResponse {
		private final Long id;
		private final String title;
		private final String content;
		private final LocalDateTime createdAt;
		private final Writer writer;
	}

	@Getter
	@Builder
	public static class Writer {
		private final Long id;
		private final String name;
		private final String profileImageUrl;
	}
}
