package ru.digital.league.x5.sign.bindings.xml.model;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Данные сотрудника, привязанного к ЦФО (Центру Финансовой Ответственности)
 * Пояснение по табельным номерам: сотрудник может совмещать свою основную работу (например, директор магазина X)
 * c другой работой (например, директор магазина Y). Такие сотрудники называются совместителями. Несмотря на то, что
 * физически человек один, в БД привязок сервиса dss-bindings у него будет 2 записи в таблице о сотрудниках
 * и 2 табельных номера. В одной записи о сотруднике в поле personalNumber будет указан основной табельный номер
 * - по основному месту работы (директор магазина X) и привязана эта запись будет к магазину X, а другая запись
 * о сотруднике будет содержать в поле personalNumber табельный номер совместителя (директор магазина Y). Получается,
 * что когда совместитель авторизуется на портале, то он должен увидеть документы магазинов, в которых он работает
 * совместителяем. Т.к. при авторизации мы знаем только основной табельный номер сотрудника, то для того чтобы показать
 * сотруднику документы всех магазинов (по основной и совмещаемой работе) добавляется поле linkedPersonalNumber,
 * в котором для совмещаемой должности будет указан основной табельный номер.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SHOP_PERNR")
public class Employee {

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
     * Если это поле инициализировано, то оно содержит основной табельный номер,
     * а personalNumber - это табельный номер совместителя
     */
    @XmlElement(name = "HPERN", namespace = "http://www.ibm.com/mdm/entry01")
    private String linkedPersonalNumber;

}
