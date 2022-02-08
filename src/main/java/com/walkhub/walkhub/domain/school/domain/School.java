package com.walkhub.walkhub.domain.school.domain;

import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class School extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @ColumnDefault(DefaultImage.SCHOOL_LOGO_IMAGE)
    private String logoImageUrl;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Long userCount;

    @Column(columnDefinition = "char(7)")
    private String authCode;

    @Column(columnDefinition = "char(7)", nullable = false)
    private String agencyCode;

    @Builder
    public School(String agencyCode, String name, String logoImageUrl) {
        this.agencyCode = agencyCode;
        this.name = name;
        this.logoImageUrl = logoImageUrl;
    }

    public void setLogoImage(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
