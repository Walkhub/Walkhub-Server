package com.walkhub.walkhub.domain.cheering.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.walkhub.walkhub.domain.cheering.exception.CanNotCheerMyselfException;
import com.walkhub.walkhub.domain.cheering.exception.SocketClientNotFoundException;
import com.walkhub.walkhub.domain.cheering.presentation.dto.request.CheerRequest;
import com.walkhub.walkhub.domain.cheering.presentation.dto.response.CheeringMessage;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.websocket.connect.WebSocketJwtHandler;
import com.walkhub.walkhub.global.websocket.property.ClientProperty;
import com.walkhub.walkhub.global.websocket.property.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CheeringService {

    private final UserFacade userFacade;

    public void execute(SocketIOClient socketIOClient, CheerRequest request) {
        User user = userFacade.getUserByAccountId(socketIOClient.get(ClientProperty.USER_KEY));
        if (user.getId().equals(request.getUserId())) {
            throw CanNotCheerMyselfException.EXCEPTION;
        }

        User targetUser = userFacade.getUserById(request.getUserId());

        SocketIOClient clientToSend = Optional.of(WebSocketJwtHandler.socketIOClientMap
                        .get(targetUser.getAccountId()))
                .orElseThrow(() -> SocketClientNotFoundException.EXCEPTION);

        CheeringMessage message = CheeringMessage.builder()
                .userName(user.getName())
                .userId(user.getId())
                .build();

        clientToSend.sendEvent(SocketProperty.NEW_CHEERING, message);

    }
}
