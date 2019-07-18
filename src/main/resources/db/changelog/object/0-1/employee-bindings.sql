--liquibase formatted sql

--changeSet vsaydumarov:employee_bindings-1 logicalFilePath:db/changelog/object/0-1
CREATE TABLE bindings.employee_bindings
(
    employee_binding_id BIGINT,
    cfo_id              VARCHAR(50),
    position_id         BIGINT,
    position_name       VARCHAR(50),
    personal_number     BIGINT,
    personal_login      VARCHAR(50),
    creation_date       TIMESTAMP,
    modified_date       TIMESTAMP
);

ALTER TABLE bindings.employee_bindings
    ADD CONSTRAINT employee_bindings_pk PRIMARY KEY (employee_binding_id);

CREATE SEQUENCE bindings.employee_bindings_seq START WITH 1 INCREMENT BY 1;

COMMENT ON TABLE bindings.employee_bindings IS 'Привязки сотрудников';

COMMENT ON COLUMN bindings.employee_bindings.employee_binding_id IS 'Уникальный ID записи';
COMMENT ON COLUMN bindings.employee_bindings.cfo_id IS 'Код ЦФО (Центра Фининансовой Ответственности)';
COMMENT ON COLUMN bindings.employee_bindings.position_id IS 'Код должности';
COMMENT ON COLUMN bindings.employee_bindings.position_name IS 'Наименование должности';
COMMENT ON COLUMN bindings.employee_bindings.personal_number IS 'Табельный номер сотрудника';
COMMENT ON COLUMN bindings.employee_bindings.personal_login IS 'Логин сотрудника в AD';