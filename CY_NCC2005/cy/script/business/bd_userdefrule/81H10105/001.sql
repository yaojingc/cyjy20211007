insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ0f3b6016af7c','cy','2021-07-01 16:11:56','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-07-01 16:11:56',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZb074959bc9c1','81H1_H','2021-07-01 16:11:56','~',0,0,20,null,'~','~','0001ZZZZ0f3b6016af7c','~','~',0,null,'2021-07-01 16:11:56',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZff045a261054','81H1_B','2021-07-01 16:11:56','~',0,0,10,null,'~','~','0001ZZZZ0f3b6016af7c','~','~',0,null,'2021-07-01 16:11:56',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ72364be0f081',null,'2021-07-01 16:11:56','~',null,0,'2021-07-01 16:11:56','~','0001ZZZZb074959bc9c1','c4fc8c90-18fa-45e9-aef9-6f6587d19e44','2021-07-01 16:11:56','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ303d44d23cca',null,'2021-07-01 16:11:56','~',null,0,null,'~','0001ZZZZff045a261054','bca81412-fbaa-4f56-9acc-6b85a36cf2d0','2021-07-01 16:11:56','vbdef');
go

