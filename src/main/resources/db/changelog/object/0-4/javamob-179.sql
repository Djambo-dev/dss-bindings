--liquibase formatted sql

--changeSet St.Yakovlev:javamob-179 logicalFilePath:db/changelog/object/0-4/javamob-179.sql
delete from bindings.employee where personal_number is NULL