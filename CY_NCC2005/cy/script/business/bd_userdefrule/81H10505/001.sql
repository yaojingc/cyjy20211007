insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZa73f57fb8433','cy','2021-07-06 09:14:25','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-07-06 09:14:25',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZa13e86c65ce6','81H1_H','2021-07-06 09:14:25','~',0,0,20,null,'~','~','0001ZZZZa73f57fb8433','~','~',0,null,'2021-07-06 09:14:25',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZb3a90031f0f7','81H1_B','2021-07-06 09:14:25','~',0,0,10,null,'~','~','0001ZZZZa73f57fb8433','~','~',0,null,'2021-07-06 09:14:25',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ9a88873c8bc9',null,'2021-07-06 09:14:25','~',null,0,'2021-07-06 09:14:25','~','0001ZZZZa13e86c65ce6','591516f3-f509-4d71-ad7c-311c8ce01e8a','2021-07-06 09:14:25','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ6a3deb933ed9',null,'2021-07-06 09:14:25','~',null,0,null,'~','0001ZZZZb3a90031f0f7','c78b640d-bf4b-4327-a559-71eeba2f509f','2021-07-06 09:14:25','vbdef');
go

