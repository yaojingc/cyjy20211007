
delete from pub_msgtemp where typecode = 'LSSQ';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZfcef43b20db9',0,'simpchn','@&m submitter.user_name@提交的老师申请单待审批','GLOBLE00000000000000',null,'LSSQT','老师申请单审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-07-06 09:14:25','LSSQ',0,0,'~','0001ZZZZ36ff91eaf8df');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZ6570002f6824',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-07-06 09:14:25',null);
go



