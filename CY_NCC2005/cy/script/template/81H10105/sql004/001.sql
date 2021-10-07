
delete from pub_msgtemp where typecode = 'XSDA';
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,pk_temptype) values('0001ZZZZe16b20aa41c1',0,'simpchn','@&m submitter.user_name@提交的学生档案待审批','GLOBLE00000000000000',null,'XSDAT','学生档案审批信息','~','<div class="divtext">
<span class="labeltext">财务组织:<span class="normaltext">@&m pk_org.name@</span></span>
</div>
<div class="divtext">
<span class="labeltext">单据编号:<span class="normaltext">@&m billno@</span></span>
</div>','2021-07-01 16:11:56','XSDA',0,0,'~','0001ZZZZ160eabd9544f');
go

delete from pub_msgtemp_type where tempcode = 'WF_cy';
go
insert into pub_msgtemp_type(pk_temptype,dr,innercode,metaid,name,parentpk,tempcode,ts,comp) values('0001ZZZZb961d140c51c',0,'DTUQC98Y','~','~','1004Z010000000005ALB','WF_cy','2021-07-01 16:11:56',null);
go



