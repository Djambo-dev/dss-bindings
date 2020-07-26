package ru.digital.league.x5.sign.bindings.data;

import ru.digital.league.x5.sign.bindings.db.entity.ClusterEmployeeEntity;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeListDto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClusterEmployeeData {

    // dto list
    public static ClusterEmployeeListDto clusterEmployeeListDto() {
        List<ClusterEmployeeDto> clusterEmployeeDtoList = Arrays.asList(clusterEmployeeDto1(), clusterEmployeeDto2());
        return ClusterEmployeeListDto.builder()
                .clusterEmployeeBindingList(clusterEmployeeDtoList)
                .build();
    }

    public static ClusterEmployeeListDto clusterEmployeeListDto_forFilter() {
        List<ClusterEmployeeDto> clusterEmployeeDtoList = Arrays.asList(clusterEmployeeDto1(), clusterEmployeeDto3());
        return ClusterEmployeeListDto.builder()
                .clusterEmployeeBindingList(clusterEmployeeDtoList)
                .build();
    }

    public static ClusterEmployeeListDto emptyClusterEmployeeListDto() {
        return ClusterEmployeeListDto.builder()
                .clusterEmployeeBindingList(Collections.emptyList())
                .build();
    }

    public static ClusterEmployeeListDto clusterEmployeeWithoutNOO() {
        List<ClusterEmployeeDto> clusterEmployeeDtoList = Arrays.asList(clusterEmployeeDto3());
        return ClusterEmployeeListDto.builder()
                .clusterEmployeeBindingList(clusterEmployeeDtoList)
                .build();
    }

    public static ClusterEmployeeListDto clusterEmployeeWithEmptyPersonalNUmber() {
        List<ClusterEmployeeDto> clusterEmployeeDtoList
                = Arrays.asList(clusterEmployeeDto_nullPersonalNumber(), clusterEmployeeDto_blankPersonalNumber());
        return ClusterEmployeeListDto.builder()
                .clusterEmployeeBindingList(clusterEmployeeDtoList)
                .build();
    }

    // dto
    private static ClusterEmployeeDto clusterEmployeeDto1() {
        return ClusterEmployeeDto.builder()
                .clusterId("8888")
                .role("Начальник отдела операций")
                .personalNumber("961135")
                .personalLogin("EKATERINA.PARUBOK")
                .positionId(50000566L)
                .positionName("Начальник отдела операций")
                .linkedPersonalNumber("098123")
                .build();
    }

    private static ClusterEmployeeDto clusterEmployeeDto2() {
        return ClusterEmployeeDto.builder()
                .clusterId("7777")
                .role("Начальник отдела операций")
                .personalNumber("323445")
                .personalLogin("ILYA.MADDYSON")
                .positionId(50000566L)
                .positionName("Начальник отдела операций")
                .build();
    }

    private static ClusterEmployeeDto clusterEmployeeDto3() {
        return ClusterEmployeeDto.builder()
                .clusterId("7777")
                .role("Начальник отдела развития")
                .personalNumber("323445")
                .personalLogin("IVAN.KUPALA")
                .positionId(50000567L)
                .positionName("Начальник отдела развития")
                .build();
    }

    /**
     * Специальный bean в котором все поля кроме clusterId равны нулю
     * Такие объекты присылает MDM, записывать их в БД не имеет смысла
     */
    private static ClusterEmployeeDto clusterEmployeeDto_nullPersonalNumber() {
        return ClusterEmployeeDto.builder()
                .clusterId("0000")
                .build();
    }

    private static ClusterEmployeeDto clusterEmployeeDto_blankPersonalNumber() {
        return ClusterEmployeeDto.builder()
                .clusterId("0000")
                .personalNumber("")
                .build();
    }

    // entityList
    public static List<ClusterEmployeeEntity> clusterEmployeeEntityList1() {
        ClusterEmployeeEntity ee1 = clusterEmployeeEntity1();
        return List.of(ee1);
    }

    public static List<ClusterEmployeeEntity> clusterEmployeeEntityList_forFilter() {
        ClusterEmployeeEntity ee1 = clusterEmployeeEntity1();
        return List.of(ee1);
    }

    // entity
    private static ClusterEmployeeEntity clusterEmployeeEntity1() {
        ClusterEmployeeEntity cee = new ClusterEmployeeEntity();
        cee.setClusterId("8888");
        cee.setRole("Начальник отдела операций");
        cee.setPersonalNumber("098123");
        cee.setPersonalLogin("EKATERINA.PARUBOK");
        cee.setPositionId(50000566L);
        cee.setPositionName("Начальник отдела операций");
        cee.setPartTimePersonalNumber("961135");
        return cee;
    }

}