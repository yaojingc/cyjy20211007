insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ44dd94b17790','cy','2021-06-30 11:07:45','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-06-30 11:07:45',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZee33210edf61','81H1_H','2021-06-30 11:07:45','~',0,0,20,null,'~','~','0001ZZZZ44dd94b17790','~','~',0,null,'2021-06-30 11:07:45',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZd547156fe7dd','81H1_B','2021-06-30 11:07:45','~',0,0,10,null,'~','~','0001ZZZZ44dd94b17790','~','~',0,null,'2021-06-30 11:07:45',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ0990cafed7de',null,'2021-06-30 11:07:45','~',null,0,'2021-06-30 11:07:45','~','0001ZZZZee33210edf61','11bafd7d-27fd-4c10-8c4f-6a4d91b659aa','2021-06-30 11:07:45','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ55b6d93961f9',null,'2021-06-30 11:07:45','~',null,0,null,'~','0001ZZZZd547156fe7dd','cb7153c5-ece4-4847-bc0a-46846213149f','2021-06-30 11:07:45','vbdef');
go

