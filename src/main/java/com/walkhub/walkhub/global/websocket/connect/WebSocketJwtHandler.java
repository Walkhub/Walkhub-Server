package com.walkhub.walkhub.global.websocket.connect;

import com.corundumstudio.socketio.SocketIOClient;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import com.walkhub.walkhub.global.websocket.property.ClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WebSocketJwtHandler {

    private final JwtTokenProvider jwtTokenProvider;

    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getHttpHeaders().get("Authorization");
        Authentication authentication = jwtTokenProvider.authentication(token);
        client.set(ClientProperty.USER_KEY, authentication.getName());
    }

}
