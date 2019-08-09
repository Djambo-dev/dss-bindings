package ru.digital.league.x5.sign.bindings.data;

import ru.digital.league.x5.sign.bindings.dto.EmployeeBindingDto;
import ru.digital.league.x5.sign.bindings.dto.EmployeeBindingInfoDto;
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

    public static EmployeeBindingInfoDto employeeBindingInfoDto() {

        List<EmployeeBindingDto> employeeBindingDtos = Arrays.asList(employeeBindingDto1(), employeeBindingDto2());

        return EmployeeBindingInfoDto.builder()
                .employeeBindings(employeeBindingDtos)
                .build();
    }

    public static EmployeeBindingInfoDto emptyEmployeeBindingInfoDto() {
        return EmployeeBindingInfoDto.builder()
                .employeeBindings(Collections.emptyList())
                .build();
    }

    public static EmployeeBindingDto employeeBindingDto1() {
        return EmployeeBindingDto.builder()
                .cfoId("E1007345")
                .personalNumber(961135L)
                .personalLogin("EKATERINA.PARUBOK")
                .positionId(50000566L)
                .positionName("Директор")
                .build();
    }

    public static EmployeeBindingDto employeeBindingDto2() {
        return EmployeeBindingDto.builder()
                .cfoId("E11111111")
                .personalNumber(323445L)
                .personalLogin("ILYA.MADDYSON")
                .positionId(50000566L)
                .positionName("Супервайзер")
                .build();
    }
}