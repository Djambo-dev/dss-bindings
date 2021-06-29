package ru.digital.league.x5.sign.bindings.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.digital.league.x5.sign.bindings.config.JaxbConfig;
import ru.digital.league.x5.sign.bindings.exception.JaxbValidationException;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployee;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeBinding;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.Employee;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeBinding;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.Store;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static ru.digital.league.x5.sign.bindings.data.MarshallingData.getClusterEmployeeCorrectMessage;
import static ru.digital.league.x5.sign.bindings.data.MarshallingData.getClusterEmployeeIncorrectMessage;
import static ru.digital.league.x5.sign.bindings.data.MarshallingData.getClusterEmployeeWithCloseTagMessage;
import static ru.digital.league.x5.sign.bindings.data.MarshallingData.getEmployeeCorrectMessage;
import static ru.digital.league.x5.sign.bindings.data.MarshallingData.getEmployeeIncorrectMessage;
import static ru.digital.league.x5.sign.bindings.data.MarshallingData.getEmployeeWithCloseTagMessage;
import static ru.digital.league.x5.sign.bindings.data.MarshallingData.getStoreInfoCorrectMessage;
import static ru.digital.league.x5.sign.bindings.data.MarshallingData.getStoreInfoIncorrectMessage;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JaxbConfig.class})
public class MarshallingServiceImplTest {

    @Autowired
    private Jaxb2Marshaller storeMarshaller;
    @Autowired
    private Jaxb2Marshaller employeeMarshaller;
    @Autowired
    private Jaxb2Marshaller clusterEmployeeMarshaller;

    @Mock
    private Jaxb2MarshallerCreator creator;

    private MarshallingService marshallingService;

    @Before
    public void setUp() {
        marshallingService = new MarshallingServiceImpl(creator);
    }

    /**
     *
     * Проверка некорректного сообщения, содрежащее сведения о магазине.
     * Выброс исключения JaxbValidationException. Причина: елемент CFO не должен содержать дочерних элементов
     *
     * */

    @Test(expected = JaxbValidationException.class)
    public void expectValidationExceptionForIncorrectStoreMessage() {
        when(creator.getStoreMarshaller()).thenReturn(storeMarshaller);
        marshallingService.getUnmarshalStores(getStoreInfoIncorrectMessage());
    }

    /**
     *
     * Проверка некорректного сообщения, содрежащее сведения о сотруднике.
     * Выброс исключения JaxbValidationException. Причина: елемент PERNR не должен содержать дочерних элементов
     *
     * */

    @Test(expected = JaxbValidationException.class)
    public void expectValidationExceptionForIncorrectEmployeeMessage() {
        when(creator.getEmployeeMarshaller()).thenReturn(employeeMarshaller);
        marshallingService.getUnmarshalEmployee(getEmployeeIncorrectMessage());
    }

    /**
     *
     * Проверка некорректного сообщения, содрежащее сведения о кластерном сотруднике.
     * Выброс исключения JaxbValidationException. Причина: елемент PERNR не должен содержать дочерних элементов
     *
     * */

    @Test(expected = JaxbValidationException.class)
    public void expectValidationExceptionForIncorrectClusterEmployeeMessage(){
        when(creator.getClusterEmployeeMarshaller()).thenReturn(clusterEmployeeMarshaller);
        marshallingService.getUnmarshalClusterEmployee(getClusterEmployeeIncorrectMessage());
    }

    /**
     *
     * Проверка корректного сообщения, содержащее сведения о магазине.
     * Валидация ключевых полей 1 магазина: mdmStoreId, cfoId, clusterId
     *
     * */

    @Test
    public void checkCorrectStoreMessage() {
        when(creator.getStoreMarshaller()).thenReturn(storeMarshaller);

        StoreInfo unmarshalStores = marshallingService.getUnmarshalStores(getStoreInfoCorrectMessage());
        Store store = unmarshalStores.getStores().get(0);

        assertEquals("501V", store.getMdmStoreId());
        assertEquals("E1007356", store.getCfoId());
        assertEquals("4491", store.getClusterId());
    }

