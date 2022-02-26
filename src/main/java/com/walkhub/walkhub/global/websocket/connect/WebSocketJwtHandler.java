package com.walkhub.walkhub.global.websocket.connect;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import com.walkhub.walkhub.global.websocket.property.ClientProperty;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RequiredArgsConstructor
@RestController
public class WebSocketJwtHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final JwtTokenProvider jwtTokenProvider;

    public static final ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getHttpHeaders().get("Authorization");
        Authentication authentication = jwtTokenProvider.authentication(token);
        socketIOClientMap.put(authentication.getName(), client);
        client.set(ClientProperty.USER_KEY, authentication.getName());
        logger.info(String.format("Connected : %s", client.getSessionId()));
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        socketIOClientMap.remove(client.get(ClientProperty.USER_KEY).toString());
        logger.info(String.format("DisConnected : %s", client.getSessionId()));
    }

}
