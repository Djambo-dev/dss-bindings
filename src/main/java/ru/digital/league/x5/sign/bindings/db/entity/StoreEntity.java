package ru.digital.league.x5.sign.bindings.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(schema = "bindings", name = "stores")
public class StoreEntity extends BindingEntity {

    @Id
    @Column(name = "mdm_store_id")
    private String mdmStoreId;

    @Column(name = "cfo_id")
    private String cfoId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "cluster_id")
    private String clusterId;

    @Column(name = "open_date")
    private LocalDate openDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        StoreEntity se = (StoreEntity) obj;
        return this.getMdmStoreId().equals(se.getMdmStoreId());
    }
}

