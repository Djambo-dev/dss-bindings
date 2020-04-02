--liquibase formatted sql

--changeSet St.Yakovlev:javamob-197 logicalFilePath:db/changelog/object/0-3/javamob-197.sql
ALTER TABLE IF EXISTS bindings.employee_bindings RENAME TO employee;
alter table bindings.employee alter column personal_number TYPE character varying(20);
ALTER SEQUENCE IF EXISTS bindings.employee_bindings_seq RENAME TO employee_seq;
ALTER TABLE bindings.employee ADD COLUMN IF NOT EXISTS pt_personal_number character varying(20);

COMMENT ON COLUMN bindings.employee.pt_personal_number IS 'табельный номер совместителя (если есть)';
COMMENT ON TABLE bindings.employee IS 'Cотрудник с привязкой к ЦФО';

ALTER TABLE bindings.employee RENAME IF EXISTS employee_binding_id TO employee_id;
ALTER TABLE bindings.employee ALTER COLUMN employee_id SET DEFAULT nextval('bindings.employee_seq');
