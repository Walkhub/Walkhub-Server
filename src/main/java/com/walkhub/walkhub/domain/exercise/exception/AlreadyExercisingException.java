package com.walkhub.walkhub.domain.exercise.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class AlreadyExercisingException extends WalkhubException {


	public static final WalkhubException EXCEPTION =
		new AlreadyExercisingException();

	private AlreadyExercisingException() {
		super(ErrorCode.ALREADY_EXERCISING);
	}

}
