package com.walkhub.walkhub.domain.notification.domain;

import com.walkhub.walkhub.domain.user.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Builder
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@EqualsAndHashCode
public class TopicListId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Boolean isSubscribe;

    public TopicListId(Topic topic, User user, Boolean isSubscribe) {
        this.topic = topic;
        this.user = user;
        this.isSubscribe = isSubscribe;
    }

    public void SubscribeTopic() {
        this.isSubscribe = true;
    }

    public void UnSubscribeTopic() {
        this.isSubscribe = false;
    }

}
