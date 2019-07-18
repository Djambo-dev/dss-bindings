package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeBindingEntity;

public interface EmployeeBindingRepository extends JpaRepository<EmployeeBindingEntity, Long> {

    void deleteAllByCfoIdAndPersonalNumber(String cfoId, Long personalNumber);
}
