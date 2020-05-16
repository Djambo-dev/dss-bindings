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
import ru.digital.league.x5.sign.bindings.dto.EmployeeInfoDto;

import java.util.List;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.digital.league.x5.sign.bindings.data.TestData.employeeEntityList1;
import static ru.digital.league.x5.sign.bindings.data.TestData.employeeInfoDto;
import static ru.digital.league.x5.sign.bindings.data.TestData.employeeInfoDtoWithNull;
import static ru.digital.league.x5.sign.bindings.data.TestData.emptyEmployeeInfoDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ModelMapperConfig.class})
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private EmployeeService employeeService;

    private EmployeeInfoDto employeeInfoDto;
    private EmployeeInfoDto emptyEmployeeInfoDto;
    private EmployeeInfoDto employeeInfoDtoWithNull;

    @Before
    public void setUp() {
        // вызов
        employeeService = new EmployeeServiceImpl(employeeRepository, modelMapper);
        // проверка
        employeeInfoDto = employeeInfoDto();
        emptyEmployeeInfoDto = emptyEmployeeInfoDto();
        employeeInfoDtoWithNull = employeeInfoDtoWithNull();
    }

    @Test
    public void save() {
        // вызов
        employeeService.save(employeeInfoDto);
        // проверка
        verify(employeeRepository, times(1)).deleteAllByCfoIdIn(anyList());
        verify(employeeRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void save_emptyList() {
        // вызов
        employeeService.save(emptyEmployeeInfoDto);
        // проверка
        verify(employeeRepository, times(0)).deleteAllByCfoIdIn(anyList());
        verify(employeeRepository, times(0)).saveAll(anyList());
    }

    @Test
    public void save_listWithoutNull() {
        // подготовка
        when(employeeRepository.saveAll(employeeEntityList1())).thenReturn(employeeEntityList1());
        // вызов
        employeeService.save(employeeInfoDtoWithNull);
        // проверка
        verify(employeeRepository, times(1)).deleteAllByCfoIdIn(List.of("E1007345"));
        verify(employeeRepository, times(1)).saveAll(employeeEntityList1());
    }

}
