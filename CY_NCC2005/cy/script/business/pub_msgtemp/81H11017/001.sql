
delete from pub_msgtemp where typecode = 'XSZN';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZbdf7f86ec499',0,'simpchn','@&m submitter.user_name@提交的新手指南待审批','GLOBLE00000000000000',null,'XSZNT','新手指南审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-07-19 13:52:06','XSZN',0,0,'~','0001ZZZZ0284e4afbc17');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZf5706a23f176',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-07-19 13:52:06',null);
go



