package ru.digital.league.x5.sign.bindings.xml.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Данные привязки сотрудников к определенному ЦФО (Центру Финансовой Ответственности)
 */
@Component
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Item")
public class EmployeeBinding implements XmlObject{

    /**
     * Код ЦФО
     */
    @XmlElement(name = "MDM_ID")
    private String cfoId;

    /**
     * Данные сотрудников, привязанных к ЦФО (Центру Финансовой Ответственности)
     */
    @XmlElement(name = "SHOP_PERNR")
    private List<Employee> employeeBindings;
}
