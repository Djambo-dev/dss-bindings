package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    @Query(value = "SELECT * FROM ( SELECT * FROM bindings.stores AS se " +
            "LEFT JOIN bindings.employee as e ON se.cfo_id = e.cfo_id " +
            "WHERE e.personal_number = :personalNumber) as js " +
            "WHERE (js.close_date IS NULL) " +
            "OR    (js.position_id in :positionIdList AND EXTRACT (DAY FROM now() - js.close_date) < :intervalDays)",
            nativeQuery = true)
    List<StoreEntity> findAllByPersonalNumber(@Param(value = "personalNumber") String personalNumber,
                                              @Param(value = "intervalDays") Integer intervalDays,
                                              @Param(value = "positionIdList") List<Long> positionIdList);

    @Query(value = "SELECT * FROM ( SELECT * FROM bindings.stores AS se " +
            "LEFT JOIN bindings.cluster_employee as ce ON se.cluster_id = ce.cluster_id " +
            "WHERE ce.personal_number = :personalNumber) as js " +
            "WHERE (js.close_date IS NULL) " +
            "OR    (js.position_id in :positionIdList AND EXTRACT (DAY FROM now() - js.close_date) < :intervalDays) ",
            nativeQuery = true)
    List<StoreEntity> findAllByClusterPersonalNumber(@Param(value = "personalNumber") String personalNumber,
                                                     @Param(value = "intervalDays") Integer intervalDays,
                                                     @Param(value = "positionIdList") List<Long> positionIdList);

    StoreEntity findByStoreKeyMdmStoreId(String storeId);
}
