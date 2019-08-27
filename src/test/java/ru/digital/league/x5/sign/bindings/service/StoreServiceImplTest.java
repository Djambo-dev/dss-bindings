package ru.digital.league.x5.sign.bindings.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.digital.league.x5.sign.bindings.data.TestData;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.db.repository.StoreRepository;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ModelMapper.class})
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
        storeEntity = modelMapper.map(TestData.storeDto1(), StoreEntity.class);
    }

    @Test
    public void save() {
        storeService.save(storeInfoDto);

        verify(storeRepository, times(1)).deleteAllByCfoIdIn(anyList());
        verify(storeRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void saveEmpty() {

        storeService.save(emptyStoreInfoDto);

        verify(storeRepository, times(0)).deleteAllByCfoIdIn(anyList());
        verify(storeRepository, times(0)).saveAll(anyList());
    }

    @Test
    public void getStoresByPersonalNumber() {

        when(storeRepository.findAllByPersonalNumber(personalNumber)).thenReturn(storeEntities);

        List<StoreDto> storesByPersonalNumber = storeService.getStoresByPersonalNumber(personalNumber);

        verify(storeRepository, times(1)).findAllByPersonalNumber(personalNumber);

        assertEquals(storesByPersonalNumber, storeDtos);

    }

    @Test
    public void getStoreByStoreId() {
        when(storeRepository.findByMdmStoreId(mdmStoreId)).thenReturn(storeEntity);

        StoreDto storeByStoreId = storeService.getStoreByStoreId(mdmStoreId);

        verify(storeRepository, times(1)).findByMdmStoreId(mdmStoreId);

        assertEquals(storeByStoreId, TestData.storeDto1());
    }
}
