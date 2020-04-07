package ru.digital.league.x5.sign.bindings.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.digital.league.x5.sign.bindings.dto.EmployeeInfoDto;
import ru.digital.league.x5.sign.bindings.dto.bad.BadEmployeeInfoDto;
import ru.digital.league.x5.sign.bindings.service.EmployeeService;
import ru.digital.league.x5.sign.bindings.service.MessageService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeListener {

    private final EmployeeService employeeService;
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.inbox_bindings}", containerFactory = "employeeBindingKafkaListenerContainerFactory")
    public void receive(EmployeeInfoDto employeeInfo) {
        if (employeeInfo instanceof BadEmployeeInfoDto) {
            return;
        }
        try {
            log.info("Got employeeInfo {} from Kafka", employeeInfo);
            employeeService.save(employeeInfo);
        } catch (Exception exception) {
            throw new RuntimeException(messageService.getMessage("exception.binding.save"));
        }
    }

}