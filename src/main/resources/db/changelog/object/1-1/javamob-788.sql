--liquibase formatted sql

--changeSet A.Lambert:javamob-788.sql logicalFilePath:db/changelog/object/1-1/javamob-788.sql

-- 1. Add new field is_deleted to employee table
ALTER TABLE bindings.employee ADD COLUMN is_deleted bool default false;

-- 2. Add new field is_deleted to cluster_employee table
ALTER TABLE bindings.cluster_employee ADD COLUMN is_deleted bool default false;

COMMENT ON COLUMN bindings.cluster_employee.is_deleted
    IS 'Флаг открепленной привязки';

COMMENT ON COLUMN bindings.employee.is_deleted
    IS 'Флаг открепленной привязки';