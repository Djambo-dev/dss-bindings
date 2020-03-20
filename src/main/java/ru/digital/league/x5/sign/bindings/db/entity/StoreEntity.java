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
//@Builder
@Entity
@Table(schema = "bindings", name = "stores1")
//@SequenceGenerator(schema = "bindings", name = "stores_seq", sequenceName = "stores_seq", allocationSize = 1)
public class StoreEntity extends BindingEntity {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stores_seq")
//    @Column(name = "store_id")
//    private Long storeId;

//    @Column(name = "mdm_store_id")
//    private String mdmStoreId;

    @EmbeddedId
    private StoreKey storeKey;

    @Column(name = "name")
    private String name;

//    @Column(name = "cfo_id")
//    private String cfoId;

    @Column(name = "address")
    private String address;

    @Column(name = "open_date")
    private LocalDate openDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

}

