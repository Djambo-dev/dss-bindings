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
import ru.digital.league.x5.sign.bindings.data.StoreData;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.db.repository.StoreRepository;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
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

    private String personalNumber = "111";
    private String mdmStoreId = "3402";
    private Integer intervalDays = 30;
    private Integer outOfIntervalDays = 40;
    private LocalDate fakeNowDate = LocalDate.of(2021,2,1 );
    private List<Long> positionIdList = List.of(5_0000_741L, 5_0000_686L);

    @Before
    public void setUp() {
        storeService = new StoreServiceImpl(positionIdList, intervalDays, storeRepository, modelMapper);
        storeInfoDto = StoreData.storeInfoDto();
        emptyStoreInfoDto = StoreData.emptyStoreInfoDto();
        storeDtos = Arrays.asList(StoreData.storeDto1(), StoreData.storeDto2(), StoreData.storeDto3());
    }

    /**
     * Проверяем наличие и количество вызовов необходимых сервисов при сохранении данных о магазине в БД
     */
    @Test
    public void saveStore_success() {
        //вызов
        storeService.save(storeInfoDto);

        //проверка
        verify(storeRepository, times(1)).saveAll(anyList());
    }

    /**
     * Проверяем что при пустом списке магазинов - запросы на сохранение в БД не отправляются
     */
    @Test
    public void saveStore_emptyList() {
        //вызов
        storeService.save(emptyStoreInfoDto);

        //проверка
        verify(storeRepository, times(0)).saveAll(anyList());
    }

    /**
     * Проверяем запрос на поиск магазинов по табельному номеру (обычный сотрудник)
     * 1 магазин найден
     */
    @Test
    public void getStoresByPersonalNumber() {
        System.out.println("getStoresByPersonalNumber");
        // подготовка
        List<StoreEntity> storeEntities = List.of(modelMapper.map(StoreData.storeDto1(), StoreEntity.class));
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntities);
        when(storeRepository.findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(Collections.emptyList());

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber, intervalDays, positionIdList);
        verify(storeRepository, times(1)).findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList);
        assertEquals(List.of(StoreData.storeDto1()), storesByPersonalNumber);

    }

    /**
     * Проверяем запрос на поиск магазинов по табельному номеру (обычный сотрудник)
     * 2 магазина найдено
     */
    @Test
    public void getStoresByPersonalNumber_severalStores() {
        // подготовка
        List<StoreEntity> storeEntities = storeDtos.stream()
                .map(storeDto -> modelMapper.map(storeDto, StoreEntity.class))
                .collect(Collectors.toList());
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntities);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber, intervalDays, positionIdList);
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
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntities);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber, intervalDays, positionIdList);
        assertEquals(Collections.EMPTY_LIST, storesByPersonalNumber);

    }

    /**
     * Проверяем поиск магазина по его mdm|sap id
     * Магазин найден
     */
    @Test
    public void getStoreByStoreId_success() {
        // подготовка
        StoreEntity storeEntity = modelMapper.map(StoreData.storeDto1(), StoreEntity.class);
        when(storeRepository.findByStoreKeyMdmStoreId(mdmStoreId)).thenReturn(storeEntity);

        //вызов
        StoreDto storeByStoreId = storeService.getStoreByStoreId(mdmStoreId);

        //проверка
        verify(storeRepository, times(1)).findByStoreKeyMdmStoreId(mdmStoreId);
        assertEquals(StoreData.storeDto1(), storeByStoreId);
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

    /**
     * Проверяем запрос на поиск магазинов по табельному номеру (НОО)
     * 1 магазин найден
     */
    @Test
    public void getStoresByPersonalNumber_NOO_oneStore() {
        // подготовка
        List<StoreEntity> storeEntitiesCluster = List.of(modelMapper.map(StoreData.storeDto2(), StoreEntity.class));
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(Collections.emptyList());
        when(storeRepository.findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntitiesCluster);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber, intervalDays, positionIdList);
        verify(storeRepository, times(1)).findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList);
        assertEquals(List.of(StoreData.storeDto2()), storesByPersonalNumber);

    }

    /**
     * Проверяем запрос на поиск магазинов по табельному номеру (НОО)
     * 2 магазина найдено
     */
    @Test
    public void getStoresByPersonalNumber_NOO_twoStores() {
        // подготовка
        List<StoreEntity> storeEntities = storeDtos.stream()
                .map(storeDto -> modelMapper.map(storeDto, StoreEntity.class))
                .collect(Collectors.toList());
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(Collections.emptyList());
        when(storeRepository.findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntities);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber, intervalDays, positionIdList);
        verify(storeRepository, times(1)).findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList);
        assertEquals(storeDtos, storesByPersonalNumber);

    }

    /**
     * Проверяем запрос на поиск магазинов по табельному номеру (НОО+совместитель )
     * 2 магазина найдено
     */
    @Test
    public void getStoresByPersonalNumber_NOOandDM_twoStores() {
        // подготовка
        List<StoreEntity> storeEntities = List.of(modelMapper.map(StoreData.storeDto1(), StoreEntity.class));
        List<StoreEntity> storeEntitiesCluster = List.of(modelMapper.map(StoreData.storeDto2(), StoreEntity.class));
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntities);
        when(storeRepository.findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntitiesCluster);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber, intervalDays, positionIdList);
        verify(storeRepository, times(1)).findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList);
        assertEquals(List.of(StoreData.storeDto1(), StoreData.storeDto2()), storesByPersonalNumber);

    }

    /**
     * Проверяем запрос на поиск магазинов, которые попадают в указанный интервал времени
     */

    @Test
    public void getStoresByPersonalNumber_InIntervalDaysForClosedShop_success() {
        // подготовка
        List<StoreEntity> storeEntitiesWithClosedDay = List.of(modelMapper.map(StoreData.storeDto3(), StoreEntity.class));
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntitiesWithClosedDay);
        when(storeRepository.findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(Collections.emptyList());

        //вызов
        LocalDate closeDate = storeService.getStoresByPersonalNumber(personalNumber)
                .get(0)
                .getCloseDate();

        //проверка
        assertTrue(fakeNowDate.isAfter(closeDate.plusDays(intervalDays)));
    }

    /**
     * Проверяем запрос на поиск магазинов, которые не попадают в указанный интервал времени
     */

    @Test
    public void getStoresByPersonalNumber_NOT_InIntervalDaysForClosedShop_success() {
        // подготовка
        List<StoreEntity> storeEntitiesWithClosedDay = List.of(modelMapper.map(StoreData.storeDto3(), StoreEntity.class));
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntitiesWithClosedDay);
        when(storeRepository.findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(Collections.emptyList());

        //вызов
        LocalDate closeDate = storeService.getStoresByPersonalNumber(personalNumber)
                .get(0)
                .getCloseDate();

        //проверка
        assertFalse(fakeNowDate.isAfter(closeDate.plusDays(outOfIntervalDays)));
    }

    /**
     * Проверяем запрос на поиск магазинов, которые попадают в указанный интервал времени (НОО)
     */

    @Test
    public void getStoresByPersonalNumber_NOO_InIntervalDaysForClosedShop_success() {
        // подготовка
        List<StoreEntity> storeEntitiesWithClosedDay = List.of(modelMapper.map(StoreData.storeDto3(), StoreEntity.class));
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(Collections.emptyList());
        when(storeRepository.findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntitiesWithClosedDay);

        //вызов
        LocalDate closeDate = storeService.getStoresByPersonalNumber(personalNumber)
                .get(0)
                .getCloseDate();

        //проверка
        assertTrue(fakeNowDate.isAfter(closeDate.plusDays(intervalDays)));
    }

    /**
     * Проверяем запрос на поиск магазинов, которые не попадают в указанный интервал времени (НОО)
     */

    @Test
    public void getStoresByPersonalNumber_NOO_NOT_InIntervalDaysForClosedShop_success() {
        // подготовка
        List<StoreEntity> storeEntitiesWithClosedDay = List.of(modelMapper.map(StoreData.storeDto3(), StoreEntity.class));
        when(storeRepository.findAllByPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(Collections.emptyList());
        when(storeRepository.findAllByClusterPersonalNumber(personalNumber, intervalDays, positionIdList)).thenReturn(storeEntitiesWithClosedDay);

        //вызов
        LocalDate closeDate = storeService.getStoresByPersonalNumber(personalNumber)
                .get(0)
                .getCloseDate();

        //проверка
        assertFalse(fakeNowDate.isAfter(closeDate.plusDays(outOfIntervalDays)));
    }
}
