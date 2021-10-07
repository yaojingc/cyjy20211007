
delete from pub_msgtemp where typecode = 'SKHT';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZ53240f47fce3',0,'simpchn','@&m submitter.user_name@提交的学费收款合同待审批','GLOBLE00000000000000',null,'SKHTT','学费收款合同审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-06-23 10:38:23','SKHT',0,0,'~','0001ZZZZ4a85a0fe1b87');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZ782b863d0098',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-06-23 10:38:23',null);
go



