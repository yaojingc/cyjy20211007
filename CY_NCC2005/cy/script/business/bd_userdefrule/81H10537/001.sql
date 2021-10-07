insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZaed1c0eb4553','cy','2021-07-19 11:53:23','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-07-19 11:53:23',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZc57ed04eb5ac','81H1_H','2021-07-19 11:53:23','~',0,0,20,null,'~','~','0001ZZZZaed1c0eb4553','~','~',0,null,'2021-07-19 11:53:23',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ5de3c50ada65','81H1_B','2021-07-19 11:53:23','~',0,0,10,null,'~','~','0001ZZZZaed1c0eb4553','~','~',0,null,'2021-07-19 11:53:23',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZbd2ee6cb2c46',null,'2021-07-19 11:53:23','~',null,0,'2021-07-19 11:53:23','~','0001ZZZZc57ed04eb5ac','ccc5f89d-422c-4252-9bee-1df51539cf30','2021-07-19 11:53:23','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ49fc1f9d3149',null,'2021-07-19 11:53:23','~',null,0,null,'~','0001ZZZZ5de3c50ada65','fc17ff01-2bd5-4376-be49-4e1790eab277','2021-07-19 11:53:23','vbdef');
go

