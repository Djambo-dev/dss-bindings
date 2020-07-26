package ru.digital.league.x5.sign.bindings.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.digital.league.x5.sign.bindings.config.ModelMapperConfig;
import ru.digital.league.x5.sign.bindings.db.repository.ClusterEmployeeRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.digital.league.x5.sign.bindings.data.ClusterEmployeeData.clusterEmployeeEntityList1;
import static ru.digital.league.x5.sign.bindings.data.ClusterEmployeeData.clusterEmployeeEntityList_forFilter;
import static ru.digital.league.x5.sign.bindings.data.ClusterEmployeeData.clusterEmployeeListDto;
import static ru.digital.league.x5.sign.bindings.data.ClusterEmployeeData.clusterEmployeeListDto_forFilter;
import static ru.digital.league.x5.sign.bindings.data.ClusterEmployeeData.clusterEmployeeWithEmptyPersonalNUmber;
import static ru.digital.league.x5.sign.bindings.data.ClusterEmployeeData.clusterEmployeeWithoutNOO;
import static ru.digital.league.x5.sign.bindings.data.ClusterEmployeeData.emptyClusterEmployeeListDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ModelMapperConfig.class})
public class ClusterEmployeeServiceImplTest {

    @Mock
    private ClusterEmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ClusterEmployeeService employeeService;

    @Before
    public void setUp() {

        employeeService = new ClusterEmployeeServiceImpl(employeeRepository, modelMapper);

    }

    /**
     * Сохраняем список сотрудников кластера - проверяем последовательность запросов
     */
    @Test
    public void save() {
        // вызов
        employeeService.save(clusterEmployeeListDto());

        // проверка
        verify(employeeRepository, times(1)).deleteAllByClusterIdIn(anyList());
        verify(employeeRepository, times(1)).saveAll(anyList());
    }

    /**
     * Проверяем, что при получении изначально пустого списка процесс сохранения не запускается
     */
    @Test
    public void save_emptyList() {
        // вызов
        employeeService.save(emptyClusterEmployeeListDto());

        // проверка
        verify(employeeRepository, times(0)).deleteAllByClusterIdIn(anyList());
        verify(employeeRepository, times(0)).saveAll(anyList());
    }

    /**
     * Проверяем, что при получении пустого списка после фильтрации процесс сохранения не запускается
     */
    @Test
    public void save_emptyListAfterFiltered() {
        // вызов
        employeeService.save(clusterEmployeeWithoutNOO());

        // проверка
        verify(employeeRepository, times(0)).deleteAllByClusterIdIn(anyList());
        verify(employeeRepository, times(0)).saveAll(anyList());
    }

    /**
     * Проверяем, что срабатывает фильтр по НОО, и только сотрудники с ролью НОО сохраняются
     */
    @Test
    public void save_onlyNOO() {
        // подготовка
        when(employeeRepository.saveAll(clusterEmployeeEntityList_forFilter())).thenReturn(clusterEmployeeEntityList_forFilter());

        // вызов
        employeeService.save(clusterEmployeeListDto_forFilter());

        // проверка
        verify(employeeRepository, times(1)).deleteAllByClusterIdIn(List.of("8888"));
        verify(employeeRepository, times(1)).saveAll(clusterEmployeeEntityList1());
    }

    /**
     * Проверяем сохранение сотрудников, у которых табельный номер равен null или является пустой строкой
     */
    @Test
    public void save_emptyPersonalNUmber() {
        // вызов
        employeeService.save(clusterEmployeeWithEmptyPersonalNUmber());

        // проверка
        verify(employeeRepository, times(0)).deleteAllByClusterIdIn(anyList());
        verify(employeeRepository, times(0)).saveAll(anyList());
    }
}
