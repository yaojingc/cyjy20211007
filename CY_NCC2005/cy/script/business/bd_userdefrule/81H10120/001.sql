insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZfbd65bfe3c0c','cy','2021-07-19 13:38:50','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-07-19 13:38:50',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ989d1dc82da2','81H1_H','2021-07-19 13:38:50','~',0,0,20,null,'~','~','0001ZZZZfbd65bfe3c0c','~','~',0,null,'2021-07-19 13:38:50',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ1d30be680865','81H1_B','2021-07-19 13:38:50','~',0,0,10,null,'~','~','0001ZZZZfbd65bfe3c0c','~','~',0,null,'2021-07-19 13:38:50',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZf2d58714c3d4',null,'2021-07-19 13:38:50','~',null,0,'2021-07-19 13:38:50','~','0001ZZZZ989d1dc82da2','acc447ba-b19e-4c03-8794-124a37aaa4c4','2021-07-19 13:38:50','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ1e4a8f7b2d56',null,'2021-07-19 13:38:50','~',null,0,null,'~','0001ZZZZ1d30be680865','eee1ce6a-6bd9-416a-8a7f-a81a84602114','2021-07-19 13:38:50','vbdef');
go

