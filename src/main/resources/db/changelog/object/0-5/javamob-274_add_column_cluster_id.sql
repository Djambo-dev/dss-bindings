--liquibase formatted sql

--changeSet St.Yakovlev:javamob-274_add_column_cluster_id logicalFilePath:db/changelog/object/0-5/javamob-274_add_column_cluster_id.sql
ALTER TABLE bindings.stores ADD COLUMN IF NOT EXISTS cluster_id varchar(20);
COMMENT ON COLUMN bindings.stores.cluster_id IS 'Идентификатор кластера';