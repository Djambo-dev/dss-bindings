package ru.digital.league.x5.sign.bindings.service;

public interface MessageService {

    String getMessage(String code);

    String getMessage(String code, Object... args);

}
