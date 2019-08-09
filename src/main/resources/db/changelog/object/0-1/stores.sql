--liquibase formatted sql

--changeSet vsaydumarov:stores-1 logicalFilePath:db/changelog/object/0-1
CREATE TABLE bindings.stores
(
    store_id      BIGINT,
    mdm_store_id  BIGINT,
    name          VARCHAR(50),
    cfo_id        VARCHAR(50),
    address       TEXT,
    open_date     DATE,
    close_date    DATE,
    creation_date TIMESTAMP,
    modified_date TIMESTAMP
);

ALTER TABLE bindings.stores
    ADD CONSTRAINT stores_pk PRIMARY KEY (store_id);

CREATE SEQUENCE bindings.stores_seq START WITH 1 INCREMENT BY 1;

COMMENT ON TABLE bindings.stores IS 'Магазины';

COMMENT ON COLUMN bindings.stores.store_id IS 'Уникальный ID записи';
COMMENT ON COLUMN bindings.stores.mdm_store_id IS 'Уникальный ID записи в системе IBM MDM';
COMMENT ON COLUMN bindings.stores.name IS 'Наименование магазина для отображения';
COMMENT ON COLUMN bindings.stores.cfo_id IS 'Код ЦФО (Центра Фининансовой Ответственности)';
COMMENT ON COLUMN bindings.stores.address IS 'Адрес магазина';
COMMENT ON COLUMN bindings.stores.open_date IS 'Дата открытия магазина';
COMMENT ON COLUMN bindings.stores.close_date IS 'Дата закрытия магазина';

--changeSet vsaydumarov:stores-2
ALTER TABLE bindings.stores
    ALTER COLUMN mdm_store_id TYPE VARCHAR(50) USING mdm_store_id::VARCHAR(50);