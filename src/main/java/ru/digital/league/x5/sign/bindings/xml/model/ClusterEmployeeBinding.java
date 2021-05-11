package ru.digital.league.x5.sign.bindings.xml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Данные привязки сотрудников к кластеру
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Item")
public class ClusterEmployeeBinding {

    /**
     * Код кластера
     */
    @XmlElement(name = "MDM_ID")
    private String clusterId;

    /**
     * Данные сотрудников, привязанных к кластеру
     */
    @XmlElement(name = "CLS_PERNR")
    private List<ClusterEmployee> clusterEmployeeList;

}
