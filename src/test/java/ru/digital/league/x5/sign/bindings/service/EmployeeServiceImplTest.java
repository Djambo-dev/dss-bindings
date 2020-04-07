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
import ru.digital.league.x5.sign.bindings.data.TestData;
import ru.digital.league.x5.sign.bindings.db.repository.EmployeeRepository;
import ru.digital.league.x5.sign.bindings.dto.EmployeeInfoDto;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Before
    public void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository, modelMapper);

        employeeInfoDto = TestData.employeeInfoDto();
        emptyEmployeeInfoDto = TestData.emptyEmployeeInfoDto();
    }

    @Test
    public void save() {
        employeeService.save(employeeInfoDto);

        verify(employeeRepository, times(1)).deleteAllByCfoIdIn(anyList());
        verify(employeeRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void saveEmpty() {
        employeeService.save(emptyEmployeeInfoDto);

        verify(employeeRepository, times(0)).deleteAllByCfoIdIn(anyList());
        verify(employeeRepository, times(0)).saveAll(anyList());
    }

}
