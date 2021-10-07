insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ11279bc594e8','cy','2021-06-23 14:36:45','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-06-23 14:36:45',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZcfafb76aed97','81H1_H','2021-06-23 14:36:45','~',0,0,20,null,'~','~','0001ZZZZ11279bc594e8','~','~',0,null,'2021-06-23 14:36:45',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ0fc4f0044a6d','81H1_B','2021-06-23 14:36:45','~',0,0,10,null,'~','~','0001ZZZZ11279bc594e8','~','~',0,null,'2021-06-23 14:36:45',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ2eb6ffdd0235',null,'2021-06-23 14:36:45','~',null,0,'2021-06-23 14:36:45','~','0001ZZZZcfafb76aed97','005173a2-bb00-4ee5-88e5-8b32e0ef254d','2021-06-23 14:36:45','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ0eae6b58e7d8',null,'2021-06-23 14:36:45','~',null,0,null,'~','0001ZZZZ0fc4f0044a6d','5010bec2-04c9-46bc-bb86-57fec4903197','2021-06-23 14:36:45','vbdef');
go

