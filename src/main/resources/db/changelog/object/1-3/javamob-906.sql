--liquibase formatted sql

--changeSet A.Lambert:javamob-906.sql logicalFilePath:db/changelog/object/1-3/javamob-906.sql

-- Delete old bindings
delete from bindings.employee where cfo_id in (
 'E1040874' -- HD01
,'E1040875' -- HD02
,'E1040873' -- HD03
,'E1040876' -- HD04
,'E1045595' -- HD05
,'E1045810' -- HD06
,'E1045811' -- HD07
,'E1046341' -- HD08
,'E1046351' -- HD09
,'E1047001' -- HD10
,'E1047000' -- HD11
,'E1047002' -- HD12
,'E1047053' -- HD13
,'E1047054' -- HD14
,'E1047055' -- HD15
,'E1047773' -- HD16
,'E1047774' -- HD17
,'E1047775' -- HD18
,'E1047822' -- HD19
,'E1047821' -- HD20
,'E1047823' -- HD21
,'E1047826' -- HD22
,'E1047824' -- HD23
,'E1047825' -- HD24
);

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
-- HD17
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047774','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
-- HD24
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047825','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
-- HD07
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1788158', 56000002,'Территориальный менеджер', 'ROMAN.DREYZIN', now());
-- HD09
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1788158', 56000002,'Территориальный менеджер', 'ROMAN.DREYZIN', now());
-- HD19
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047822','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- HD21
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047823','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- HD22
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047826','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- HD23
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047824','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- HD13
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047053','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
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
-- HD20
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047821','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
-- HD22
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047826','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());



-- HD05
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045595','1729160', 56000001,'Управляющий магазином', 'IVAN.RUMYANTSEV', now());
-- HD11
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047000','1773071', 56000001,'Управляющий магазином', 'MARINA.RAKITKINA', now());
-- HD12
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047002','1729697', 56000001,'Управляющий магазином', 'ANASTASIA.DOROFEEVA', now());
-- HD06
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045810','1729486', 56000001,'Управляющий магазином', 'MARIA.RUBCHIKHINA', now());
-- HD02
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1775431', 56000001,'Управляющий магазином', 'EVGENIYA.BEZYZVESTNOVA', now());
-- HD14
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047054','1775815', 56000001,'Управляющий магазином', 'ANATOLY.SAVIN', now());
-- HD10
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047001','1763861', 56000001,'Управляющий магазином', 'ALEXEY.ERMILOV', now());
-- HD16
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1781109', 56000001,'Управляющий магазином', 'ALEXANDR.DRONYAEV', now());
-- HD18
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1771953', 56000001,'Управляющий магазином', 'ANASTASIA.KLIMOVA', now());
-- HD01
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040874','1663608', 56000001,'Управляющий магазином', 'NIKOLAY.KOMAROV', now());
-- HD20
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047821','1800826', 56000001,'Управляющий магазином', 'EKATERINA.POPOVA', now());
-- HD17
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047774','1757271', 56000001,'Управляющий магазином', 'NIKITA.SHARAPOV', now());
-- HD07
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1788686', 56000001,'Управляющий магазином', 'MAXIM.KHARITONOV', now());
-- HD13
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047053','1778918', 56000001,'Управляющий магазином', 'MARINA.NOVIKOVA', now());
-- HD15
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047055','1783878', 56000001,'Управляющий магазином', 'ANTON.POLONSKY', now());
-- HD19
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047822','1785823', 56000001,'Управляющий магазином', 'SERGEY.VISHNYAKOV', now());
-- HD08
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046341','1689061', 56000001,'Управляющий магазином', 'KONSTANTIN.MOKSHIN', now());
-- HD04
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1663726', 56000001,'Управляющий магазином', 'EVGENIY.POLEZHAEV', now());
-- HD03
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040873','1678992', 56000001,'Управляющий магазином', 'IGOR.MIKLYAEV', now());
-- HD09
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1760149', 56000001,'Управляющий магазином', 'ALEXANDR.KATAFEEV', now());
-- HD21
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047823','1783878', 56000001,'Управляющий магазином', 'ANTON.POLONSKY', now());
-- HD22
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047826','1814950', 56000001,'Управляющий магазином', 'VLADIMIR.ZHUVAGIN', now());