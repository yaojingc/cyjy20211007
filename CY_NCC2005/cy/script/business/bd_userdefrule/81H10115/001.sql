insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZd229e3073af7','cy','2021-06-23 09:36:36','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-06-23 09:36:36',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ4fd18a04ea1c','81H1_H','2021-06-23 09:36:36','~',0,0,20,null,'~','~','0001ZZZZd229e3073af7','~','~',0,null,'2021-06-23 09:36:36',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ62a40c782381','81H1_B','2021-06-23 09:36:36','~',0,0,10,null,'~','~','0001ZZZZd229e3073af7','~','~',0,null,'2021-06-23 09:36:36',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ2e35468bfdd7',null,'2021-06-23 09:36:36','~',null,0,'2021-06-23 09:36:36','~','0001ZZZZ4fd18a04ea1c','9db2e2be-2abc-415f-bb42-6736d49e8249','2021-06-23 09:36:36','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ18fed7e2a7b2',null,'2021-06-23 09:36:36','~',null,0,null,'~','0001ZZZZ62a40c782381','66b294bb-8e75-4c95-9423-832bca810d91','2021-06-23 09:36:36','vbdef');
go

