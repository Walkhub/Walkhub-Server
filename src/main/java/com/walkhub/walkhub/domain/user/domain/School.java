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
    private String logoImageUrl;

    @Builder
    public School(String agencyCode, String name, String logoImageUrl) {
        this.agencyCode = agencyCode;
        this.name = name;
        this.logoImageUrl = logoImageUrl;
    }

    public void setLogoImage(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }
}
