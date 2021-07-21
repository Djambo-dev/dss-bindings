package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digital.league.x5.sign.bindings.db.entity.ClusterEmployeeEntity;

import java.util.List;

public interface ClusterEmployeeRepository extends JpaRepository<ClusterEmployeeEntity, Long> {

    void deleteAllByClusterIdIn(List<String> clusterIdList);

    @Modifying
    @Query(value =
            "UPDATE bindings.cluster_employee " +
            "SET is_deleted = true, modified_date = now() " +
            "WHERE cluster_id in ( :clusterIds )",
            nativeQuery = true)
    void markAsDeletedByCfoId(@Param(value = "clusterIds") List<String> clusterIds);

}
