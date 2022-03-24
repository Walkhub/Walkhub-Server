package com.walkhub.walkhub.domain.notice.domain.repository;

import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse.NoticeResponse;
import com.walkhub.walkhub.domain.school.domain.School;

import java.util.List;

public interface NoticeRepositoryCustom {
    List<NoticeResponse> queryNoticeByScopeAndPage(Scope scope, Integer page, School userSchool);
}
