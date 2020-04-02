DROP FUNCTION IF EXISTS bindings.rename_column_if_exists;
create function bindings.rename_column_if_exists (t_schema text, t_name text, c_name text, constraint_sql text)
returns void AS
$$
begin
    if exists (select * from information_schema.columns
                     where table_schema = t_schema and
                     table_name = t_name and
                     column_name = c_name) then
        execute constraint_sql;
    end if;
end;
$$ language 'plpgsql';

SELECT bindings.rename_column_if_exists(
        'bindings',
        'employee',
		'employee_binding_id',
        'ALTER TABLE bindings.employee RENAME employee_binding_id TO employee_id');

ALTER TABLE bindings.employee ALTER COLUMN employee_id SET DEFAULT nextval('bindings.employee_seq');