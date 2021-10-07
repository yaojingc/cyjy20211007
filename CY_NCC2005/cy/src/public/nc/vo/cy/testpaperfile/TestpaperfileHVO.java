package nc.vo.cy.testpaperfile;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 
 * 试卷档案
 *
 */
public class TestpaperfileHVO extends SuperVO {

	private static final long serialVersionUID = 1L;
	public java.lang.String pk_testpaperfile;
	public java.lang.String testname;
	public java.lang.String testcode;
	public java.lang.Integer total;
	public java.lang.Integer num;
	public nc.vo.pub.lang.UFDouble totalscore1;
	public nc.vo.pub.lang.UFDouble totalscore2;
	public nc.vo.pub.lang.UFDouble totalscore3;
	public nc.vo.pub.lang.UFDouble totalscore4;
	public nc.vo.pub.lang.UFDouble totalscore5;
	public nc.vo.pub.lang.UFDouble totalscore6;
	public nc.vo.pub.lang.UFDouble totalscore7;
	public nc.vo.pub.lang.UFDouble totalscore8;
	public nc.vo.pub.lang.UFDouble totalscore9;
	public nc.vo.pub.lang.UFDouble totalscore10;
	public nc.vo.pub.lang.UFDouble writescore;
	public java.lang.String pk_group;
	public java.lang.String pk_org;
	public java.lang.String pk_org_v;
	public UFDate dbilldate;
	public java.lang.Integer billstatus;
	public java.lang.String bill_no;
	public java.lang.String pk_busitype;
	public java.lang.String pk_billtypeid;
	public java.lang.String billtype;
	public java.lang.String transtypecode;
	public java.lang.String pk_transtype;
	public java.lang.String billmaker;
	public UFDate maketime;
	public java.lang.String creator;
	public UFDateTime creationtime;
	public java.lang.String modifier;
	public UFDateTime modifiedtime;
	public java.lang.String approver;
	public java.lang.Integer approvestatus;
	public java.lang.String approvenote;
	public UFDateTime approvedate;
	public java.lang.String vnote;
	public java.lang.String srcbilltype;
	public java.lang.String srcbillid;
	public java.lang.String srccode;
	public java.lang.String srcid;
	public java.lang.String srcbid;
	public java.lang.String srcrowno;
	public java.lang.String srctrantype;
	public java.lang.String vfirsttype;
	public java.lang.String vfirstcode;
	public java.lang.String vfirstid;
	public java.lang.String vfirstbid0;
	public java.lang.String vfirstrowno;
	public java.lang.String vfirsttrantype;
	public java.lang.String def1;
	public java.lang.String def2;
	public java.lang.String def3;
	public java.lang.String def4;
	public java.lang.String def5;
	public java.lang.String def6;
	public java.lang.String def7;
	public java.lang.String def8;
	public java.lang.String def9;
	public java.lang.String def10;
	public java.lang.String def11;
	public java.lang.String def12;
	public java.lang.String def13;
	public java.lang.String def14;
	public java.lang.String def15;
	public java.lang.String def16;
	public java.lang.String def17;
	public java.lang.String def18;
	public java.lang.String def19;
	public java.lang.String def20;
	public java.lang.String def21;
	public java.lang.String def22;
	public java.lang.String def23;
	public java.lang.String def24;
	public java.lang.String def25;
	public java.lang.String def26;
	public java.lang.String def27;
	public java.lang.String def28;
	public java.lang.String def29;
	public java.lang.String def30;
	public nc.vo.pub.lang.UFDouble line1;
	public nc.vo.pub.lang.UFDouble line2;
	public nc.vo.pub.lang.UFDouble line3;
	public nc.vo.pub.lang.UFDouble line4;
	public UFDateTime ts;

	public java.lang.String getPk_testpaperfile() {
		return this.pk_testpaperfile;
	}

	public void setPk_testpaperfile(java.lang.String pk_testpaperfile) {
		this.pk_testpaperfile = pk_testpaperfile;
	}

	public java.lang.String getTestname() {
		return this.testname;
	}

	public void setTestname(java.lang.String testname) {
		this.testname = testname;
	}

	public java.lang.String getTestcode() {
		return this.testcode;
	}

	public void setTestcode(java.lang.String testcode) {
		this.testcode = testcode;
	}

	public java.lang.Integer getTotal() {
		return this.total;
	}

	public void setTotal(java.lang.Integer total) {
		this.total = total;
	}

	public java.lang.Integer getNum() {
		return this.num;
	}

