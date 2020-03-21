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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
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
    private Long personalNumber;
    private String mdmStoreId;
    private StoreEntity storeEntity;
    private List<StoreEntity> storeEntities;
    private List<StoreDto> storeDtos;

    @Before
    public void setUp() {
        storeService = new StoreServiceImpl(storeRepository, modelMapper);

        storeInfoDto = TestData.storeInfoDto();
        emptyStoreInfoDto = TestData.emptyStoreInfoDto();
        personalNumber = 111L;
        storeDtos = Arrays.asList(TestData.storeDto1(), TestData.storeDto2());
        storeEntities = storeDtos.stream()
                .map(storeDto -> modelMapper.map(storeDto, StoreEntity.class))
                .collect(Collectors.toList());
        mdmStoreId = "3402";
        System.out.println("setup storeDto = " + TestData.storeDto1());
        System.out.println(modelMapper.getConfiguration().getConverters());
        storeEntity = modelMapper.map(TestData.storeDto1(), StoreEntity.class);
        System.out.println("setup = " + storeEntity);
    }

    /**
     * Проверяем наличие и количество вызовов необходимых сервисов при сохранении данных о магазине в БД
     */
    @Test
    public void save() {
        storeService.save(storeInfoDto);
        verify(storeRepository, times(1)).saveAll(anyList());
    }

    /**
     * Проверяем что при пустом списке магазинов - запросы на сохранение в БД не отправляются
     */
    @Test
    public void saveEmpty() {
        storeService.save(emptyStoreInfoDto);
        verify(storeRepository, times(0)).saveAll(anyList());
    }

    @Test
    public void getStoresByPersonalNumber() {
        // подготовка
        when(storeRepository.findAllByPersonalNumber(personalNumber)).thenReturn(storeEntities);

        //вызов
        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        //проверка
        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber);
        assertEquals(storesByPersonalNumber, storeDtos);

    }

    @Test
    public void getStoreByStoreId() {
        // подготовка
        when(storeRepository.findByStoreKeyMdmStoreId(mdmStoreId)).thenReturn(storeEntity);

        //вызов
        StoreDto storeByStoreId = storeService.getStoreByStoreId(mdmStoreId);

        //проверка
        verify(storeRepository, times(1)).findByStoreKeyMdmStoreId(mdmStoreId);
        assertEquals(TestData.storeDto1(), storeByStoreId);
    }
}
