package ru.digital.league.x5.sign.bindings.db.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class StoreKey implements Serializable {

    @Column(name = "mdm_store_id")
    private String mdmStoreId;

    @Column(name = "cfo_id")
    private String cfoId;

    public StoreKey() {
    }

    public StoreKey(String mdmStoreId, String cfoId) {
        this.mdmStoreId = mdmStoreId;
        this.cfoId = cfoId;
    }
}
