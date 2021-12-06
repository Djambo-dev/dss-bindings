--liquibase formatted sql

--changeSet A.Lambert:javamob-1027.sql logicalFilePath:db/changelog/object/1-5/javamob-1027.sql

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
,'E1047828' -- HD25
,'E1047827' -- HD26
,'E1047829' -- HD27
,'E1047831' -- HD28
,'E1047830' -- HD29
,'E1047832' -- HD31
,'E1047833' -- HD32
,'E1047835' -- HD33
,'E1047834' -- HD34
,'E1047837' -- HD35
,'E1047838' -- HD36
,'E1047841' -- HD37
,'E1047842' -- HD38
,'E1047840' -- HD39
,'E1047843' -- HD40
,'E1047844' -- HD41
,'E1047846' -- HD42
,'E1045893' -- HD43
,'E1049464' -- HD44
,'E1047836' -- HD45
,'E1047839' -- HD46
,'E1049716' -- HD47
,'E1049769' -- HD48
,'E1049768' -- HD49
,'E1049771' -- HD50
,'E1049772' -- HD51
,'E1049786' -- HD52
,'E1050244' -- HD53
,'E1049790' -- HD54
,'E1049770' -- HD55
,'E1049751' -- HD56
,'E1049717' -- HD57
,'E1049735' -- HD58
,'E1050263' -- HD59
,'E1050265' -- HD60
,'E1050267' -- HD61
,'E1050264' -- HD62
,'E1050351' -- HD63
,'E1050373' -- HD64
,'E1050405' -- HD65
,'E1050410' -- HD66
,'E1050403' -- HD67
,'E1050456' -- HD68
,'E1050485' -- HD69
,'E1050455' -- HD70
,'E1050496' -- HD71
,'E1050487' -- HD72
,'E1050578' -- HD73
,'E1050567' -- HD74
,'E1050530' -- HD75
,'E1049804' -- HD76
,'E1049793' -- HD77
,'E1050568' -- HD78
,'E1050569' -- HD79
,'E1049794' -- HD80
);