	public void setNum(java.lang.Integer num) {
		this.num = num;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore1() {
		return this.totalscore1;
	}

	public void setTotalscore1(nc.vo.pub.lang.UFDouble totalscore1) {
		this.totalscore1 = totalscore1;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore2() {
		return this.totalscore2;
	}

	public void setTotalscore2(nc.vo.pub.lang.UFDouble totalscore2) {
		this.totalscore2 = totalscore2;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore3() {
		return this.totalscore3;
	}

	public void setTotalscore3(nc.vo.pub.lang.UFDouble totalscore3) {
		this.totalscore3 = totalscore3;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore4() {
		return this.totalscore4;
	}

	public void setTotalscore4(nc.vo.pub.lang.UFDouble totalscore4) {
		this.totalscore4 = totalscore4;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore5() {
		return this.totalscore5;
	}

	public void setTotalscore5(nc.vo.pub.lang.UFDouble totalscore5) {
		this.totalscore5 = totalscore5;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore6() {
		return this.totalscore6;
	}

	public void setTotalscore6(nc.vo.pub.lang.UFDouble totalscore6) {
		this.totalscore6 = totalscore6;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore7() {
		return this.totalscore7;
	}

	public void setTotalscore7(nc.vo.pub.lang.UFDouble totalscore7) {
		this.totalscore7 = totalscore7;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore8() {
		return this.totalscore8;
	}

	public void setTotalscore8(nc.vo.pub.lang.UFDouble totalscore8) {
		this.totalscore8 = totalscore8;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore9() {
		return this.totalscore9;
	}

	public void setTotalscore9(nc.vo.pub.lang.UFDouble totalscore9) {
		this.totalscore9 = totalscore9;
	}

	public nc.vo.pub.lang.UFDouble getTotalscore10() {
		return this.totalscore10;
	}

	public void setTotalscore10(nc.vo.pub.lang.UFDouble totalscore10) {
		this.totalscore10 = totalscore10;
	}

	public nc.vo.pub.lang.UFDouble getWritescore() {
		return this.writescore;
	}

	public void setWritescore(nc.vo.pub.lang.UFDouble writescore) {
		this.writescore = writescore;
	}

	public java.lang.String getPk_group() {
		return this.pk_group;
	}

	public void setPk_group(java.lang.String pk_group) {
		this.pk_group = pk_group;
	}

	public java.lang.String getPk_org() {
		return this.pk_org;
	}

	public void setPk_org(java.lang.String pk_org) {
		this.pk_org = pk_org;
	}

	public java.lang.String getPk_org_v() {
		return this.pk_org_v;
	}

	public void setPk_org_v(java.lang.String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	public UFDate getDbilldate() {
		return this.dbilldate;
	}

	public void setDbilldate(UFDate dbilldate) {
		this.dbilldate = dbilldate;
	}

	public java.lang.Integer getBillstatus() {
		return this.billstatus;
	}

	public void setBillstatus(java.lang.Integer billstatus) {
		this.billstatus = billstatus;
	}

	public java.lang.String getBill_no() {
		return this.bill_no;
	}

	public void setBill_no(java.lang.String bill_no) {
		this.bill_no = bill_no;
	}

	public java.lang.String getPk_busitype() {
		return this.pk_busitype;
	}

	public void setPk_busitype(java.lang.String pk_busitype) {
		this.pk_busitype = pk_busitype;
	}

	public java.lang.String getPk_billtypeid() {
		return this.pk_billtypeid;
	}

	public void setPk_billtypeid(java.lang.String pk_billtypeid) {
		this.pk_billtypeid = pk_billtypeid;
	}

	public java.lang.String getBilltype() {
		return this.billtype;
	}

	public void setBilltype(java.lang.String billtype) {
		this.billtype = billtype;
	}

	public java.lang.String getTranstypecode() {
		return this.transtypecode;
	}

	public void setTranstypecode(java.lang.String transtypecode) {
		this.transtypecode = transtypecode;
	}

	public java.lang.String getPk_transtype() {
		return this.pk_transtype;
	}

	public void setPk_transtype(java.lang.String pk_transtype) {
		this.pk_transtype = pk_transtype;
	}

	public java.lang.String getBillmaker() {
		return this.billmaker;
	}

	public void setBillmaker(java.lang.String billmaker) {
		this.billmaker = billmaker;
	}

	public UFDate getMaketime() {
		return this.maketime;
	}

	public void setMaketime(UFDate maketime) {
		this.maketime = maketime;
	}

	public java.lang.String getCreator() {
		return this.creator;
	}

	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}

	public UFDateTime getCreationtime() {
		return this.creationtime;
	}

	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	public java.lang.String getModifier() {
		return this.modifier;
	}

	public void setModifier(java.lang.String modifier) {
		this.modifier = modifier;
	}

	public UFDateTime getModifiedtime() {
		return this.modifiedtime;
	}

	public void setModifiedtime(UFDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public java.lang.String getApprover() {
		return this.approver;
	}

	public void setApprover(java.lang.String approver) {
		this.approver = approver;
	}

	public java.lang.Integer getApprovestatus() {
		return this.approvestatus;
	}

	public void setApprovestatus(java.lang.Integer approvestatus) {
		this.approvestatus = approvestatus;
	}

	public java.lang.String getApprovenote() {
		return this.approvenote;
	}

	public void setApprovenote(java.lang.String approvenote) {
		this.approvenote = approvenote;
	}

	public UFDateTime getApprovedate() {
		return this.approvedate;
	}

	public void setApprovedate(UFDateTime approvedate) {
		this.approvedate = approvedate;
	}

	public java.lang.String getVnote() {
		return this.vnote;
	}

	public void setVnote(java.lang.String vnote) {
		this.vnote = vnote;
	}

	public java.lang.String getSrcbilltype() {
		return this.srcbilltype;
	}

	public void setSrcbilltype(java.lang.String srcbilltype) {
		this.srcbilltype = srcbilltype;
	}

	public java.lang.String getSrcbillid() {
		return this.srcbillid;
	}

	public void setSrcbillid(java.lang.String srcbillid) {
		this.srcbillid = srcbillid;
	}

	public java.lang.String getSrccode() {
		return this.srccode;
	}

	public void setSrccode(java.lang.String srccode) {
		this.srccode = srccode;
	}

	public java.lang.String getSrcid() {
		return this.srcid;
	}

	public void setSrcid(java.lang.String srcid) {
		this.srcid = srcid;
	}

	public java.lang.String getSrcbid() {
		return this.srcbid;
	}

	public void setSrcbid(java.lang.String srcbid) {
		this.srcbid = srcbid;
	}

	public java.lang.String getSrcrowno() {
		return this.srcrowno;
	}

	public void setSrcrowno(java.lang.String srcrowno) {
		this.srcrowno = srcrowno;
	}

	public java.lang.String getSrctrantype() {
		return this.srctrantype;
	}

	public void setSrctrantype(java.lang.String srctrantype) {
		this.srctrantype = srctrantype;
	}

	public java.lang.String getVfirsttype() {
		return this.vfirsttype;
	}

	public void setVfirsttype(java.lang.String vfirsttype) {
		this.vfirsttype = vfirsttype;
	}

	public java.lang.String getVfirstcode() {
		return this.vfirstcode;
	}

	public void setVfirstcode(java.lang.String vfirstcode) {
		this.vfirstcode = vfirstcode;
	}

	public java.lang.String getVfirstid() {
		return this.vfirstid;
	}

	public void setVfirstid(java.lang.String vfirstid) {
		this.vfirstid = vfirstid;
	}

	public java.lang.String getVfirstbid0() {
		return this.vfirstbid0;
	}

	public void setVfirstbid0(java.lang.String vfirstbid0) {
		this.vfirstbid0 = vfirstbid0;
	}

	public java.lang.String getVfirstrowno() {
		return this.vfirstrowno;
	}

	public void setVfirstrowno(java.lang.String vfirstrowno) {
		this.vfirstrowno = vfirstrowno;
	}

	public java.lang.String getVfirsttrantype() {
		return this.vfirsttrantype;
	}

	public void setVfirsttrantype(java.lang.String vfirsttrantype) {
		this.vfirsttrantype = vfirsttrantype;
	}

	public java.lang.String getDef1() {
		return this.def1;
	}

	public void setDef1(java.lang.String def1) {
		this.def1 = def1;
	}

	public java.lang.String getDef2() {
		return this.def2;
	}

	public void setDef2(java.lang.String def2) {
		this.def2 = def2;
	}

	public java.lang.String getDef3() {
		return this.def3;
	}

	public void setDef3(java.lang.String def3) {
		this.def3 = def3;
	}

	public java.lang.String getDef4() {
		return this.def4;
	}

	public void setDef4(java.lang.String def4) {
		this.def4 = def4;
	}

	public java.lang.String getDef5() {
		return this.def5;
	}

	public void setDef5(java.lang.String def5) {
		this.def5 = def5;
	}

	public java.lang.String getDef6() {
		return this.def6;
	}

	public void setDef6(java.lang.String def6) {
		this.def6 = def6;
	}

	public java.lang.String getDef7() {
		return this.def7;
	}

	public void setDef7(java.lang.String def7) {
		this.def7 = def7;
	}

	public java.lang.String getDef8() {
		return this.def8;
	}

	public void setDef8(java.lang.String def8) {
		this.def8 = def8;
	}

	public java.lang.String getDef9() {
		return this.def9;
	}

	public void setDef9(java.lang.String def9) {
		this.def9 = def9;
	}

	public java.lang.String getDef10() {
		return this.def10;
	}

	public void setDef10(java.lang.String def10) {
		this.def10 = def10;
	}

	public java.lang.String getDef11() {
		return this.def11;
	}

	public void setDef11(java.lang.String def11) {
		this.def11 = def11;
	}

	public java.lang.String getDef12() {
		return this.def12;
	}

	public void setDef12(java.lang.String def12) {
		this.def12 = def12;
	}

	public java.lang.String getDef13() {
		return this.def13;
	}

	public void setDef13(java.lang.String def13) {
		this.def13 = def13;
	}

	public java.lang.String getDef14() {
		return this.def14;
	}

	public void setDef14(java.lang.String def14) {
		this.def14 = def14;
	}

	public java.lang.String getDef15() {
		return this.def15;
	}

	public void setDef15(java.lang.String def15) {
		this.def15 = def15;
	}

	public java.lang.String getDef16() {
		return this.def16;
	}

	public void setDef16(java.lang.String def16) {
		this.def16 = def16;
	}

	public java.lang.String getDef17() {
		return this.def17;
	}

	public void setDef17(java.lang.String def17) {
		this.def17 = def17;
	}

	public java.lang.String getDef18() {
		return this.def18;
	}

	public void setDef18(java.lang.String def18) {
		this.def18 = def18;
	}

	public java.lang.String getDef19() {
		return this.def19;
	}

	public void setDef19(java.lang.String def19) {
		this.def19 = def19;
	}

	public java.lang.String getDef20() {
		return this.def20;
	}

	public void setDef20(java.lang.String def20) {
		this.def20 = def20;
	}

	public java.lang.String getDef21() {
		return this.def21;
	}

	public void setDef21(java.lang.String def21) {
		this.def21 = def21;
	}

	public java.lang.String getDef22() {
		return this.def22;
	}

	public void setDef22(java.lang.String def22) {
		this.def22 = def22;
	}

	public java.lang.String getDef23() {
		return this.def23;
	}

	public void setDef23(java.lang.String def23) {
		this.def23 = def23;
	}

	public java.lang.String getDef24() {
		return this.def24;
	}

	public void setDef24(java.lang.String def24) {
		this.def24 = def24;
	}

	public java.lang.String getDef25() {
		return this.def25;
	}

	public void setDef25(java.lang.String def25) {
		this.def25 = def25;
	}

	public java.lang.String getDef26() {
		return this.def26;
	}

	public void setDef26(java.lang.String def26) {
		this.def26 = def26;
	}

	public java.lang.String getDef27() {
		return this.def27;
	}

	public void setDef27(java.lang.String def27) {
		this.def27 = def27;
	}

	public java.lang.String getDef28() {
		return this.def28;
	}

	public void setDef28(java.lang.String def28) {
		this.def28 = def28;
	}

	public java.lang.String getDef29() {
		return this.def29;
	}

	public void setDef29(java.lang.String def29) {
		this.def29 = def29;
	}

	public java.lang.String getDef30() {
		return this.def30;
	}

	public void setDef30(java.lang.String def30) {
		this.def30 = def30;
	}

	public nc.vo.pub.lang.UFDouble getLine1() {
		return this.line1;
	}

	public void setLine1(nc.vo.pub.lang.UFDouble line1) {
		this.line1 = line1;
	}

	public nc.vo.pub.lang.UFDouble getLine2() {
		return this.line2;
	}

	public void setLine2(nc.vo.pub.lang.UFDouble line2) {
		this.line2 = line2;
	}

	public nc.vo.pub.lang.UFDouble getLine3() {
		return this.line3;
	}

	public void setLine3(nc.vo.pub.lang.UFDouble line3) {
		this.line3 = line3;
	}

	public nc.vo.pub.lang.UFDouble getLine4() {
		return this.line4;
	}

	public void setLine4(nc.vo.pub.lang.UFDouble line4) {
		this.line4 = line4;
	}

	public UFDateTime getTs() {
		return this.ts;
	}

	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("cy.testpaperfileHVO");
	}
}
