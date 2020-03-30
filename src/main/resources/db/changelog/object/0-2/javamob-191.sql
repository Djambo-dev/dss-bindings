--liquibase formatted sql

--changeSet St.Yakovlev:javamob-191 logicalFilePath:db/changelog/object/0-2/javamob-191.sql
ALTER TABLE bindings.stores DROP CONSTRAINT IF EXISTS stores_pk;
ALTER TABLE bindings.stores DROP COLUMN IF EXISTS store_id;

ALTER TABLE bindings.stores DROP COLUMN IF EXISTS creation_date;

DROP SEQUENCE IF EXISTS bindings.stores_seq;

ALTER TABLE bindings.employee_bindings DROP COLUMN IF EXISTS creation_date;

