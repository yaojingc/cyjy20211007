insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ3dd9ec02fbe2','cy','2021-06-23 10:38:23','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-06-23 10:38:23',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZe30b6d0c22b7','81H1_H','2021-06-23 10:38:23','~',0,0,20,null,'~','~','0001ZZZZ3dd9ec02fbe2','~','~',0,null,'2021-06-23 10:38:23',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZe39db9a6391b','81H1_B','2021-06-23 10:38:23','~',0,0,10,null,'~','~','0001ZZZZ3dd9ec02fbe2','~','~',0,null,'2021-06-23 10:38:23',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ221a2816adf3',null,'2021-06-23 10:38:23','~',null,0,'2021-06-23 10:38:23','~','0001ZZZZe30b6d0c22b7','75a2e753-ea7a-4071-be2a-5aaaf888bfcc','2021-06-23 10:38:23','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ0e96974c24a9',null,'2021-06-23 10:38:23','~',null,0,null,'~','0001ZZZZe39db9a6391b','ecea3c6a-ce5a-4b7d-b4d6-476eec5354d3','2021-06-23 10:38:23','vbdef');
go

