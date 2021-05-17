package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    @Query("SELECT se FROM StoreEntity se " +
            "LEFT JOIN EmployeeEntity ee ON se.cfoId = ee.cfoId " +
            "WHERE ee.personalNumber = :personalNumber AND se.closeDate IS NULL AND se.openDate IS NOT NULL")
    List<StoreEntity> findAllByPersonalNumber(@Param(value = "personalNumber") String personalNumber);

    @Query("SELECT se FROM StoreEntity se " +
            "LEFT JOIN ClusterEmployeeEntity cee ON se.clusterId = cee.clusterId " +
            "WHERE cee.personalNumber = :personalNumber AND se.closeDate IS NULL AND se.openDate IS NOT NULL")
    List<StoreEntity> findAllByClusterPersonalNumber(@Param(value = "personalNumber") String personalNumber);

    @Query(value = "SELECT se.mdm_store_id , se.cfo_id , se.name , se.address, se.open_date , se.cluster_id , se.close_date, se.modified_date " +
            "FROM bindings.stores AS se " +
            "LEFT JOIN  bindings.cluster_employee AS ce ON ce.cluster_id = se.cluster_id " +
            "WHERE (ce.personal_number = :personalNumber) AND " +
            "(ce.position_id in :positionIdList) AND " +
            "(extract ( day from now() - se.close_date ) < :datePeriod) " +
            "UNION " +
            "SELECT se.mdm_store_id , se.cfo_id , se.name , se.address, se.open_date , se.cluster_id , se.close_date, se.modified_date " +
            "FROM bindings.stores AS se " +
            "LEFT JOIN  bindings.employee AS e ON e.cfo_id = se.cfo_id " +
            "WHERE (e.personal_number = :personalNumber) AND " +
            "(e.position_id in :positionIdList) AND " +
            "(extract ( day from now() - se.close_date ) < :datePeriod)",
            nativeQuery = true)
    List<StoreEntity> findAllClosedShopByPersonalNumber(@Param(value = "personalNumber") String personalNumber,
                                                        @Param(value = "datePeriod") Integer datePeriod,
                                                        @Param(value = "positionIdList") List<Long> positionIdList);

    StoreEntity findByMdmStoreId(String storeId);

}
