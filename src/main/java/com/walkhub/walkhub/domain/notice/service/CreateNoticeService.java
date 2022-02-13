package com.walkhub.walkhub.domain.notice.service;

import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.notice.domain.repository.NoticeRepository;
import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateNoticeService {

    private final UserFacade userFacade;
    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(CreateNoticeRequest request) {
        User user = userFacade.getCurrentUser();

        if (request.getScope().equals(Scope.SCHOOL) && !user.getAuthority().equals(Authority.ROOT) ||
                request.getScope().equals(Scope.ALL) && !user.getAuthority().equals(Authority.SU)) {
            throw InvalidRoleException.EXCEPTION;
        }

        noticeRepository.save(Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .scope(request.getScope())
                .user(user)
                .build());
    }
}
