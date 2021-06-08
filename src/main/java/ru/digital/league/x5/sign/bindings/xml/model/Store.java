package ru.digital.league.x5.sign.bindings.xml.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.digital.league.x5.sign.bindings.xml.adapter.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Данные магазина
 */
@Component
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Item")
public class Store {

    /**
     * Код завода SAP
     */
    @XmlElement(name = "MDM_ID")
    private String mdmStoreId;

    /**
     * Название магазина для отображения
     */
    @XmlElement(name = "NAME")
    private String name;

    /**
     * Код ЦФО
     */
    @XmlElement(name = "CFO")
    private String cfoId;

    /**
     * Адрес магазина
     */
    @XmlElement(name = "ADDRESS_TEXT")
    private String address;

    /**
     * Идентификатор кластера
     */
    @XmlElement(name = "CLUSTER")
    private String clusterId;

    /**
     * Дата открытия магазина
     */
    @XmlElement(name = "OPEN_DATE")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate openDate;

    /**
     * Дата закрытия магазина
     */
    @XmlElement(name = "CLOSE_DATE")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate closeDate;

}
