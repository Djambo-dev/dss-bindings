package ru.digital.league.x5.sign.bindings.data;

import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;
import ru.digital.league.x5.sign.bindings.dto.EmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.EmployeeListDto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeData {

    public static EmployeeListDto employeeInfoDto() {

        List<EmployeeDto> employeeBindingDtos = Arrays.asList(employeeBindingDto1(), employeeBindingDto2());

        return EmployeeListDto.builder()
                .employeeBindingList(employeeBindingDtos)
                .build();
    }

    public static EmployeeListDto employeeInfoDtoWithNull() {

        List<EmployeeDto> employeeBindingDtos = Arrays.asList(employeeBindingDto1(), employeeBindingDtoNull());

        return EmployeeListDto.builder()
                .employeeBindingList(employeeBindingDtos)
                .build();
    }

    public static EmployeeListDto emptyEmployeeInfoDto() {
        return EmployeeListDto.builder()
                .employeeBindingList(Collections.emptyList())
                .build();
    }

    private static EmployeeDto employeeBindingDto1() {
        return EmployeeDto.builder()
                .cfoId("E1007345")
                .personalNumber("961135")
                .personalLogin("EKATERINA.PARUBOK")
                .positionId(50000566L)
                .positionName("Директор")
                .linkedPersonalNumber("098123")
                .build();
    }

    public static List<EmployeeEntity> employeeEntityList1() {
        EmployeeEntity ee1 = employeeEntity1();
        return List.of(ee1);
    }

    private static EmployeeEntity employeeEntity1() {
        EmployeeEntity ee = new EmployeeEntity();
        ee.setCfoId("E1007345");
        ee.setPersonalNumber("098123");
        ee.setPersonalLogin("EKATERINA.PARUBOK");
        ee.setPositionId(50000566L);
        ee.setPositionName("Директор");
        ee.setPartTimePersonalNumber("961135");
        return ee;
    }

    private static EmployeeDto employeeBindingDto2() {
        return EmployeeDto.builder()
                .cfoId("E11111111")
                .personalNumber("323445")
                .personalLogin("ILYA.MADDYSON")
                .positionId(50000566L)
                .positionName("Супервайзер")
                .build();
    }

    /**
     * Специальный bean в котором все поля кроме cfoId равны нулю
     * Такие объекты присылает MDM, записывать их в БД не имеет смысла
     */
    private static EmployeeDto employeeBindingDtoNull() {
        return EmployeeDto.builder()
                .cfoId("E222222")
                .build();
    }

}
