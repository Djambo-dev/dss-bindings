package ru.digital.league.x5.sign.bindings.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.digital.league.x5.sign.bindings.exception.JaxbValidationException;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.substringBetween;

@Slf4j
@ControllerAdvice
public class JaxbExceptionHandler {

    private final static String OPEN_TAG = "<reqId>";
    private final static String CLOSE_TAG = "</reqId>";

    @ExceptionHandler(JaxbValidationException.class)
    public void processErrorLog(JaxbValidationException ex) throws IOException {
        String payload = ex.getIncomingMessage();
        String reqId = substringBetween(payload, OPEN_TAG, CLOSE_TAG);
        log.error("Wrong request message with reqId={} by reason: {}.\nPayload:[{}]",
                reqId == null ? "undefined" : reqId,
                ex.getMessage(),
                payload);
    }
}
