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
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.digital.league.x5.sign.bindings.data.ClusterEmployeeData.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ModelMapperConfig.class})
public class ClusterEmployeeServiceImplTest {

    @Mock
    private ClusterEmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ClusterEmployeeService employeeService;

    private ClusterEmployeeList clusterEmployeeList;
    private ClusterEmployeeList emptyClusterEmployeeList;
    private ClusterEmployeeList clusterEmployeeListWithNull;
    private ClusterEmployeeList clusterEmployeeListWithNull_and_CorrectValue;

    @Before
    public void setUp() {
        employeeService = new ClusterEmployeeServiceImpl(employeeRepository, modelMapper);
        clusterEmployeeList = clusterEmployeeList();
        emptyClusterEmployeeList = emptyClusterEmployeeList();
        clusterEmployeeListWithNull = clusterEmployeeListWithInvalidValue();
        clusterEmployeeListWithNull_and_CorrectValue = clusterEmployeeListWithInvalidValue_and_CorrectValue();
    }

    /**
     *  Проверяем сохранение сущностей СlusterEmployeeEntity (case: обычный случай)
     *  Результат: корректная обработка входящих данных
     * */

    @Test
    public void save() {
        // вызов
        employeeService.save(clusterEmployeeList);
        // проверка
        verify(employeeRepository, times(1)).deleteAllByClusterIdIn(List.of("0000"));
        verify(employeeRepository, times(1)).saveAll(clusterEmployeeEntityList1());
        verify(employeeRepository, times(0)).markAsDeletedByCfoId(null);
    }

    /**
     *  Проверяем сохранение сущностей ClusterEmployeeEntity (case: выгрузка, содержащая пустой список привязок)
     *  Результат: пропуск методов репозитория на сохранение и удаление
     * */

    @Test
    public void save_emptyList() {
        //подготовка
        List<String> clusterIds = List.of(emptyClusterEmployeeList.getClusterEmployeeBindingList().get(0).getClusterId());
        // вызов
        employeeService.save(emptyClusterEmployeeList);
        // проверка
        verify(employeeRepository, times(0)).deleteAllByClusterIdIn(null);
        verify(employeeRepository, times(0)).saveAll(null);
        verify(employeeRepository, times(0)).markAsDeletedByCfoId(null);
    }

    /**
     *  Проверяем сохранение сущности ClusterEmployeeEntity (case: наличие null в полях сущности)
     *  Результат: сохранение отсутствует
     * */

    @Test
    public void save_emptyPersonalNUmber() {
        List<String> clusterIds =
                List.of(clusterEmployeeListWithNull.getClusterEmployeeBindingList().get(0).getClusterId());
        // вызов
        employeeService.save(clusterEmployeeListWithNull);
        // проверка
        verify(employeeRepository, times(0)).deleteAllByClusterIdIn(anyList());
        verify(employeeRepository, times(0)).saveAll(anyList());
        verify(employeeRepository, times(1)).markAsDeletedByCfoId(clusterIds);
    }

    /**
     *  Проверяем сохранение сущностей ClusterEmployeeEntity (case: наличие null полей в одной сущности)
     *  Результат : сохранение 1 из 2-х сущностей
     * */

    @Test
    public void save_listWithNull_and_withoutNull() {
        // подготовка
        List<String> clusterIdsToDelete =
                List.of(clusterEmployeeListWithNull_and_CorrectValue.getClusterEmployeeBindingList().get(0).getClusterId());
        List<String> clusterIdsToUpdate =
                List.of(clusterEmployeeListWithNull_and_CorrectValue.getClusterEmployeeBindingList().get(1).getClusterId());
        // вызов
        employeeService.save(clusterEmployeeListWithNull_and_CorrectValue);
        // проверка
        verify(employeeRepository, times(1)).deleteAllByClusterIdIn(clusterIdsToUpdate);
        verify(employeeRepository, times(1)).saveAll(clusterEmployeeEntityList1());
        verify(employeeRepository, times(1)).markAsDeletedByCfoId(clusterIdsToDelete);
    }


}
