package com.walkhub.walkhub.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public School(String agencyCode, String name, String logoImage) {
        this.agencyCode = agencyCode;
        this.name = name;
        this.logoImage = logoImage;
    }
}
