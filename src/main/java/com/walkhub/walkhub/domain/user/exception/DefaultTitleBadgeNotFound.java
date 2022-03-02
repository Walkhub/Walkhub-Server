package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class DefaultTitleBadgeNotFound extends WalkhubException {

	public static final WalkhubException EXCEPTION =
		new DefaultTitleBadgeNotFound();

	public DefaultTitleBadgeNotFound() {
		super(ErrorCode.DEFAULT_TITLE_BADGE_NOT_FOUND);
	}
}
