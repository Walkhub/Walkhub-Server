package com.walkhub.walkhub.infrastructure.fcm.dto.request;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;
import com.walkhub.walkhub.domain.notice.domain.Notice;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.enums.UserScope;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationInformation {

    private final User user;

    private final String title;

    private final UserScope scope;

    private final String data;

    public static NotificationInformation challengeNotificationInformation(Challenge challenge) {
        return new NotificationInformation(challenge.getUser(), challenge.getName(),
                UserScope.valueOf(challenge.getUserScope().toString()), challenge.getId().toString());
    }

    public static NotificationInformation noticeNotificationInformation(Notice notice) {
        return new NotificationInformation(notice.getUser(), notice.getTitle(),
                UserScope.valueOf(notice.getScope().toString()), notice.getId().toString());
    }


}
