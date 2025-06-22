package com.github.hotechbackend.shared.infrastructure.sfw.exeption.handler;

import com.github.hotechbackend.shared.domain.aggregates.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.IOException;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<MessageResponse> handleRuntimeException(RuntimeException e) {
        LOGGER.error("Debugging error", e);
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<MessageResponse> handleIOException(IOException e) {
        LOGGER.error("Debugging error", e);
        return ResponseEntity.badRequest().body(new MessageResponse(e.getLocalizedMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        LOGGER.error("Debugging error", e);
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    public ResponseEntity<MessageResponse> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        LOGGER.error("Debugging error", e);
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

}
