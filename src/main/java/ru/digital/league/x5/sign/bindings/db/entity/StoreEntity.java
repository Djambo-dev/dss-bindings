package ru.digital.league.x5.sign.bindings.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.digital.league.x5.sign.bindings.db.key.StoreKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(schema = "bindings", name = "stores")
public class StoreEntity extends BindingEntity {

    @EmbeddedId
    private StoreKey storeKey;

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
        if (this.getStoreKey() == null || se.getStoreKey() == null) {
            return false;
        }

        return this.getStoreKey().getCfoId().equals(se.getStoreKey().getCfoId()) &&
                this.getStoreKey().getMdmStoreId().equals(se.getStoreKey().getMdmStoreId());
    }
}

