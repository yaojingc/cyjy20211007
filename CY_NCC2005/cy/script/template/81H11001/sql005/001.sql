
delete from BD_RELATEDAPP where APPCODE like '81H11001%';
go

INSERT INTO BD_RELATEDAPP (APPCODE, DR, PAGECODE, PK_BILLTYPECODE, PK_BILLTYPEID, PK_RELATEDAPP, PRIORITY, SENCE, TS, VDEF1, VDEF2, VDEF3) VALUES ('81H11001', null, '81H11001L_CARD', 'KFTD', '0001ZZZZ82D6E2A8973B', '0001ZZZZa32cc2822ea8', 0, 4, '2021-06-23 11:46:18', null, null, null);
go
INSERT INTO BD_RELATEDAPP (APPCODE, DR, PAGECODE, PK_BILLTYPECODE, PK_BILLTYPEID, PK_RELATEDAPP, PRIORITY, SENCE, TS, VDEF1, VDEF2, VDEF3) VALUES ('81H11001A', null, null, 'KFTD', '0001ZZZZ82D6E2A8973B', '0001ZZZZeea51bb6a112', 0, 3, '2021-06-23 11:46:18', null, null, null);
go