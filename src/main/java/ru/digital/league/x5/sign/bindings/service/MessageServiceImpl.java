package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final static Locale ru_RU = new Locale("ru", "RU");

    private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final ResourceBundleMessageSource messageSource;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, ru_RU);
        } catch (Exception e) {
            logger.warn("Get message key='" + code + "' error:" + e.getMessage());
        }
        return code;
    }

    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, ru_RU);
    }

}