insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ2b0257825db5','cy','2021-07-19 10:24:42','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-07-19 10:24:42',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ303a9350512d','81H1_H','2021-07-19 10:24:42','~',0,0,20,null,'~','~','0001ZZZZ2b0257825db5','~','~',0,null,'2021-07-19 10:24:42',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZf2aff29ed7e1','81H1_B','2021-07-19 10:24:42','~',0,0,10,null,'~','~','0001ZZZZ2b0257825db5','~','~',0,null,'2021-07-19 10:24:42',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZe6a145a93793',null,'2021-07-19 10:24:42','~',null,0,'2021-07-19 10:24:42','~','0001ZZZZ303a9350512d','616d6644-e695-4fce-a886-35ddb082cb2a','2021-07-19 10:24:42','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZda41c67edc2d',null,'2021-07-19 10:24:42','~',null,0,null,'~','0001ZZZZf2aff29ed7e1','b33b8125-cf5d-482e-9413-9a9678613e05','2021-07-19 10:24:42','vbdef');
go

