package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeBindingEntity;

import java.util.List;

public interface EmployeeBindingRepository extends JpaRepository<EmployeeBindingEntity, Long> {

    void deleteAllByCfoIdIn(List<String> cfoIds);

}