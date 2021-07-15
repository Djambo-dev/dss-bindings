package ru.digital.league.x5.sign.bindings.db.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Запись о сотруднике
 */
@Setter @Getter @ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(schema = "bindings", name = "cluster_employee")
@SequenceGenerator(schema = "bindings", name = "cl_employee_seq", sequenceName = "cl_employee_seq", allocationSize = 1)
public class ClusterEmployeeEntity extends BindingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cl_employee_seq")
    @Column(name = "employee_id")
    private Long employeeId;

    /**
     * Идентификатор кластера
     */
    @Column(name = "cluster_id")
    private String clusterId;

    /**
     * Основной табельный номер сотрудника
     */
    @Column(name = "personal_number")
    private String personalNumber;

    /**
     * Логин сотрудника в AD
     */
    @Column(name = "personal_login")
    private String personalLogin;

    /**
     * Идентификатор должности сотрудника
     */
    @Column(name = "position_id")
    private Long positionId;

    /**
     * Название должности сотрудника
     */
    @Column(name = "position_name")
    private String positionName;

    /**
     * ФИО сотрудника
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * Название роли
     */
    @Column(name = "role")
    private String role;

    /**
     * Название роли
     */
    @Column(name = "email")
    private String email;

    /**
     * Табельный номер совместителя
     */
    @Column(name = "pt_personal_number")
    private String partTimePersonalNumber;

    /**
     *  Маркер, обозначающий неактуальную привязку
     * */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
