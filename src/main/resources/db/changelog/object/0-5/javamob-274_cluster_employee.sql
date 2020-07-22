--liquibase formatted sql

--changeSet St.Yakovlev:javamob-274 logicalFilePath:db/changelog/object/0-5/javamob-274_cluster_employee.sql
CREATE TABLE IF NOT EXISTS bindings.cluster_employee
(
    employee_id         BIGINT,
    cluster_id          VARCHAR(50),
    position_id         BIGINT,
    position_name       VARCHAR(50),
    personal_number     VARCHAR(20),
    personal_login      VARCHAR(50),
    full_name           VARCHAR(255),
    role                VARCHAR(255),
    email               VARCHAR(100),
    modified_date       TIMESTAMP,
    pt_personal_number  VARCHAR(20)
);

SELECT bindings.create_constraint_if_not_exists(
        'bindings',
        'cl_employee_pk',
        'ALTER TABLE bindings.cluster_employee ADD CONSTRAINT cl_employee_pk PRIMARY KEY (employee_id)');

CREATE SEQUENCE IF NOT EXISTS bindings.cl_employee_seq START WITH 1 INCREMENT BY 1;

COMMENT ON TABLE bindings.cluster_employee IS 'Привязки сотрудников кластера';

COMMENT ON COLUMN bindings.cluster_employee.employee_id IS 'Уникальный ID записи';
COMMENT ON COLUMN bindings.cluster_employee.cluster_id IS 'Код кластера';
COMMENT ON COLUMN bindings.cluster_employee.position_id IS 'Код должности';
COMMENT ON COLUMN bindings.cluster_employee.position_name IS 'Наименование должности';
COMMENT ON COLUMN bindings.cluster_employee.personal_number IS 'Табельный номер сотрудника';
COMMENT ON COLUMN bindings.cluster_employee.personal_login IS 'Логин сотрудника в AD';
COMMENT ON COLUMN bindings.cluster_employee.pt_personal_number IS 'Табельный номер совместителя (если есть)';
COMMENT ON COLUMN bindings.cluster_employee.modified_date IS 'Дата и время последней модификации записи';
COMMENT ON COLUMN bindings.cluster_employee.full_name IS 'ФИО сотрудника кластера';
COMMENT ON COLUMN bindings.cluster_employee.role IS 'Роль сотрудника кластера';
COMMENT ON COLUMN bindings.cluster_employee.email IS 'Email сотрудника кластера';