package ru.digital.league.x5.sign.bindings.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@MappedSuperclass
public abstract class BindingEntity {

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime lastUpdateDate;

}
