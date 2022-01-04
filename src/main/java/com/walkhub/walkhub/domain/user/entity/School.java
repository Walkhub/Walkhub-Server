package com.walkhub.walkhub.domain.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class School {

    @Id
    @Column(length = 7, nullable = false)
    private String agencyCode;

    @Column(length = 20, nullable = false)
    private String name;

    @Column
    private String logoImage;
}
