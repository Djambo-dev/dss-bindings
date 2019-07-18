package ru.digital.league.x5.sign.bindings.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.digital.league.x5.sign.bindings.dto.EmployeeBindingInfoDto;
import ru.digital.league.x5.sign.bindings.service.EmployeeBindingService;
import ru.digital.league.x5.sign.bindings.service.MessageService;

@Service
@RequiredArgsConstructor
public class EmployeeBindingListener {

    private final Logger logger = LoggerFactory.getLogger(EmployeeBindingListener.class);

    private final EmployeeBindingService employeeBindingService;
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.inbox_bindings}", containerFactory = "employeeBindingKafkaListenerContainerFactory")
    public void receive(EmployeeBindingInfoDto employeeBindingInfo) {
        try {
            logger.info("Got employeeBindingInfo {} from Kafka", employeeBindingInfo);
            employeeBindingService.save(employeeBindingInfo);
        } catch (Exception exception) {
            throw new RuntimeException(messageService.getMessage("exception.binding.save"));
        }
    }

}
