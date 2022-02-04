package com.walkhub.walkhub.global.websocket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Order(1)
@Component
public class SocketRunner implements CommandLineRunner {

    private final SocketIOServer socketIOServer;

    @Override
    public void run(String... args) {
        socketIOServer.start();
    }

}
