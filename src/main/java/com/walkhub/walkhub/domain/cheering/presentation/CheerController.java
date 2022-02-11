package com.walkhub.walkhub.domain.cheering.presentation;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.walkhub.walkhub.domain.cheering.presentation.dto.request.CheerRequest;
import com.walkhub.walkhub.domain.cheering.service.CheeringService;
import com.walkhub.walkhub.global.websocket.property.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CheerController {

    private final CheeringService cheeringService;

    @OnEvent(SocketProperty.CHEERING)
    public void cheering(SocketIOClient socketIOClient, CheerRequest request) {
        cheeringService.execute(socketIOClient, request);
    }

}
