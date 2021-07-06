--liquibase formatted sql

--changeSet A.Lambert:javamob-808.sql logicalFilePath:db/changelog/object/1-0/javamob-808.sql

-- Delete old bindings
delete from bindings.employee where personal_number in ('1661476', '1652849', '1661372', '1639616', '1679960');

-- Add new bindings:
-- HD05
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045595','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD06
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045810','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD11
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047000','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD12
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047002','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD01
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040874','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
-- HD07
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
-- HD17
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047774','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
-- HD20
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047821','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
-- HD13
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047053','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- HD15
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047055','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- HD19
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047822','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- HD21
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047823','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- HD10
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047001','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
-- HD14
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047054','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
-- HD16
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
-- HD18
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
-- HD02
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
-- HD03
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040873','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
-- HD04
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
-- HD08
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046341','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
-- HD09
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
