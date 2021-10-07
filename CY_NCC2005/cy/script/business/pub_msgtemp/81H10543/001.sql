
delete from pub_msgtemp where typecode = 'XFJM';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZ05ee27c92296',0,'simpchn','@&m submitter.user_name@提交的学费减免申请单待审批','GLOBLE00000000000000',null,'XFJMT','学费减免申请单审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-07-19 14:33:45','XFJM',0,0,'~','0001ZZZZ016476ffa734');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZ37edc8a2511b',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-07-19 14:33:45',null);
go



