package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.repository.GroupRepository;
import com.walkhub.walkhub.domain.user.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GroupFacade {

    private final GroupRepository groupRepository;

    public Group getGroup(Long id) {
       return groupRepository.findById(id)
                .orElseThrow(() -> GroupNotFoundException.EXCEPTION);
    }
}
