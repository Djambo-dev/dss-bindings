package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digital.league.x5.sign.bindings.db.entity.ClusterEmployeeEntity;

import java.util.List;

public interface ClusterEmployeeRepository extends JpaRepository<ClusterEmployeeEntity, Long> {

    void deleteAllByClusterIdIn(List<String> clusterIdList);

    ClusterEmployeeEntity getByPersonalNumber(String personalNumber);

}
