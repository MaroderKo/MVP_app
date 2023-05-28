package com.mvp.exception;

public class NoWinnerTeamException extends RuntimeException {
    public NoWinnerTeamException(String message) {
        super(message);
    }
}
