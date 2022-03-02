package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryGoalWalkCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryGoalWalkCountService {

	private final UserFacade userFacade;

	public QueryGoalWalkCountResponse execute() {
		User user = userFacade.getCurrentUser();

		return new QueryGoalWalkCountResponse(user.getDailyWalkCountGoal());
	}
}