    /**
     *
     * Проверка корректного сообщения, содержащее сведения о сотруднике.
     * Валидация ключевых полей 1 сотрудника: personalNumber, personalLogin, cfoId
     *
     * */

    @Test
    public void checkCorrectEmployeeMessage() {
        when(creator.getEmployeeMarshaller()).thenReturn(employeeMarshaller);

        EmployeeList unmarshalEmployee = marshallingService.getUnmarshalEmployee(getEmployeeCorrectMessage());
        EmployeeBinding employeeBinding = unmarshalEmployee.getEmployeeBindings().get(0);
        Employee employee = employeeBinding.getEmployeeBindings().get(0);

        assertEquals("EX046976", employeeBinding.getCfoId());
        assertEquals("555452", employee.getPersonalNumber());
        assertEquals("NATALIA.BIRICHOVA", employee.getPersonalLogin());
    }

    /**
     *
     * Проверка корректного сообщения, содержащее сведения о кластерном сотруднике.
     * Валидация ключевых полей 1 кластерного сотрудника: personalNumber, personalLogin, clusterId, positionId
     *
     * */

    @Test
    public void checkCorrectClusterEmployeeMessage(){
        when(creator.getClusterEmployeeMarshaller()).thenReturn(clusterEmployeeMarshaller);

        ClusterEmployeeList unmarshalClusterEmployee = marshallingService.getUnmarshalClusterEmployee(getClusterEmployeeCorrectMessage());
        ClusterEmployeeBinding clusterEmployeeBinding = unmarshalClusterEmployee.getClusterEmployeeBindingList().get(0);
        ClusterEmployee clusterEmployee = clusterEmployeeBinding.getClusterEmployeeList().get(0);

        assertEquals("9992", clusterEmployeeBinding.getClusterId());
        assertEquals("616393", clusterEmployee.getPersonalNumber());
        assertEquals("MIKHAIL.PANOV", clusterEmployee.getPersonalLogin());
        assertEquals(Long.valueOf(52036679), clusterEmployee.getPositionId());
    }


    /**
     * Проверка сообщения, содрежащее закрытый ключевой элемент CLS_PERNR
     * Поля кластерного сотрудника - null, ошибка валидации не вощникает
     *
     * */

    @Test
    public void expectValidationExceptionForWithCloseTagClusterEmployeeMessage(){
        when(creator.getClusterEmployeeMarshaller()).thenReturn(clusterEmployeeMarshaller);
        ClusterEmployeeList unmarshalClusterEmployee = marshallingService.getUnmarshalClusterEmployee(getClusterEmployeeWithCloseTagMessage());
        ClusterEmployeeBinding clusterEmployeeBinding = unmarshalClusterEmployee.getClusterEmployeeBindingList().get(0);
        ClusterEmployee clusterEmployee = clusterEmployeeBinding.getClusterEmployeeList().get(0);
        assertEquals("9992", clusterEmployeeBinding.getClusterId());
        assertEquals(null, clusterEmployee.getPersonalNumber());
        assertEquals(null, clusterEmployee.getPersonalLogin());
        assertEquals(null, clusterEmployee.getPositionId());
    }


    /**
     * Проверка сообщения, содрежащее закрытый ключевой элемент SHOP_PERNR
     * Поля сотрудника - null, ошибка валидации не вощникает
     *
     * */

    @Test
    public void expectValidationExceptionForWithCloseTagEmployeeMessage(){
        when(creator.getEmployeeMarshaller()).thenReturn(employeeMarshaller);
        EmployeeList unmarshalEmployee = marshallingService.getUnmarshalEmployee(getEmployeeWithCloseTagMessage());
        EmployeeBinding employeeBindings = unmarshalEmployee.getEmployeeBindings().get(0);
        Employee employee = employeeBindings.getEmployeeBindings().get(0);

        assertEquals("EX046976", employeeBindings.getCfoId());
        assertEquals(null, employee.getPersonalNumber());
        assertEquals(null, employee.getPersonalLogin());
        assertEquals(null, employee.getPositionId());
    }
}
