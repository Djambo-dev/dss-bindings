package ru.digital.league.x5.sign.bindings.web.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.service.StoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bindings")
public class BindingController {

    private final Logger logger = LoggerFactory.getLogger(BindingController.class);

    private final StoreService storeService;

    @GetMapping(value = "/stores/{personalNumber}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<StoreDto> getStoreIdsByPersonalNumber(@PathVariable("personalNumber") Long personalNumber) {
        logger.info("Searching for stores for employee with personal number {}", personalNumber);
        return storeService.getStoresByPersonalNumber(personalNumber);
    }

}
