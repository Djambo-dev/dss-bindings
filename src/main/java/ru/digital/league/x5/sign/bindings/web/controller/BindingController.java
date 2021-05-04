package ru.digital.league.x5.sign.bindings.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.service.StoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BindingController {

    private final StoreService storeService;

    @GetMapping(value = "/binding/{personalNumber}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<StoreDto> getStoresByPersonalNumber(@PathVariable("personalNumber") String personalNumber) {
        log.debug("Searching for stores for employee with personal number {}", personalNumber);
        return storeService.getStoresByPersonalNumber(personalNumber);
    }

    @GetMapping(value = "/store/{storeId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public StoreDto getStoreByStoreId(@PathVariable("storeId") String storeId) {
        log.debug("Searching for store with store id {}", storeId);
        return storeService.getStoreByStoreId(storeId);
    }
}
