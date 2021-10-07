
delete from pub_msgtemp where typecode = 'KQJD';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZb8bfa25edaaf',0,'simpchn','@&m submitter.user_name@提交的客情接待申请单待审批','GLOBLE00000000000000',null,'KQJDT','客情接待申请单审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-07-19 11:53:23','KQJD',0,0,'~','0001ZZZZ98e4cf16e9ee');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZ269ffed1788c',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-07-19 11:53:23',null);
go



