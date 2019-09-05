package ru.digital.league.x5.sign.bindings.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;
import ru.digital.league.x5.sign.bindings.dto.bad.BadStoreInfoDto;
import ru.digital.league.x5.sign.bindings.service.MessageService;
import ru.digital.league.x5.sign.bindings.service.StoreService;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreListener {

    private final StoreService storeService;
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.inbox_stores}", containerFactory = "storeKafkaListenerContainerFactory")
    public void receive(StoreInfoDto storeInfo) {
        if (storeInfo instanceof BadStoreInfoDto) {
            return;
        }
        try {
            log.info("Got storeInfo {} from Kafka", storeInfo);
            storeService.save(storeInfo);
        } catch (Exception exception) {
            throw new RuntimeException(messageService.getMessage("exception.store.save"));
        }
    }

}
