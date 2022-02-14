package com.walkhub.walkhub.global.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class InvalidVerificationCodeException extends WalkhubException {

	public static final WalkhubException EXCEPTION =
		new InvalidVerificationCodeException();

	private InvalidVerificationCodeException() {
		super(ErrorCode.INVALID_VERIFICATION_CODE);
	}
}
