package ru.digital.league.x5.sign.bindings.xml.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CLS_PERNR")
public class ClusterEmployee {

    /**
     * Табельный номер сотрудника
     */
    @XmlElement(name = "PERNR", namespace = "http://www.ibm.com/mdm/entry01")
    private String personalNumber;

    /**
     * Логин сотрудника в AD (без указания домена)
     */
    @XmlElement(name = "USRID", namespace = "http://www.ibm.com/mdm/entry01")
    private String personalLogin;

    /**
     * Код должности
     */
    @XmlElement(name = "STELL_ID", namespace = "http://www.ibm.com/mdm/entry01")
    private Long positionId;

    /**
     * Название должности
     */
    @XmlElement(name = "STELL_TXT", namespace = "http://www.ibm.com/mdm/entry01")
    private String positionName;

    /**
     * ФИО сотрудника
     */
    @XmlElement(name = "FIO", namespace = "http://www.ibm.com/mdm/entry01")
    private String fullName;

    /**
     * Название роли
     */
    @XmlElement(name = "ROLE", namespace = "http://www.ibm.com/mdm/entry01")
    private String role;

    /**
     * Название роли
     */
    @XmlElement(name = "EMAIL", namespace = "http://www.ibm.com/mdm/entry01")
    private String email;

    /**
     * Если это поле инициализировано, то оно содержит основной табельный номер,
     * а personalNumber - это табельный номер совместителя
     */
    @XmlElement(name = "HPERN", namespace = "http://www.ibm.com/mdm/entry01")
    private String linkedPersonalNumber;

}
