package ru.digital.league.x5.sign.bindings.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(schema = "bindings", name = "employee_bindings")
@SequenceGenerator(schema = "bindings", name = "employee_bindings_seq", sequenceName = "employee_bindings_seq", allocationSize = 1)
public class EmployeeBindingEntity extends BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_bindings_seq")
    @Column(name = "employee_binding_id")
    private Long employeeBindingId;

    @Column(name = "cfo_id")
    private String cfoId;

    @Column(name = "personal_number")
    private Long personalNumber;

    @Column(name = "personal_login")
    private String personalLogin;

    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "position_name")
    private String positionName;

}
