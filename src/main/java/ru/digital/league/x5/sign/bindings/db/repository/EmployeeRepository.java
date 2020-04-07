package ru.digital.league.x5.sign.bindings.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    void deleteAllByCfoIdIn(List<String> cfoIds);

}