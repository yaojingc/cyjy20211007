insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ22ffad6e481e','cy','2021-07-19 13:53:01','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-07-19 13:53:01',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ168b0fb42f93','81H1_H','2021-07-19 13:53:01','~',0,0,20,null,'~','~','0001ZZZZ22ffad6e481e','~','~',0,null,'2021-07-19 13:53:01',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ030e1149fa2a','81H1_B','2021-07-19 13:53:01','~',0,0,10,null,'~','~','0001ZZZZ22ffad6e481e','~','~',0,null,'2021-07-19 13:53:01',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZccbd8d67d441',null,'2021-07-19 13:53:01','~',null,0,'2021-07-19 13:53:01','~','0001ZZZZ168b0fb42f93','945b2855-04d2-4856-bdf2-24d1acd9ddf3','2021-07-19 13:53:01','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ8c6813050060',null,'2021-07-19 13:53:01','~',null,0,null,'~','0001ZZZZ030e1149fa2a','93ed4f13-f81a-455c-b99c-446143f0571e','2021-07-19 13:53:01','vbdef');
go

