package com.walkhub.walkhub.domain.badge.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class BadgeCollectionId implements Serializable {

    private Long badge;

    private Long user;
}
