package com.walkhub.walkhub.domain.school.domain;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import com.walkhub.walkhub.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Cacheable
public class School extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(unique = true)
    private String name;

    @ColumnDefault(DefaultImage.SCHOOL_LOGO_IMAGE)
    @Column(nullable = false)
    private String logoImageUrl;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Long userCount;

    @Column(columnDefinition = "char(7)", unique = true)
    private String authCode;

    @OneToMany(mappedBy = "school", cascade = CascadeType.REMOVE)
    private List<Section> sections;

    @Builder
    public School(String name, String logoImageUrl) {
        this.name = name;
        this.logoImageUrl = logoImageUrl;
    }

    public void addUserCount() {
        this.userCount++;
    }

    public void minusUserCount() {
        this.userCount--;
    }

    public void setLogoImage(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
