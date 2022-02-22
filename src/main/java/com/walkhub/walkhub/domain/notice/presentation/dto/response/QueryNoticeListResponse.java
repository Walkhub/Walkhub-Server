package com.walkhub.walkhub.domain.notice.presentation.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

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

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
        private final ZonedDateTime createdAt;
        private final Writer writer;

        @QueryProjection
        public NoticeResponse(Long id, String title, String content, ZonedDateTime createdAt,
                              Writer writer) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.writer = writer;
        }
    }

    @Getter
    @Builder
    public static class Writer {
        private final Long id;
        private final String name;
        private final String profileImageUrl;

        @QueryProjection
        public Writer(Long id, String name, String profileImageUrl) {
            this.id = id;
            this.name = name;
            this.profileImageUrl = profileImageUrl;
        }
    }
}
