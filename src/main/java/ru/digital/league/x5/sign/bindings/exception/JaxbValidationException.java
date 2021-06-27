package ru.digital.league.x5.sign.bindings.exception;

import lombok.Getter;

@Getter
public class JaxbValidationException extends RuntimeException {
    private String incomingMessage;

    public JaxbValidationException(String incomingMessage, Throwable cause) {
        super(cause);
        this.incomingMessage = incomingMessage;
    }
}
