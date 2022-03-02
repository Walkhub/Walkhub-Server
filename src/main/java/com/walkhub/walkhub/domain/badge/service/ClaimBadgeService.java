package com.walkhub.walkhub.domain.badge.service;

import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.ClaimBadgeResponse;
import com.walkhub.walkhub.domain.badge.presentation.dto.response.ClaimBadgeResponse.BadgeResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClaimBadgeService {

    private final UserFacade userFacade;
    private final BadgeRepository badgeRepository;

    public ClaimBadgeResponse execute() {
        User user = userFacade.getCurrentUser();
        List<BadgeResponse> badgeResponses = new ArrayList<>();
        List<DefaultBadgeVO> claimBadgeList = badgeRepository.findAllByBadgeCollectionsNotIn(user.getId());

        for (DefaultBadgeVO vo : claimBadgeList) {
            // todo 뱃지가 추가되면 그때마다 if 처리를 하겠습니다.
            badgeResponses.add(buildBadgeResponse(vo));
        }

        return new ClaimBadgeResponse(badgeResponses);
    }

    private BadgeResponse buildBadgeResponse(DefaultBadgeVO vo) {
        return BadgeResponse.builder()
                .badgeId(vo.getBadgeId())
                .name(vo.getName())
                .imageUrl(vo.getImageUrl())
                .build();
    }
}
