--liquibase formatted sql

--changeSet A.Lambert:alter_table_stores.sql logicalFilePath:db/changelog/object/0-7/alter_table_stores.sql
-- 1. Dropping the original primary key
ALTER TABLE bindings.stores DROP CONSTRAINT pk_stores;
-- 2. Create new constraint
ALTER TABLE bindings.stores ADD CONSTRAINT pk_stores PRIMARY KEY (mdm_store_id);