package com.walkhub.walkhub.infrastructure.fcm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentType {

    EXPIRED_CHALLENGE("챌린지가 기간 만료되었습니다."),
    SUCCESS_CHALLENGE("챌린지 성공 하였습니다."),
    CAN_PARTICIPATE_CHALLENGE("챌린지를 참여할수 있습니다."),

    CREATE_NOTICE("공지사항이 등록되었습니다.");

    private final String content;
}
