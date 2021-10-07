insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ623c25f26933','cy','2021-06-22 17:47:56','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-06-22 17:47:56',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZc8adc0707733','81H1_H','2021-06-22 17:47:56','~',0,0,20,null,'~','~','0001ZZZZ623c25f26933','~','~',0,null,'2021-06-22 17:47:56',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZb14a44045090','81H1_B','2021-06-22 17:47:56','~',0,0,10,null,'~','~','0001ZZZZ623c25f26933','~','~',0,null,'2021-06-22 17:47:56',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ24f0f344701c',null,'2021-06-22 17:47:56','~',null,0,'2021-06-22 17:47:56','~','0001ZZZZc8adc0707733','d895f3c5-b8a9-4cea-8c18-413c6d06f813','2021-06-22 17:47:56','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ077c8fce2f7e',null,'2021-06-22 17:47:56','~',null,0,null,'~','0001ZZZZb14a44045090','fef4bb10-8d53-475e-9792-8fa3d25db5ab','2021-06-22 17:47:56','vbdef');
go

