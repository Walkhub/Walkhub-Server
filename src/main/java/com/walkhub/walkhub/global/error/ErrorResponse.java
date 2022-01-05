package com.walkhub.walkhub.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
	private final int status;
	private final String code;
	private final String message;
}