package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.UserExistsException;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

	private final UserRepository userRepository;

	public User getUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}

	public void userExists(String accountId) {
		if (userRepository.findByAccountId(accountId).isPresent()) {
			throw UserExistsException.EXCEPTION;
		}
	}

}
