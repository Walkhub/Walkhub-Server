package com.walkhub.walkhub.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (WalkhubException e) {
            sendErrorMessage(response, e.getErrorCode());
        } catch (Exception e) {
            sendErrorMessage(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendErrorMessage(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        String errorResponseJson = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(errorCode.getStatus());
        response.setContentType("application/json");
        response.getWriter().write(errorResponseJson);
    }
}
