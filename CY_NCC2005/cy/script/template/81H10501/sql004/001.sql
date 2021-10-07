
delete from pub_msgtemp where typecode = 'XXSQ';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZ9abec9509d94',0,'simpchn','@&m submitter.user_name@提交的学校申请单待审批','GLOBLE00000000000000',null,'XXSQT','学校申请单审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-06-23 14:36:45','XXSQ',0,0,'~','0001ZZZZe6d55debbe48');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZ724e59894a93',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-06-23 14:36:45',null);
go



