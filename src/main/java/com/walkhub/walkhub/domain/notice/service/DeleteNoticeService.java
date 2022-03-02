package com.walkhub.walkhub.domain.notice.service;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.notice.domain.repository.NoticeRepository;
import com.walkhub.walkhub.domain.notice.exception.NoticeNotFoundException;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteNoticeService {

    private final UserFacade userFacade;
    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(Long noticeId) {
        User user = userFacade.getCurrentUser();

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        if (!notice.getUser().equals(user)) {
            throw InvalidRoleException.EXCEPTION;
        }

        noticeRepository.delete(notice);
    }
}
