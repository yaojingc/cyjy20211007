
delete from pub_msgtemp where typecode = 'CYBJ';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZ11e70e511587',0,'simpchn','@&m submitter.user_name@提交的词源班级档案待审批','GLOBLE00000000000000',null,'CYBJT','词源班级档案审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-06-23 09:36:36','CYBJ',0,0,'~','0001ZZZZd8c5621613ae');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZ7639e378cae4',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-06-23 09:36:36',null);
go



