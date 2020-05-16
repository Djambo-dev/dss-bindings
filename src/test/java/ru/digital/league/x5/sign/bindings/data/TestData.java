package ru.digital.league.x5.sign.bindings.data;

import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;
import ru.digital.league.x5.sign.bindings.dto.EmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.EmployeeInfoDto;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestData {

    public static StoreInfoDto storeInfoDto() {

        List<StoreDto> storeDtos = Arrays.asList(storeDto1(), storeDto2());

        return StoreInfoDto.builder()
                .stores(storeDtos)
                .build();
    }

    public static StoreInfoDto emptyStoreInfoDto() {
        return StoreInfoDto.builder()
                .stores(Collections.emptyList())
                .build();
    }

    public static StoreDto storeDto1() {
        return StoreDto.builder()
                .mdmStoreId("3402")
                .name("4467-Пятерочка.")
                .cfoId("E1007345")
                .address("184510, Мурманская обл, Мончегорск г, Бредова ул, 30")
                .openDate(LocalDate.of(2014, 7, 7))
                .closeDate(null)
                .build();
    }

    public static StoreDto storeDto2() {
        return StoreDto.builder()
                .mdmStoreId("1111")
                .name("1111-Пятерочка.")
                .cfoId("E11111111")
                .address("111111, Москва г, Никольская ул, 1")
                .openDate(LocalDate.of(2011, 1, 1))
                .closeDate(null)
                .build();
    }

    public static EmployeeInfoDto employeeInfoDto() {

        List<EmployeeDto> employeeBindingDtos = Arrays.asList(employeeBindingDto1(), employeeBindingDto2());

        return EmployeeInfoDto.builder()
                .employeeBindings(employeeBindingDtos)
                .build();
    }

    public static EmployeeInfoDto employeeInfoDtoWithNull() {

        List<EmployeeDto> employeeBindingDtos = Arrays.asList(employeeBindingDto1(), employeeBindingDtoNull());

        return EmployeeInfoDto.builder()
                .employeeBindings(employeeBindingDtos)
                .build();
    }

    public static EmployeeInfoDto emptyEmployeeInfoDto() {
        return EmployeeInfoDto.builder()
                .employeeBindings(Collections.emptyList())
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