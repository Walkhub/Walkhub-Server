package com.walkhub.walkhub.global.websocket.exception;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.walkhub.walkhub.global.error.ErrorResponse;
import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;
import com.walkhub.walkhub.global.websocket.property.SocketProperty;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class SocketExceptionListener implements ExceptionListener {
    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
        client.disconnect();
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        return false;
    }

    private void runExceptionHandling(Exception e, SocketIOClient client) {
        final ErrorCode errorCode;

        if (e.getCause() instanceof WalkhubException) {
            errorCode = ((WalkhubException) e.getCause()).getErrorCode();
        } else {
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        }
        ErrorResponse message = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        client.sendEvent(SocketProperty.ERROR, message);
    }

}
