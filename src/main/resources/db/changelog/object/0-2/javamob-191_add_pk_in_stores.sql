DROP FUNCTION IF EXISTS bindings.create_constraint_if_not_exists;
create function bindings.create_constraint_if_not_exists (
    t_schema text, c_name text, constraint_sql text
)
returns void AS
$$
begin
    if not exists (select constraint_name
                   from information_schema.constraint_column_usage
                   where table_schema = t_schema  and constraint_name = c_name) then
        execute constraint_sql;
    end if;
end;
$$ language 'plpgsql';

SELECT bindings.create_constraint_if_not_exists(
        'bindings',
        'pk_stores',
        'ALTER TABLE bindings.stores ADD CONSTRAINT pk_stores PRIMARY KEY (mdm_store_id, cfo_id)');