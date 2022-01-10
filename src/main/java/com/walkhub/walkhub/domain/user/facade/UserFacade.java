package com.walkhub.walkhub.domain.user.facade;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.exception.CredentialsNotFoundException;
import com.walkhub.walkhub.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

	private final UserRepository userRepository;
  
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof AuthDetails)) {
			throw CredentialsNotFoundException.EXCEPTION;
		}
		return ((AuthDetails) principal).getUser();
  }

	public User getUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}
}
