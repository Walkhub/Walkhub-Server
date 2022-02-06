package com.walkhub.walkhub.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GroupId implements Serializable {

    @Column(columnDefinition = "TINYINT")
    private Integer grade;

    @Column(columnDefinition = "TINYINT")
    private Integer classNum;

    private String school;
}
