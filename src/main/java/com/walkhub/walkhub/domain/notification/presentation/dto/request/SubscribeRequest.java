package com.walkhub.walkhub.domain.notification.presentation.dto.request;

import com.walkhub.walkhub.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscribeRequest {

    @NotNull(message = "List는 Null일 수 없습니다.")
    private List<User> users;

}
