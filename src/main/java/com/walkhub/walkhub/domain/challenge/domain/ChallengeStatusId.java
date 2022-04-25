package com.walkhub.walkhub.domain.challenge.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ChallengeStatusId implements Serializable {

    private Long challenge;

    private Long user;
}