-- HD01
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040874','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040874','1663608', 56000001,'Управляющий магазином', 'NIKOLAY.KOMAROV', now());
-- HD02
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1763861', 56000001,'Управляющий магазином', 'ALEXEY.ERMILOV', now());
-- HD03
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040873','1817898', 56000002,'Территориальный менеджер', 'E.RUDAKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040873','1678992', 56000001,'Управляющий магазином', 'IGOR.MIKLYAEV', now());
-- HD04
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1788158', 56000002,'Территориальный менеджер', 'ROMAN.DREYZIN', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1663726', 56000001,'Управляющий магазином', 'EVGENIY.POLEZHAEV', now());
-- HD05
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045595','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045595','1729160', 56000001,'Управляющий магазином', 'IVAN.RUMYANTSEV', now());
-- HD06
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045810','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045810','1729486', 56000001,'Управляющий магазином', 'MARIA.RUBCHIKHINA', now());
-- HD07
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1817090', 56000002,'Территориальный менеджер', 'K.SCRIPNIK', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1788686', 56000001,'Управляющий магазином', 'MAXIM.KHARITONOV', now());
-- HD08
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046341','1788158', 56000002,'Территориальный менеджер', 'ROMAN.DREYZIN', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046341','1689061', 56000001,'Управляющий магазином', 'KONSTANTIN.MOKSHIN', now());
-- HD09
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1817090', 56000002,'Территориальный менеджер', 'K.SCRIPNIK', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1760149', 56000001,'Управляющий магазином', 'ALEXANDR.KATAFEEV', now());
-- HD10
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047001','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047001','1867339', 56000001,'Управляющий магазином', 'MARIYA.HUDYAKOVA', now());
-- HD11
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047000','1788158', 56000002,'Территориальный менеджер', 'ROMAN.DREYZIN', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047000','1766550', 56000001,'Управляющий магазином', 'MARINA.UTKINA', now());
-- HD12
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047002','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047002','1787356', 56000001,'Управляющий магазином', 'EKATERINA.KUZNETSOVA', now());
-- HD13
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047053','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047053','1780180', 56000001,'Управляющий магазином', 'A.BAZUNOVA', now());
-- HD14
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047054','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047054','1775815', 56000001,'Управляющий магазином', 'ANATOLY.SAVIN', now());
-- HD15
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047055','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047055','1841629', 56000001,'Управляющий магазином', 'N.VOROBIEVA', now());
-- HD16
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1781109', 56000001,'Управляющий магазином', 'ALEXANDR.DRONYAEV', now());
-- HD17
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047774','1817090', 56000002,'Территориальный менеджер', 'K.SCRIPNIK', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047774','1846316', 56000001,'Управляющий магазином', 'ANDREY.BAKAEV', now());
-- HD18
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1778918', 56000001,'Управляющий магазином', 'MARINA.NOVIKOVA', now());
-- HD19
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047822','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047822','1805347', 56000001,'Управляющий магазином', 'TATIANA.KONKOVA', now());
-- HD20
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047821','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047821','1800826', 56000001,'Управляющий магазином', 'EKATERINA.POPOVA', now());
-- HD21
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047823','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047823','1783878', 56000001,'Управляющий магазином', 'ANTON.POLONSKY', now());
-- HD22
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047826','1817898', 56000002,'Территориальный менеджер', 'E.RUDAKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047826','1814950', 56000001,'Управляющий магазином', 'VLADIMIR.ZHUVAGIN', now());
-- HD24
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047825','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047825','1812363', 56000001,'Управляющий магазином', 'IVAN.GRECHUHIN', now());
-- HD25
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047828','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047828','1827582', 56000001,'Управляющий магазином', 'D.MIREDELINA', now());
-- HD26
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047827','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047827','1665300', 56000001,'Управляющий магазином', 'K.ZUBKOV', now());
-- HD27
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047829','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047829','1834045', 56000001,'Управляющий магазином', 'M.KAMALOV', now());
-- HD28
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047831','1788158', 56000002,'Территориальный менеджер', 'ROMAN.DREYZIN', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047831','1832039', 56000001,'Управляющий магазином', 'E.KUKUSHKINA', now());
-- HD29
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047830','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047830','1828142', 56000001,'Управляющий магазином', 'ELENA.NESTEROVA', now());
-- HD31
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047832','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047832','1845542', 56000001,'Управляющий магазином', 'V.MALISHKINA', now());
-- HD32
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047833','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047833','1811994', 56000001,'Управляющий магазином', 'OLGA.KARZUKHINA', now());
-- HD33
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047835','1817898', 56000002,'Территориальный менеджер', 'E.RUDAKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047835','1841688', 56000001,'Управляющий магазином', 'ANTONINA.CHEGOLYA', now());
-- HD34
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047834','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047834','1785823', 56000001,'Управляющий магазином', 'SERGEY.VISHNYAKOV', now());
-- HD36
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047838','1817898', 56000002,'Территориальный менеджер', 'E.RUDAKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047838','1840461', 56000001,'Управляющий магазином', 'DMITRY.SIDOROV', now());
-- HD37
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047841','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047841','1850788', 56000001,'Управляющий магазином', 'ANASTASIYA.BUTKO', now());
-- HD38
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047842','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047842','1775431', 56000001,'Управляющий магазином', 'EVGENI.BEZYZVESTNOVA', now());
-- HD39
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047840','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047840','1780529', 56000001,'Управляющий магазином', 'DENIO.POPOV', now());
-- HD40
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047843','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047843','1854829', 56000001,'Управляющий магазином', 'IVAN.SAVVATEV', now());
-- HD41
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047844','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047844','1844429', 56000001,'Управляющий магазином', 'NATKUZNETSOVA', now());
-- HD42
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047846','1817090', 56000002,'Территориальный менеджер', 'K.SCRIPNIK', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047846','1837856', 56000001,'Управляющий магазином', 'ALEXANDER.LISCHUK', now());
-- HD43
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045893','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045893','1785541', 56000001,'Управляющий магазином', 'A.TITOVA', now());
-- HD44
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049464','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049464','1834777', 56000001,'Управляющий магазином', 'MARIN.ZHIGUNOVA', now());
-- HD45
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047836','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047836','1822750', 56000001,'Управляющий магазином', 'A.ZHOLOBOV', now());
-- HD46
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047839','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
-- empty
-- HD47
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049716','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049716','1856730', 56000001,'Управляющий магазином', 'NATALIV.YAKOVLEVA', now());
-- HD48
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049769','1817898', 56000002,'Территориальный менеджер', 'E.RUDAKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049769','1849363', 56000001,'Управляющий магазином', 'IRINA.KARPENKO', now());
-- HD49
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049768','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049768','1863580', 56000001,'Управляющий магазином', 'IRINA.LESINA', now());
-- HD50
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049771','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049771','1850094', 56000001,'Управляющий магазином', 'VYACHESLAV.KHANIN', now());
-- HD51
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049772','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049772','1858777', 56000001,'Управляющий магазином', 'YULIA.SALIVAN', now());
-- HD52
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049786','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049786','1840739', 56000001,'Управляющий магазином', 'OLES.SOROKINA', now());
-- HD53
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050244','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
    -- UM empty
