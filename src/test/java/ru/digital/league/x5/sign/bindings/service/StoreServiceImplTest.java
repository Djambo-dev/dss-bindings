package ru.digital.league.x5.sign.bindings.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.digital.league.x5.sign.bindings.config.ModelMapperConfig;
import ru.digital.league.x5.sign.bindings.data.TestData;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.db.repository.StoreRepository;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ModelMapperConfig.class})
public class StoreServiceImplTest {

    @Mock
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private StoreService storeService;

    private StoreInfoDto storeInfoDto;
    private StoreInfoDto emptyStoreInfoDto;
    private List<StoreDto> storeDtos;

    private Long personalNumber = 111L;
    private String mdmStoreId = "3402";

    @Before
    public void setUp() {
        storeService = new StoreServiceImpl(storeRepository, modelMapper);
        storeInfoDto = TestData.storeInfoDto();
        emptyStoreInfoDto = TestData.emptyStoreInfoDto();
        storeDtos = Arrays.asList(TestData.storeDto1(), TestData.storeDto2());
    }

    /**
     * Проверяем наличие и количество вызовов необходимых сервисов при сохранении данных о магазине в БД
     */
    @Test
    public void saveStore_success() {
        storeService.save(storeInfoDto);
        verify(storeRepository, times(1)).saveAll(anyList());
    }

    /**
     * Проверяем что при пустом списке магазинов - запросы на сохранение в БД не отправляются
     */
    @Test
    public void saveStore_emptyList() {
        storeService.save(emptyStoreInfoDto);
        verify(storeRepository, times(0)).saveAll(anyList());
    }

    /**
     * Проверяем запрос на поиск магазинов по табельному номеру
     * 1 магазин найден
     */
    @Test
    public void getStoresByPersonalNumber() {
        // подготовка
        List<StoreEntity> storeEntities = List.of(modelMapper.map(TestData.storeDto1(), StoreEntity.class));
        when(storeRepository.findAllByPersonalNumber(personalNumber)).thenReturn(storeEntities);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber);
        assertEquals(List.of(TestData.storeDto1()), storesByPersonalNumber);

    }

    /**
     * Проверяем запрос на поиск магазинов по табельному номеру
     * 2 магазина найдено
     */
    @Test
    public void getStoresByPersonalNumber_severalStores() {
        // подготовка
        List<StoreEntity> storeEntities = storeDtos.stream()
                .map(storeDto -> modelMapper.map(storeDto, StoreEntity.class))
                .collect(Collectors.toList());
        when(storeRepository.findAllByPersonalNumber(personalNumber)).thenReturn(storeEntities);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber);
        assertEquals(storeDtos, storesByPersonalNumber);

    }

    /**
     * Проверяем запрос на поиск магазинов по табельному номеру
     * Магазины не найдены
     */
    @Test
    public void getStoresByPersonalNumber_storesNotFound() {
        // подготовка
        List<StoreEntity> storeEntities = new ArrayList<>();
        when(storeRepository.findAllByPersonalNumber(personalNumber)).thenReturn(storeEntities);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber);
        assertEquals(Collections.EMPTY_LIST, storesByPersonalNumber);

    }

    /**
     * Проверяем поиск магазина по его mdm|sap id
     * Магазин найден
     */
    @Test
    public void getStoreByStoreId_success() {
        // подготовка
        StoreEntity storeEntity = modelMapper.map(TestData.storeDto1(), StoreEntity.class);
        when(storeRepository.findByStoreKeyMdmStoreId(mdmStoreId)).thenReturn(storeEntity);

        //вызов
        StoreDto storeByStoreId = storeService.getStoreByStoreId(mdmStoreId);

        //проверка
        verify(storeRepository, times(1)).findByStoreKeyMdmStoreId(mdmStoreId);
        assertEquals(TestData.storeDto1(), storeByStoreId);
    }

    /**
     * Проверяем поиск магазина по его mdm|sap id
     * Магазин не найден
     */
    @Test
    public void getStoreByStoreId_notFound() {
        // подготовка
        when(storeRepository.findByStoreKeyMdmStoreId(mdmStoreId)).thenReturn(null);

        //вызов
        StoreDto storeByStoreId = storeService.getStoreByStoreId(mdmStoreId);

        //проверка
        verify(storeRepository, times(1)).findByStoreKeyMdmStoreId(mdmStoreId);
        assertNull(storeByStoreId);
    }
}
