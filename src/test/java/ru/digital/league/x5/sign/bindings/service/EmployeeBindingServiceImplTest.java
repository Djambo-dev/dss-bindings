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
import ru.digital.league.x5.sign.bindings.db.repository.EmployeeBindingRepository;
import ru.digital.league.x5.sign.bindings.dto.EmployeeBindingInfoDto;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ModelMapperConfig.class})
public class EmployeeBindingServiceImplTest {

    @Mock
    private EmployeeBindingRepository employeeBindingRepository;

    @Autowired
    private ModelMapper modelMapper;

    private EmployeeBindingService employeeBindingService;

    private EmployeeBindingInfoDto employeeBindingInfoDto;
    private EmployeeBindingInfoDto emptyEmployeeBindingInfoDto;

    @Before
    public void setUp() {
        employeeBindingService = new EmployeeBindingServiceImpl(employeeBindingRepository, modelMapper);

        employeeBindingInfoDto = TestData.employeeBindingInfoDto();
        emptyEmployeeBindingInfoDto = TestData.emptyEmployeeBindingInfoDto();
    }

    @Test
    public void save() {
        employeeBindingService.save(employeeBindingInfoDto);

        verify(employeeBindingRepository, times(employeeBindingInfoDto.getEmployeeBindings().size())).deleteAllByCfoIdAndPersonalNumber(anyString(), anyLong());
        verify(employeeBindingRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void saveEmpty() {
        employeeBindingService.save(emptyEmployeeBindingInfoDto);

        verify(employeeBindingRepository, times(0)).deleteAllByCfoIdAndPersonalNumber(anyString(), anyLong());
        verify(employeeBindingRepository, times(0)).saveAll(anyList());
    }

}
