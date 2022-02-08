package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.GroupRepository;
import com.walkhub.walkhub.domain.user.facade.GroupFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteClassService {

    private final UserFacade userFacade;
    private final GroupFacade groupFacade;
    private final GroupRepository groupRepository;

    @Transactional
    public void execute(Long groupId) {
        Group group = groupFacade.getGroup(groupId);

        User user = userFacade.getCurrentUser();

        if (user.getAuthority() == Authority.TEACHER && !user.getGroup().equals(group)) {
            throw InvalidRoleException.EXCEPTION;
        }

        groupRepository.delete(group);
    }

}
