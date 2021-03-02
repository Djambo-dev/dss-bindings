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

    @Query(value = "SELECT * FROM bindings.stores AS se " +
            "LEFT JOIN bindings.employee AS e ON se.cfo_id = e.cfo_id " +
            "WHERE e.personal_number = :personalNumber AND se.close_date IS NULL " +
            "OR e.personal_number = :personalNumber AND EXTRACT (DAY FROM now() - se.close_date) < :intervalDays ",
            nativeQuery = true)
    List<StoreEntity> findAllWithClosedShopByPersonalNumber(@Param(value = "personalNumber") String personalNumber,
                                                            @Param(value = "intervalDays") Integer intervalDays);

    @Query(value = "SELECT * FROM bindings.stores AS se " +
            "LEFT JOIN bindings.cluster_employee AS ce ON se.cluster_id = ce.cluster_id " +
            "WHERE ce.personal_number = :personalNumber AND se.close_date IS NULL " +
            "OR ce.personal_number = :personalNumber AND EXTRACT (DAY FROM now() - se.close_date) < :intervalDays ",
            nativeQuery = true)
    List<StoreEntity> findAllWithClosedShopByClusterPersonalNumber(@Param(value = "personalNumber") String personalNumber,
                                                                   @Param(value = "intervalDays") Integer intervalDays);
    StoreEntity findByStoreKeyMdmStoreId(String storeId);
}
