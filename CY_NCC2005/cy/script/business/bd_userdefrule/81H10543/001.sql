insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ8105c75078f0','cy','2021-07-19 14:33:45','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-07-19 14:33:45',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZb3562102f214','81H1_H','2021-07-19 14:33:45','~',0,0,20,null,'~','~','0001ZZZZ8105c75078f0','~','~',0,null,'2021-07-19 14:33:45',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ3b60b063608a','81H1_B','2021-07-19 14:33:45','~',0,0,10,null,'~','~','0001ZZZZ8105c75078f0','~','~',0,null,'2021-07-19 14:33:45',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZe4eb02e53d19',null,'2021-07-19 14:33:45','~',null,0,'2021-07-19 14:33:45','~','0001ZZZZb3562102f214','5cdc5913-ad9f-4f88-9923-785f7097915d','2021-07-19 14:33:45','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZc10fd75e689c',null,'2021-07-19 14:33:45','~',null,0,null,'~','0001ZZZZ3b60b063608a','e0335c51-dd64-4488-9b4a-8139cb32d124','2021-07-19 14:33:45','vbdef');
go

