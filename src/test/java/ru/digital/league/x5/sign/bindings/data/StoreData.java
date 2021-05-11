package ru.digital.league.x5.sign.bindings.data;

import org.assertj.core.util.Lists;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;
import ru.digital.league.x5.sign.bindings.xml.model.Store;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class StoreData {

    public static StoreInfo storeInfo() {
        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setStores((Arrays.asList(store1(), store2(), store3())));
        return storeInfo;
    }

    public static StoreInfo emptyStoreInfo() {
        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setStores((Lists.emptyList()));
        return storeInfo;
    }

    public static Store store1() {
        Store store = new Store();
        store.setMdmStoreId("3402");
        store.setName("4467-Пятерочка.");
        store.setCfoId("E1007345");
        store.setClusterId("9999");
        store.setAddress("184510, Мурманская обл, Мончегорск г, Бредова ул, 30");
        store.setOpenDate(LocalDate.of(2014, 7, 7));
        store.setCloseDate(null);
        return store;
    }

    public static Store store2() {
        Store store = new Store();
        store.setMdmStoreId("1111");
        store.setName("1111-Пятерочка.");
        store.setCfoId("E11111111");
        store.setClusterId("8888");
        store.setAddress("111111, Москва г, Никольская ул, 1");
        store.setOpenDate(LocalDate.of(2011, 1, 1));
        store.setCloseDate(null);
        return store;
    }

    public static Store store3() {
        Store store = new Store();
        store.setMdmStoreId("1111");
        store.setName("1111-Пятерочка.");
        store.setCfoId("E11111111");
        store.setClusterId("8888");
        store.setAddress("111111, Москва г, Никольская ул, 1");
        store.setOpenDate(LocalDate.of(2011, 1, 1));
        store.setCloseDate(LocalDate.of(2021, 1, 1));
        return store;
    }

    public static StoreInfoDto storeInfoDto() {
        List<StoreDto> storeDtos = Arrays.asList(storeDto1(), storeDto2(), storeDto3());
        return StoreInfoDto.builder()
                .stores(storeDtos)
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
                .closeDate(LocalDate.of(2021, 1, 1))
                .build();
    }
}
