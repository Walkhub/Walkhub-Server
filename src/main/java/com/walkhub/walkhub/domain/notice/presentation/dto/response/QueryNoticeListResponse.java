package com.walkhub.walkhub.domain.notice.presentation.dto.response;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class QueryNoticeListResponse {

	public final List<NoticeResponse> noticeList;

	@Getter
	@Builder
	public static class NoticeResponse {
		private final Long id;
		private final String title;
		private final String content;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:SS")
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
