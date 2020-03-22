package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    @Query("SELECT se FROM StoreEntity se " +
            "LEFT JOIN EmployeeBindingEntity ebe ON se.storeKey.cfoId = ebe.cfoId " +
            "WHERE ebe.personalNumber = :personalNumber AND se.closeDate IS NULL")
    List<StoreEntity> findAllByPersonalNumber(@Param(value = "personalNumber") Long personalNumber);

    StoreEntity findByStoreKeyMdmStoreId(String storeId);
}
