package ru.digital.league.x5.sign.bindings.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.digital.league.x5.sign.bindings.dto.EmployeeBindingInfoDto;
import ru.digital.league.x5.sign.bindings.dto.bad.BadEmployeeBindingInfoDto;
import ru.digital.league.x5.sign.bindings.service.EmployeeBindingService;
import ru.digital.league.x5.sign.bindings.service.MessageService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeBindingListener {

    private final EmployeeBindingService employeeBindingService;
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.inbox_bindings}", containerFactory = "employeeBindingKafkaListenerContainerFactory")
    public void receive(EmployeeBindingInfoDto employeeBindingInfo) {
        if (employeeBindingInfo instanceof BadEmployeeBindingInfoDto) {
            return;
        }
        try {
            log.info("Got employeeBindingInfo {} from Kafka", employeeBindingInfo);
            employeeBindingService.save(employeeBindingInfo);
        } catch (Exception exception) {
            throw new RuntimeException(messageService.getMessage("exception.binding.save"));
        }
    }

}
