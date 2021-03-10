package ru.digital.league.x5.sign.bindings.data;

import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StoreData {

    public static StoreInfoDto storeInfoDto() {

        List<StoreDto> storeDtos = Arrays.asList(storeDto1(), storeDto2(), storeDto3());

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
                .clusterId("9999")
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
                .clusterId("8888")
                .address("111111, Москва г, Никольская ул, 1")
                .openDate(LocalDate.of(2011, 1, 1))
                .closeDate(null)
                .build();
    }

    public static StoreDto storeDto3() {
        return StoreDto.builder()
                .mdmStoreId("1111")
                .name("1111-Пятерочка.")
                .cfoId("E11111111")
                .clusterId("8888")
                .address("111111, Москва г, Никольская ул, 1")
                .openDate(LocalDate.of(2011, 1, 1))
                .closeDate(LocalDate.of(2021,1,1))
                .build();
    }

}
