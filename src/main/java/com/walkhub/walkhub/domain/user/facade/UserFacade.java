package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.UserExistsException;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public Optional<User> getUserByAccountId(String id) {
        return userRepository.findByAccountId(id);
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

}
