package ru.digital.league.x5.sign.bindings.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeListDto;
import ru.digital.league.x5.sign.bindings.dto.bad.BadClusterEmployeeListDto;
import ru.digital.league.x5.sign.bindings.service.ClusterEmployeeService;
import ru.digital.league.x5.sign.bindings.service.MessageService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClusterEmployeeListener {

    private final ClusterEmployeeService clusterEmployeeService;
    private final MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.cluster_employee}", containerFactory = "`clusterEmployeeBindingKafkaListenerContainerFactory`")
    public void receive(ClusterEmployeeListDto clusterEmployeeList) {
        if (clusterEmployeeList instanceof BadClusterEmployeeListDto) {
            BadClusterEmployeeListDto badObject = (BadClusterEmployeeListDto) clusterEmployeeList;
            log.error("Got {} {}", badObject.getHeaders(), new String(badObject.getFailedDecode()));
            return;
        }
        try {
            log.info("Got employeeInfo {} from Kafka", clusterEmployeeList);
            clusterEmployeeService.save(clusterEmployeeList);
        } catch (Exception exception) {
            throw new RuntimeException(messageService.getMessage("exception.binding.save"));
        }
    }

}
