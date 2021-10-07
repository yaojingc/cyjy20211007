
delete from pub_msgtemp where typecode = 'XXZZ';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZbd92cb1e519f',0,'simpchn','@&m submitter.user_name@提交的消息中转站待审批','GLOBLE00000000000000',null,'XXZZT','消息中转站审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-07-19 10:24:42','XXZZ',0,0,'~','0001ZZZZ52d3fadbf836');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZc6731de0d3d9',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-07-19 10:24:42',null);
go



