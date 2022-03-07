package com.walkhub.walkhub.global.annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@WalkhubService
public @interface WalkhubService {
}
