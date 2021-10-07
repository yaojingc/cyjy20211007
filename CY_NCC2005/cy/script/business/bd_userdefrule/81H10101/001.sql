insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZ016d1abb67c3','cy','2021-07-02 14:38:31','~',0,0,0,null,'~','~',null,'~','~',1,null,'2021-07-02 14:38:31',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZf017bb2a3c2c','81H1_H','2021-07-02 14:38:31','~',0,0,20,null,'~','~','0001ZZZZ016d1abb67c3','~','~',0,null,'2021-07-02 14:38:31',null);
go

insert into bd_userdefrule(pk_userdefrule,code,creationtime,creator,dataoriginflag,dr,itemcount,modifiedtime,modifier,name,parent_id,pk_group,pk_org,ruletype,showorder,ts,comp) values('0001ZZZZb60cb591c8fb','81H1_B','2021-07-02 14:38:31','~',0,0,10,null,'~','~','0001ZZZZ016d1abb67c3','~','~',0,null,'2021-07-02 14:38:31',null);
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZ11580490912d',null,'2021-07-02 14:38:31','~',null,0,'2021-07-02 14:38:31','~','0001ZZZZf017bb2a3c2c','2ee48597-c5ef-4cb4-af8c-f529fb3bd3d1','2021-07-02 14:38:31','vdef');
go

insert into bd_userdefruleref(pk_userdefruleref,checkclass,creationtime,creator,dataoriginflag,dr,modifiedtime,modifier,pk_userdefrule,refclass,ts,prefix) values('0001ZZZZeba459a211a9',null,'2021-07-02 14:38:31','~',null,0,null,'~','0001ZZZZb60cb591c8fb','9055de19-24dc-433c-9905-2ff913cc01c8','2021-07-02 14:38:31','vbdef');
go

