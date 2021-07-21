package ru.digital.league.x5.sign.bindings.data;

import ru.digital.league.x5.sign.bindings.db.entity.ClusterEmployeeEntity;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployee;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeBinding;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;

import java.util.List;

public class ClusterEmployeeData {

    public static ClusterEmployeeList clusterEmployeeList() {
        ClusterEmployeeList clusterEmployeeList = new ClusterEmployeeList();
        clusterEmployeeList.setClusterEmployeeBindingList(List.of(clusterEmployeeBindingCorrectValue()));
        return clusterEmployeeList;
    }

    public static ClusterEmployeeList emptyClusterEmployeeList() {
        ClusterEmployeeList clusterEmployeeList = new ClusterEmployeeList();
        clusterEmployeeList.setClusterEmployeeBindingList(List.of(emptyClusterEmployeeBinding()));
        return clusterEmployeeList;
    }

    public static ClusterEmployeeList clusterEmployeeListWithInvalidValue() {
        ClusterEmployeeList clusterEmployeeList = new ClusterEmployeeList();
        clusterEmployeeList.setClusterEmployeeBindingList(List.of(clusterEmployeeBindingWithInvalidValue()));
        return clusterEmployeeList;
    }

    public static ClusterEmployeeList clusterEmployeeListWithInvalidValue_and_CorrectValue(){
        ClusterEmployeeList clusterEmployeeList = new ClusterEmployeeList();
        clusterEmployeeList.setClusterEmployeeBindingList(List.of(clusterEmployeeBindingWithInvalidValue(), clusterEmployeeBindingCorrectValue()));
        return clusterEmployeeList;
    }

    public static ClusterEmployeeBinding clusterEmployeeBindingCorrectValue() {
        ClusterEmployeeBinding clusterEmployeeBinding = new ClusterEmployeeBinding();
        clusterEmployeeBinding.setClusterId("0000");
        clusterEmployeeBinding.setClusterEmployeeList(List.of(clusterEmployee1CorrectValue()));
        return clusterEmployeeBinding;
    }

    public static ClusterEmployeeBinding clusterEmployeeBindingWithInvalidValue() {
        ClusterEmployeeBinding clusterEmployeeBinding = new ClusterEmployeeBinding();
        clusterEmployeeBinding.setClusterId("0001");
        clusterEmployeeBinding.setClusterEmployeeList(List.of(clusterEmployee1PersonalNumberNull()));
        return clusterEmployeeBinding;
    }

    public static ClusterEmployeeBinding emptyClusterEmployeeBinding() {
        ClusterEmployeeBinding clusterEmployeeBinding = new ClusterEmployeeBinding();
        clusterEmployeeBinding.setClusterId("0000");
        clusterEmployeeBinding.setClusterEmployeeList(null);
        return clusterEmployeeBinding;
    }

    public static ClusterEmployee clusterEmployee1CorrectValue() {
        ClusterEmployee clusterEmployee = new ClusterEmployee();
        clusterEmployee.setRole("Начальник отдела операций");
        clusterEmployee.setPersonalNumber("961135");
        clusterEmployee.setPersonalLogin("EKATERINA.PARUBOK");
        clusterEmployee.setPositionId(50000566L);
        clusterEmployee.setPositionName("Начальник отдела операций");
        clusterEmployee.setLinkedPersonalNumber("098123");
        return clusterEmployee;
    }

    public static ClusterEmployee clusterEmployee1PersonalNumberNull() {
        ClusterEmployee clusterEmployee = new ClusterEmployee();
        clusterEmployee.setRole("Начальник отдела операций");
        clusterEmployee.setPersonalNumber(null);
        clusterEmployee.setPersonalLogin("EKATERINA.PARUBOK");
        clusterEmployee.setPositionId(50000566L);
        clusterEmployee.setPositionName("Начальник отдела операций");
        clusterEmployee.setLinkedPersonalNumber("098123");
        return clusterEmployee;
    }

    private static ClusterEmployeeEntity clusterEmployeeEntity1() {
        ClusterEmployeeEntity cee = new ClusterEmployeeEntity();
        cee.setClusterId("0000");
        cee.setRole("Начальник отдела операций");
        cee.setPersonalNumber("098123");
        cee.setPersonalLogin("EKATERINA.PARUBOK");
        cee.setPositionId(50000566L);
        cee.setPositionName("Начальник отдела операций");
        cee.setPartTimePersonalNumber("961135");
        cee.setIsDeleted(false);
        return cee;
    }

    public static List<ClusterEmployeeEntity> clusterEmployeeEntityList1() {
        ClusterEmployeeEntity ee1 = clusterEmployeeEntity1();
        return List.of(ee1);
    }
}