package com.walkhub.walkhub.global.websocket.connect;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import com.walkhub.walkhub.global.websocket.property.ClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RequiredArgsConstructor
@RestController
public class WebSocketJwtHandler {

    private final JwtTokenProvider jwtTokenProvider;

    public static ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getHttpHeaders().get("Authorization");
        Authentication authentication = jwtTokenProvider.authentication(token);
        socketIOClientMap.put(authentication.getName(), client);
        client.set(ClientProperty.USER_KEY, authentication.getName());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        socketIOClientMap.remove(client.get(ClientProperty.USER_KEY).toString());
    }

}
