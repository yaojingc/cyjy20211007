
delete from BD_RELATEDAPP where APPCODE like '81H10501%';
go

INSERT INTO BD_RELATEDAPP (APPCODE, DR, PAGECODE, PK_BILLTYPECODE, PK_BILLTYPEID, PK_RELATEDAPP, PRIORITY, SENCE, TS, VDEF1, VDEF2, VDEF3) VALUES ('81H10501', null, '81H10501L_CARD', 'XXSQ', '0001ZZZZ78FB444C45C2', '0001ZZZZ93ee6a6d731d', 0, 4, '2021-06-23 14:36:45', null, null, null);
go
INSERT INTO BD_RELATEDAPP (APPCODE, DR, PAGECODE, PK_BILLTYPECODE, PK_BILLTYPEID, PK_RELATEDAPP, PRIORITY, SENCE, TS, VDEF1, VDEF2, VDEF3) VALUES ('81H10501A', null, null, 'XXSQ', '0001ZZZZ78FB444C45C2', '0001ZZZZ078c8d9587e6', 0, 3, '2021-06-23 14:36:45', null, null, null);
go