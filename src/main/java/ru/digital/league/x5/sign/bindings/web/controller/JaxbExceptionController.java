package ru.digital.league.x5.sign.bindings.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.substringBetween;

@Slf4j
@ControllerAdvice
public class JaxbExceptionController {

    private final static String openTag = "<reqId>";
    private final static String closeTag = "</reqId>";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void makeSomeText(HttpMessageNotReadableException ex, HttpServletRequest request, HttpServletResponse response, WebRequest webRequest) throws IOException {
        String message = getRequestBodyMessage(request);
        log.error("Wrong request message with reqId={} by reason: {}.\nMessage:[{}]",
                substringBetween(message, openTag, closeTag),
                ex.getMessage(),
                message);
    }

    private String getRequestBodyMessage(HttpServletRequest request) {
        return new String(((ContentCachingRequestWrapper) request).getContentAsByteArray());
    }
}
