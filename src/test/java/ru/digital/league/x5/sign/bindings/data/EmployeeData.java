package ru.digital.league.x5.sign.bindings.data;

import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;
import ru.digital.league.x5.sign.bindings.xml.model.Employee;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeBinding;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeData {

    public static EmployeeList employeeList() {
        EmployeeList employeeList = new EmployeeList();
        employeeList.setEmployeeBindings(Arrays.asList(employeeBinding()));
        return employeeList;
    }

    public static EmployeeList emptyEmployeeList() {
        EmployeeList employeeList = new EmployeeList();
        employeeList.setEmployeeBindings(Arrays.asList(emptyEmployeeBinding()));
        return employeeList;
    }

    public static EmployeeList employeeListWithNull() {
        EmployeeList employeeList = new EmployeeList();
        employeeList.setEmployeeBindings(Arrays.asList(emptyEmployeeBindingWithNull()));
        return employeeList;
    }

    public static EmployeeList employeeListWithNull_and_withoutNull() {
        EmployeeList employeeList = new EmployeeList();
        employeeList.setEmployeeBindings(Arrays.asList(employeeBinding(), emptyEmployeeBindingWithNull()));
        return employeeList;
    }

    public static EmployeeList employeeListWithExcludedPositionId() {
        EmployeeList employeeList = new EmployeeList();
        employeeList.setEmployeeBindings(Arrays.asList(employeeBindingWithExcludedPosition()));
        return employeeList;
    }

    public static EmployeeBinding employeeBinding() {
        EmployeeBinding employeeBinding = new EmployeeBinding();
        employeeBinding.setCfoId("E2221111");
        employeeBinding.setEmployeeBindings(Arrays.asList(employee1(), employee2()));
        return employeeBinding;

    }

    public static EmployeeBinding emptyEmployeeBinding() {
        EmployeeBinding employeeBinding = new EmployeeBinding();
        employeeBinding.setCfoId("E3221112");
        employeeBinding.setEmployeeBindings(Collections.EMPTY_LIST);
        return employeeBinding;
    }

    public static EmployeeBinding emptyEmployeeBindingWithNull() {
        EmployeeBinding employeeBinding = new EmployeeBinding();
        employeeBinding.setCfoId("E4221112");
        employeeBinding.setEmployeeBindings(null);
        return employeeBinding;
    }

    public static EmployeeBinding employeeBindingWithExcludedPosition() {
        EmployeeBinding employeeBinding = new EmployeeBinding();
        employeeBinding.setCfoId("E2221111");
        employeeBinding.setEmployeeBindings(Arrays.asList(employee1(), employee2(), employee3()));
        return employeeBinding;
    }


    public static Employee employee1() {
        Employee employee = new Employee();
        employee.setPersonalNumber("961135");
        employee.setPersonalLogin("EKATERINA.PARUBOK");
        employee.setPositionId(50000566L);
        employee.setPositionName("Директор");
        employee.setLinkedPersonalNumber("098123");
        return employee;
    }

    private static EmployeeEntity employeeEntity1() {
        EmployeeEntity ee = new EmployeeEntity();
        ee.setCfoId("E2221111");
        ee.setPersonalNumber("098123");
        ee.setPersonalLogin("EKATERINA.PARUBOK");
        ee.setPositionId(50000566L);
        ee.setPositionName("Директор");
        ee.setPartTimePersonalNumber("961135");
        ee.setIsDeleted(false);
        return ee;
    }

    /**
     * Создает EmployeeEntity, который учитывает значение поля partTimePersonalNumber
     */

    private static EmployeeEntity employeeEntity1RevertPartTimePN() {
        EmployeeEntity ee = new EmployeeEntity();
        ee.setCfoId("E2221111");
        ee.setPersonalNumber("961135");
        ee.setPersonalLogin("EKATERINA.PARUBOK");
        ee.setPositionId(50000566L);
        ee.setPositionName("Директор");
        ee.setPartTimePersonalNumber("098123");
        ee.setIsDeleted(false);
        return ee;
    }

    private static Employee employee2() {
        Employee employee = new Employee();
        employee.setPersonalNumber("098123");
        employee.setPersonalLogin("ARTEM.PETROVSKIY");
        employee.setPositionId(50000500L);
        employee.setPositionName("Супервайзер");
        employee.setLinkedPersonalNumber(null);
        return employee;
    }

    private static EmployeeEntity employeeEntity2() {
        EmployeeEntity ee = new EmployeeEntity();
        ee.setCfoId("E2221111");
        ee.setPersonalNumber("098123");
        ee.setPersonalLogin("ARTEM.PETROVSKIY");
        ee.setPositionId(50000500L);
        ee.setPositionName("Супервайзер");
        ee.setPartTimePersonalNumber(null);
        ee.setIsDeleted(false);
        return ee;
    }

    private static Employee employee3() {
        Employee employee = new Employee();
        employee.setPersonalNumber("098120");
        employee.setPersonalLogin("SIRKA.BURKA");
        employee.setPositionId(50000999L);
        employee.setPositionName("Супервайзер");
        employee.setLinkedPersonalNumber(null);
        return employee;
    }

    public static List<EmployeeEntity> employeeEntityList1() {
        return List.of(employeeEntity1(), employeeEntity2());
    }
}
