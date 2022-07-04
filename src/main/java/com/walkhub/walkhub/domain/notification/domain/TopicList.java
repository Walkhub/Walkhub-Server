package com.walkhub.walkhub.domain.notification.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TopicList {

    @EmbeddedId
    private TopicListId id;

}
