package ru.digital.league.x5.sign.bindings.xml.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Выгрузка по привязкам сотрудников к ЦФО (Центру Финансовой Ответственности)
 */
@Component
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mt_mdm_ecp_cfo", namespace = "http://x5.ru/mdm")
public class EmployeeList {

    /**
     * Данные привязок сотрудников
     */
    @XmlElement(name = "Item", namespace = "http://www.ibm.com/mdm/entry01")
    private List<EmployeeBinding> employeeBindings;

}
