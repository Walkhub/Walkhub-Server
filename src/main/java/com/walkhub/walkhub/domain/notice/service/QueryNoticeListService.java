package com.walkhub.walkhub.domain.notice.service;

import com.walkhub.walkhub.domain.notice.domain.repository.NoticeRepository;
import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse;
import com.walkhub.walkhub.domain.notice.presentation.dto.response.QueryNoticeListResponse.NoticeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
public class QueryNoticeListService {

    private final NoticeRepository noticeRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryNoticeListResponse execute(Scope scope, Integer page) {
        User user = userFacade.getCurrentUser();

        List<NoticeResponse> noticeResponseList = noticeRepository
            .queryNoticeByScopeAndPage(scope, page, user.getSchool());

        return new QueryNoticeListResponse(noticeResponseList);
    }
}
