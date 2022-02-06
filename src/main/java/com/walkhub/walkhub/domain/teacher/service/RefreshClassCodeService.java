package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassCodeResponse;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.utils.code.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RefreshClassCodeService {

	private final UserFacade userFacade;

	@Transactional
	public ClassCodeResponse execute() {

		String classCode = RandomCodeUtil.make(5);

		userFacade.getCurrentUser().getGroup().setClassCode(classCode);

		return new ClassCodeResponse(classCode);
	}
}
