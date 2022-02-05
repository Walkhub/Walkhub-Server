package com.walkhub.walkhub.domain.cheering.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.walkhub.walkhub.domain.cheering.exception.SocketClientNotFoundException;
import com.walkhub.walkhub.domain.cheering.presentation.dto.request.CheerRequest;
import com.walkhub.walkhub.domain.cheering.presentation.dto.response.CheeringMessage;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.websocket.property.ClientProperty;
import com.walkhub.walkhub.global.websocket.property.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheeringService {

    private final SocketIOServer socketIOServer;
    private final UserFacade userFacade;

    public void execute(SocketIOClient socketIOClient, CheerRequest request) {
        User user = userFacade.getUserById(socketIOClient.get(ClientProperty.USER_KEY));

        socketIOServer.getAllClients()
                .forEach(client -> System.out.println(client.get(ClientProperty.USER_KEY).toString()));

        SocketIOClient clientToSend = socketIOServer.getAllClients().stream()
                .filter(client -> client.get(ClientProperty.USER_KEY).equals(request.getUserId()))
                .findFirst()
                .orElseThrow(() -> SocketClientNotFoundException.EXCEPTION);

        CheeringMessage message = CheeringMessage.builder()
                .userName(user.getName())
                .userId(user.getId())
                .build();

        clientToSend.sendEvent(SocketProperty.NEW_CHEERING, message);

    }
}
