package com.walkhub.walkhub.domain.notice.service;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.notice.domain.repository.NoticeRepository;
import com.walkhub.walkhub.domain.notice.exception.NoticeNotFoundException;
import com.walkhub.walkhub.domain.notice.presentation.dto.request.ModifyNoticeRequest;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ModifyNoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(Long noticeId, ModifyNoticeRequest request) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
        notice.updateNotice(request.getTitle(), request.getContent());
    }
}
