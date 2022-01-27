package com.walkhub.walkhub.domain.notice.service;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.notice.domain.repository.NoticeRepository;
import com.walkhub.walkhub.domain.notice.exception.NoticeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteNoticeService {

    private final NoticeRepository noticeRepository;

    public void execute(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        noticeRepository.delete(notice);
    }
}
