package ru.digital.league.x5.sign.bindings.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;
import ru.digital.league.x5.sign.bindings.service.MessageService;
import ru.digital.league.x5.sign.bindings.service.StoreService;

@Service
@RequiredArgsConstructor
public class StoreListener {

    private final Logger logger = LoggerFactory.getLogger(EmployeeBindingListener.class);

    private final StoreService storeService;
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.inbox_stores}", containerFactory = "storeKafkaListenerContainerFactory")
    public void receive(StoreInfoDto storeInfo) {
        try {
            logger.info("Got storeInfo {} from Kafka", storeInfo);
            storeService.save(storeInfo);
        } catch (Exception exception) {
            throw new RuntimeException(messageService.getMessage("exception.store.save"));
        }
    }

}
