package ru.digital.league.x5.sign.bindings.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@MappedSuperclass
public abstract class BindingEntity {

    @Column(name = "modified_date")
    private LocalDateTime lastUpdateDate;

//    @PrePersist
//    public void prePersist() {
//        this.lastUpdateDate = this.creationDate;
//    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
    }

}
