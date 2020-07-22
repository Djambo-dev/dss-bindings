package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    @Query("SELECT se FROM StoreEntity se " +
            "LEFT JOIN EmployeeEntity ee ON se.storeKey.cfoId = ee.cfoId " +
            "WHERE ee.personalNumber = :personalNumber AND se.closeDate IS NULL")
    List<StoreEntity> findAllByPersonalNumber(@Param(value = "personalNumber") String personalNumber);

    @Query("SELECT se FROM StoreEntity se " +
            "LEFT JOIN ClusterEmployeeEntity cee ON se.clusterId = cee.clusterId " +
            "WHERE cee.personalNumber = :personalNumber AND se.closeDate IS NULL")
    List<StoreEntity> findAllByClusterPersonalNumber(@Param(value = "personalNumber") String personalNumber);

    StoreEntity findByStoreKeyMdmStoreId(String storeId);
}
