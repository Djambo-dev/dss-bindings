package ru.digital.league.x5.sign.bindings.xml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Выгрузка по магазинам
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mt_mdm_ecp_werk", namespace = "http://x5.ru/mdm")
public class StoreInfo {

    /**
     * Данные магазинов
     */
    @XmlElement(name = "Item", namespace = "http://www.ibm.com/mdm/entry01")
    private List<Store> stores;

}
