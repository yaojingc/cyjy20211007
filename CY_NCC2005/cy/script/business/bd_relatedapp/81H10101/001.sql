
delete from BD_RELATEDAPP where APPCODE like '81H10101%';
go

INSERT INTO BD_RELATEDAPP (APPCODE, DR, PAGECODE, PK_BILLTYPECODE, PK_BILLTYPEID, PK_RELATEDAPP, PRIORITY, SENCE, TS, VDEF1, VDEF2, VDEF3) VALUES ('81H10101', null, '81H10101L_CARD', 'XXDA', '0001ZZZZ16F7F90AD009', '0001ZZZZ5f41b40d5cbe', 0, 4, '2021-07-02 14:38:31', null, null, null);
go
INSERT INTO BD_RELATEDAPP (APPCODE, DR, PAGECODE, PK_BILLTYPECODE, PK_BILLTYPEID, PK_RELATEDAPP, PRIORITY, SENCE, TS, VDEF1, VDEF2, VDEF3) VALUES ('81H10101A', null, null, 'XXDA', '0001ZZZZ16F7F90AD009', '0001ZZZZc8d1a796562e', 0, 3, '2021-07-02 14:38:31', null, null, null);
go
