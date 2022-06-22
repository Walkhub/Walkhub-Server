package com.walkhub.walkhub.domain.notification.domain.repository;

import com.walkhub.walkhub.domain.notification.domain.TopicList;
import com.walkhub.walkhub.domain.notification.domain.TopicListId;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicListRepository extends CrudRepository<TopicList, TopicListId> {

    List<TopicList> findAllByIdUser(User user);

}
