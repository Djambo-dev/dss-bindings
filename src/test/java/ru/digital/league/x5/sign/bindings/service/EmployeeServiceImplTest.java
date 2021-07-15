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
import ru.digital.league.x5.sign.bindings.db.repository.EmployeeRepository;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.digital.league.x5.sign.bindings.data.EmployeeData.employeeEntityList1;
import static ru.digital.league.x5.sign.bindings.data.EmployeeData.employeeList;
import static ru.digital.league.x5.sign.bindings.data.EmployeeData.employeeListWithNull;
import static ru.digital.league.x5.sign.bindings.data.EmployeeData.employeeListWithNull_and_withoutNull;
import static ru.digital.league.x5.sign.bindings.data.EmployeeData.emptyEmployeeList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ModelMapperConfig.class})
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private EmployeeService employeeService;

    private EmployeeList employeeList;
    private EmployeeList emptyEmployeeList;
    private EmployeeList employeeListWithNull;
    private EmployeeList employeeListWithNull_and_withoutNull;

    @Before
    public void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository, modelMapper);

        employeeList = employeeList();
        emptyEmployeeList = emptyEmployeeList();
        employeeListWithNull = employeeListWithNull();
        employeeListWithNull_and_withoutNull = employeeListWithNull_and_withoutNull();
    }

    /**
     *  Проверяем сохранение сущностей EmployeeEntity (case: обычный случай)
     *  Результат: корректная обработка входящих данных, пометки на удаление нет
     * */

    @Test
    public void save() {
        // вызов
        employeeService.save(employeeList);
        // проверка
        verify(employeeRepository, times(1)).deleteAllByCfoIdIn(List.of("E2221111"));
        verify(employeeRepository, times(1)).saveAll(employeeEntityList1());
        verify(employeeRepository, times(0)).markAsDeletedByCfoId(null);
    }

    /**
     *  Проверяем сохранение сущностей EmployeeEntity (case: выгрузка, содержащая пустой список привязок)
     *  Результат: пропуск методов репозитория на сохранение и удаление
     * */


    @Test
    public void save_emptyList() {
        // вызов
        employeeService.save(emptyEmployeeList);
        // проверка
        verify(employeeRepository, times(0)).deleteAllByCfoIdIn(null);
        verify(employeeRepository, times(0)).saveAll(null);
        verify(employeeRepository, times(0)).markAsDeletedByCfoId(null);
    }

    /**
     *  Проверяем сохранение сущности EmployeeEntity (case: наличие null в полях сущности)
     *  Результат: сохранение отсутствует
     * */

    @Test
    public void save_listWithNull() {
        //подготовка
        List<String> cfoIds = List.of(employeeListWithNull.getEmployeeBindings().get(0).getCfoId());
        // вызов
        employeeService.save(employeeListWithNull);
        // проверка
        verify(employeeRepository, times(0)).deleteAllByCfoIdIn(null);
        verify(employeeRepository, times(0)).saveAll(null);
        verify(employeeRepository, times(1)).markAsDeletedByCfoId(cfoIds);
    }


    /**
     *  Проверяем сохранение сущностей EmployeeEntity (case: наличие null полей в одной сущности)
     *  Результат : сохранение 2 из 3-х сущностей
     * */

    @Test
    public void save_listWithNull_and_withoutNull() {
        //подготовка
        List<String> cfoIdsToDelete = List.of(employeeListWithNull.getEmployeeBindings().get(0).getCfoId());
        List<String> cfoIdsToUpdate = List.of(employeeList.getEmployeeBindings().get(0).getCfoId());
        // вызов
        employeeService.save(employeeListWithNull_and_withoutNull);
        // проверка
        verify(employeeRepository, times(1)).deleteAllByCfoIdIn(cfoIdsToUpdate);
        verify(employeeRepository, times(1)).saveAll(employeeEntityList1());
        verify(employeeRepository, times(1)).markAsDeletedByCfoId(cfoIdsToDelete);
    }
}
