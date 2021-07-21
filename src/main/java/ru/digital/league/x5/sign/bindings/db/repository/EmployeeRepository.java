package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    void deleteAllByCfoIdIn(List<String> cfoIds);

    @Modifying
    @Query(value =
            "UPDATE bindings.employee " +
            "SET is_deleted = true, modified_date = now() " +
            "WHERE cfo_id in ( :cfoIds )",
            nativeQuery = true)
    void markAsDeletedByCfoId(@Param(value = "cfoIds") List<String> cfoIds);
}