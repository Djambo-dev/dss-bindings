--liquibase formatted sql

--changeSet S.Vanin:javamob-829.sql logicalFilePath:db/changelog/object/1-0/javamob-829.sql

-- Delete old bindings
delete from bindings.employee where personal_number in ('1661476', '1652849', '1661372', '1639616', '1679960', --TMs
                                                        '1600079', -- HD05, HD02, HD14, HD10
                                                        '1763861', -- HD10
                                                        '1604646', -- HD01, HD03, HD04, HD13, HD08, HD09
                                                        '1663608', -- HD01
                                                        '1770770',  --HD13
                                                        '1754784', --HD08
                                                        '1678992', -- HD03
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
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047001','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD14
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047054','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD16
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD18
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD02
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1661476', 56000002,'Территориальный менеджер', 'RUSLAN.FOROSTOVSKY', now());
-- HD03
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040873','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
-- HD04
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
-- HD08
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046341','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
-- HD09
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1679960', 56000002,'Территориальный менеджер', 'MIKHA.KOLTSOV', now());
-- HD05
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045595','1729160', 56000004,'Старший работник торгового зала', 'IVAN.RUMYANTSEV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045595','1760192', 56000004,'Старший работник торгового зала', 'NIKITA.BELYAEV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045595','1785541', 56000004,'Старший работник торгового зала', 'ANASTASIA.TITOVA', now());
-- HD11
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047000','1766550', 56000004,'Старший работник торгового зала', 'MARINA.UTKINA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047000','1787744', 56000004,'Старший работник торгового зала', 'SVETLANA.PETROVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047000','1773071', 56000001,'Управляющий магазином', 'MARINA.RAKITKINA', now());
-- HD12
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047002','1729697', 56000001,'Управляющий магазином', 'ANASTASIA.DOROFEEVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047002','1785185', 56000004,'Старший работник торгового зала', 'ELENA.KOZLOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047002','1787356', 56000004,'Старший работник торгового зала', 'EKATERINA.KUZNETSOVA', now());
-- HD06
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045810','1729486', 56000004,'Старший работник торгового зала', 'MARIA.RUBCHIKHINA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045810','1782896', 56000004,'Старший работник торгового зала', 'MARIA.VEDENEEVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045810','1786208', 56000004,'Старший работник торгового зала', 'ALEKSEY.ROMANOV', now());
-- HD02
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1665303', 56000004,'Старший работник торгового зала', 'EKATERINA.BONDAREVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1665306', 56000004,'Старший работник торгового зала', 'EKATERINA.ZHAROVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1775431', 56000001,'Управляющий магазином', 'EVGENIYA.BEZYZVESTNOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1778923', 56000004,'Старший работник торгового зала', 'ELENA.ZAVRAZHNOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040875','1780529', 56000004,'Старший работник торгового зала', 'DENIS.POPOV', now());
-- HD14
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047054','1775815', 56000001,'Управляющий магазином', 'ANATOLY.SAVIN', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047054','1776562', 56000004,'Старший работник торгового зала', 'KRISTINA.VYSTAVKINA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047054','1787469', 56000004,'Старший работник торгового зала', 'KSENIA.RIGA', now());
-- HD10
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047001','1763861', 56000001,'Управляющий магазином', 'ALEXEY.ERMILOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047001','1764522', 56000004,'Старший работник торгового зала', 'NUGMANOVA.NATALIA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047001','1774216', 56000004,'Старший работник торгового зала', 'LYSENKO.ANTON', now());
-- HD16
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1781109', 56000001,'Управляющий магазином', 'ALEXANDR.DRONYAEV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1790120', 56000004,'Старший работник торгового зала', 'ILYA.RYABININ', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1790886', 56000004,'Старший работник торгового зала', 'MARIA.KUZNETSOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047773','1793579', 56000004,'Старший работник торгового зала', 'MIKHAIL.RUBTSOV', now());
-- HD18
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1771953', 56000001,'Управляющий магазином', 'ANASTASIA.KLIMOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1770692', 56000004,'Старший работник торгового зала', 'MARINA.GURIANOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1787391', 56000004,'Старший работник торгового зала', 'SERGEY.ZUEV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047775','1804310', 56000004,'Старший работник торгового зала', 'DMITRIY.SABANOV', now());
-- HD01
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040874','1663608', 56000001,'Управляющий магазином', 'NIKOLAY.KOMAROV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040874','1664115', 56000004,'Старший работник торгового зала', 'ALEXANDR.SURKOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040874','1665300', 56000004,'Старший работник торгового зала', 'KONSTANTIN.ZUBKOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040874','1695869', 56000004,'Старший работник торгового зала', 'YULIA.GUBANOVA', now());
-- HD20
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047821','1800826', 56000004,'Старший работник торгового зала', 'EKATERINA.POPOVA', now());
-- HD17
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047774','1757271', 56000001,'Управляющий магазином', 'NIKITA.SHARAPOV', now());
-- HD07
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1779310', 56000004,'Старший работник торгового зала', 'NIKITA.GUSEV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1780246', 56000004,'Старший работник торгового зала', 'ELVIRA.MINNEGALIEVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1799731', 56000004,'Старший работник торгового зала', 'BAKHTYAR.ASVAROV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1045811','1788686', 56000001,'Управляющий магазином', 'MAXIM.KHARITONOV', now());
-- HD13
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047053','1778918', 56000001,'Управляющий магазином', 'MARINA.NOVIKOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047053','1780180', 56000004,'Старший работник торгового зала', 'ANNA.BAZUNOVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047053','1780831', 56000004,'Старший работник торгового зала', 'LUBOV.NEPOGODEVA', now());
-- HD15
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047055','1783878', 56000001,'Управляющий магазином', 'ANTON.POLONSKY', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047055','1785402', 56000004,'Старший работник торгового зала', 'IRINA.SHMELKOVA', now());
-- HD19
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047822','1785823', 56000001,'Управляющий магазином', 'SERGEY.VISHNYAKOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047822','1798366', 56000004,'Старший работник торгового зала', 'IRINA.POPOVTSEVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1047822','1805347', 56000004,'Старший работник торгового зала', 'TATIANA.KONKOVA', now());
-- HD08
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046341','1689061', 56000001,'Управляющий магазином', 'KONSTANTIN.MOKSHIN', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046341','1728974', 56000004,'Старший работник торгового зала', 'ELENA.BEGININA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046341','1761830', 56000004,'Старший работник торгового зала', 'ELMIRA.DZHUMANYAZOVA', now());
-- HD04
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1663726', 56000001,'Управляющий магазином', 'EVGENIY.POLEZHAEV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1682933', 56000004,'Старший работник торгового зала', 'ELENA.PEREVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1793216', 56000004,'Старший работник торгового зала', 'IVAN.ZHEREBILOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040876','1801150', 56000004,'Старший работник торгового зала', 'BORIS.RADZIVANOVSKY', now());
-- HD03
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040873','1663864', 56000004,'Старший работник торгового зала', 'ALEXANDR.SERGIEVSKY', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040873','1680996', 56000004,'Старший работник торгового зала', 'ARTEM.TOVSTY', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1040873','1678992', 56000001,'Управляющий магазином', 'IGOR.MIKLYAEV', now());
-- HD09
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1755468', 56000004,'Старший работник торгового зала', 'IRINA.KOLESNICHENKO', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1787669', 56000004,'Старший работник торгового зала', 'POLINA.ANDREEVA', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1794478', 56000004,'Старший работник торгового зала', 'NIKITA.SHARAPOV', now());
insert into bindings.employee(cfo_id, personal_number, position_id, position_name, personal_login, modified_date) values ('E1046351','1760149', 56000001,'Управляющий магазином', 'ALEXANDR.KATAFEEV', now());
