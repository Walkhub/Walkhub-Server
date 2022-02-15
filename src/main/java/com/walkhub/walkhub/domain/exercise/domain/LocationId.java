package com.walkhub.walkhub.domain.exercise.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class LocationId implements Serializable {

    private Integer sequence;

    private Long exercise;

}
