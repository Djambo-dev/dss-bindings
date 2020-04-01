--liquibase formatted sql

--changeSet St.Yakovlev:javamob-197 logicalFilePath:db/changelog/object/0-3/javamob-197.sql
ALTER TABLE IF EXISTS bindings.employee_bindings RENAME TO employee;
alter table bindings.employee alter column personal_number TYPE character varying(20);
ALTER SEQUENCE IF EXISTS bindings.employee_bindings_seq RENAME TO employee_seq;
ALTER TABLE bindings.employee ADD COLUMN IF NOT EXISTS pt_personal_number character varying(20);

