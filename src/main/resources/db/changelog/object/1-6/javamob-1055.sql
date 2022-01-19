--liquibase formatted sql

--changeSet A.Lambert:javamob-1055.sql logicalFilePath:db/changelog/object/1-6/javamob-1055.sql

-- TODO HD45 - связь удалить
-- insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047836','1822750', 56000001,'Управляющий магазином', 'A.ZHOLOBOV', now());
-- TODO добавить его в HD31
-- insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047832','1822750', 56000001,'Управляющий магазином', 'A.ZHOLOBOV', now());