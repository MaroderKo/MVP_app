package com.mvp.exception.handler;

import com.mvp.exception.NoWinnerTeamException;
import com.mvp.exception.WrongFileFormatException;
import com.mvp.model.response.ResponseBodyEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseBodyEntity> responseStatusExceptionHandler(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new ResponseBodyEntity(e.getReason()));
    }

    @ExceptionHandler({NoWinnerTeamException.class, WrongFileFormatException.class})
    public ResponseEntity<ResponseBodyEntity> GameStateExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBodyEntity(e.getMessage()));
    }
}