-- HD54
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049790','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
    -- UM empty
-- HD55
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049770','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049770','1840131', 56000001,'Управляющий магазином', 'SE.KLIMOV', now());
-- HD56
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049751','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
    -- UM empty
-- HD57
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049717','1817090', 56000002,'Территориальный менеджер', 'K.SCRIPNIK', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049717','1823296', 56000001,'Управляющий магазином', 'D.SANDUL', now());
-- HD58
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049735','1788158', 56000002,'Территориальный менеджер', 'ROMAN.DREYZIN', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049735','1790886', 56000001,'Управляющий магазином', 'M.KUZNETSOVA', now());
-- HD59
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050263','1661372', 56000002,'Территориальный менеджер', 'LEV.BELOZEROV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050263','1830883', 56000001,'Управляющий магазином', 'IGO.KOROLEV', now());
-- HD60
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050265','1788158', 56000002,'Территориальный менеджер', 'ROMAN.DREYZIN', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050265','1859288', 56000001,'Управляющий магазином', 'ANASTAA.BOGDANOVA', now());
-- HD61
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050267','1817090', 56000002,'Территориальный менеджер', 'K.SCRIPNIK', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050267','1851100', 56000001,'Управляющий магазином', 'SERGEY.MAKUNIN', now());
-- HD62
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050264','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050264','1853316', 56000001,'Управляющий магазином', 'NINA.ESIPOVA', now());
-- HD63
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050351','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
    -- UM empty
-- HD64
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050373','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
    -- UM empty
-- HD65
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050405','1817898', 56000002,'Территориальный менеджер', 'E.RUDAKOVA', now());
    -- UM empty
-- HD66
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050410','1817898', 56000002,'Территориальный менеджер', 'E.RUDAKOVA', now());
    -- UM empty
-- HD67
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050403','1817898', 56000002,'Территориальный менеджер', 'E.RUDAKOVA', now());
    -- UM empty
-- HD69
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050485','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
    -- UM empty
-- HD70
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050455','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
    -- UM empty
-- HD71
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050496','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
    -- UM empty
-- HD72
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050487','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
    -- UM empty
-- HD73
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050578','1652849', 56000002,'Территориальный менеджер', 'IRINA.BABKOVA', now());
    -- UM empty
-- HD74
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050567','1639616', 56000002,'Территориальный менеджер', 'ANDRE.KUZNETSOV4', now());
    -- UM empty
-- HD75
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1050530','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
    -- UM empty
-- HD76
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1049804','1797164', 56000002,'Территориальный менеджер', 'DENIS.PODVALNOV', now());
    -- UM empty