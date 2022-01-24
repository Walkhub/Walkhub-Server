package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.GroupId;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.GroupRepository;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.GroupNotFoundException;
import com.walkhub.walkhub.domain.user.exception.UserExistsException;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(accountId);
    }

    public User getUserByAccountId(String id) {
        return userRepository.findByAccountId(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void checkUserExists(String accountId) {
        if (userRepository.findByAccountId(accountId).isPresent()) {
            throw UserExistsException.EXCEPTION;
        }
    }

    public Group getGroupByGroupId(String agencyCode, Integer grade, Integer classNum) {
        return groupRepository.findById(new GroupId(grade, classNum, agencyCode))
                .orElseThrow(() -> GroupNotFoundException.EXCEPTION);
    }

}
