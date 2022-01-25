package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Badge;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.user.exception.BadgeNotFoundException;
import com.walkhub.walkhub.domain.user.exception.BadgeUnauthorizedException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.QueryTitleBadgeRequest;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryMyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TitleBadgeSettingService {

    private final BadgeRepository badgeRepository;
    private final UserFacade userFacade;

    @Transactional
    public QueryMyPageResponse execute(Long badgeId) {
        User user = userFacade.getCurrentUser();
        Badge titleBadge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);


        if (!user.getBadge().equals(badgeId)) {
            throw BadgeUnauthorizedException.EXCEPTION;
        }
        return QueryMyPageResponse.builder()
                .titleBadge(QueryMyPageResponse.TitleBadge.builder()
                        .id(titleBadge.getId())
                        .name(titleBadge.getName())
                        .image(titleBadge.getImage())
                        .build())
                .build();
    }
}
