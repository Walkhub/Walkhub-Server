package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.TeacherCodeRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.exception.InvalidVerificationCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ConfirmTeacherCodeService {

	private final UserFacade userFacade;

	@Transactional
	public void execute(TeacherCodeRequest request) {
		User user = userFacade.getCurrentUser();

		if (!user.getSchool().getAuthCode().equals(request.getCode())) {
			throw InvalidVerificationCodeException.EXCEPTION;
		}
		user.setAuthorityTeacher();
	}
}
