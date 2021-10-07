package nc.vo.cy.studentfile;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 * �˴�����۵�������Ϣ
 * </p>
 * ��������:2021-7-21
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class StudentHVO extends SuperVO {
	
	/**
	 * 身份证号 数据库身份证号唯一性约束脚本
	 * alter table cy_student add constraint stuidcard_unique unique (idcard);  
	 */

	/**
	 * ѧ������
	 */
	public java.lang.String pk_student;
	/**
	 * ����
	 */
	public java.lang.String pk_group;
	/**
	 * ��֯
	 */
	public java.lang.String pk_org;
	/**
	 * ��֯�汾
	 */
	public java.lang.String pk_org_v;
	/**
	 * ��������
	 */
	public UFDate dbilldate;
	/**
	 * ����״̬
	 */
	public java.lang.Integer billstatus;
	/**
	 * ���ݺ�
	 */
	public java.lang.String bill_no;
	/**
	 * ҵ������
	 */
	public java.lang.String pk_busitype;
	/**
	 * ��������
	 */
	public java.lang.String pk_billtypeid;
	/**
	 * �������ͱ���
	 */
	public java.lang.String billtype;
	/**
	 * �������ͱ���
	 */
	public java.lang.String transtypecode;
	/**
	 * ��������
	 */
	public java.lang.String pk_transtype;
	/**
	 * �Ƶ���
	 */
	public java.lang.String billmaker;
	/**
	 * �Ƶ�ʱ��
	 */
	public UFDate maketime;
	/**
	 * ������
	 */
	public java.lang.String creator;
	/**
	 * ����ʱ��
	 */
	public UFDateTime creationtime;
	/**
	 * �޸���
	 */
	public java.lang.String modifier;
	/**
	 * �޸�ʱ��
	 */
	public UFDateTime modifiedtime;
	/**
	 * ������
	 */
	public java.lang.String approver;
	/**
	 * ����״̬
	 */
	public java.lang.Integer approvestatus;
	/**
	 * ��������
	 */
	public java.lang.String approvenote;
	/**
	 * ����ʱ��
	 */
	public UFDateTime approvedate;
	/**
	 * ��ע
	 */
	public java.lang.String vnote;
	/**
	 * ��Դ��������
	 */
	public java.lang.String srcbilltype;
	/**
	 * ��Դ����id
	 */
	public java.lang.String srcbillid;
	/**
	 * ��Դ���ݺ�
	 */
	public java.lang.String srccode;
	/**
	 * ��Դ��������id
	 */
	public java.lang.String srcid;
	/**
	 * ��Դ�����ӱ�id
	 */
	public java.lang.String srcbid;
	/**
	 * ��Դ�����к�
	 */
	public java.lang.String srcrowno;
	/**
	 * ��Դ��������
	 */
	public java.lang.String srctrantype;
	/**
	 * Դͷ��������
	 */
	public java.lang.String vfirsttype;
	/**
	 * Դͷ���ݺ�
	 */
	public java.lang.String vfirstcode;
	/**
	 * Դͷ��������id
	 */
	public java.lang.String vfirstid;
	/**
	 * Դͷ�����ӱ�id
	 */
	public java.lang.String vfirstbid;
	/**
	 * Դͷ�����к�
	 */
	public java.lang.String vfirstrowno;
	/**
	 * Դͷ��������
	 */
	public java.lang.String vfirsttrantype;
	/**
	 * 班级
	 */
	public java.lang.String def1;
	/**
	 * 学校
	 */
	public java.lang.String def2;
	/**
	 * 是否生成学生合同
	 */
	public java.lang.String def3;
	/**
	 * �Զ�����4
	 */
	public java.lang.String def4;
	/**
	 * �Զ�����5
	 */
	public java.lang.String def5;
	/**
	 * �Զ�����6
	 */
	public java.lang.String def6;
	/**
	 * �Զ�����7
	 */
	public java.lang.String def7;
	/**
	 * �Զ�����8
	 */
	public java.lang.String def8;
	/**
	 * �Զ�����9
	 */
	public java.lang.String def9;
	/**
	 * �Զ�����10
	 */
	public java.lang.String def10;
	/**
	 * �Զ�����11
	 */
	public java.lang.String def11;
	/**
	 * �Զ�����12
	 */
	public java.lang.String def12;
	/**
	 * �Զ�����13
	 */
	public java.lang.String def13;
	/**
	 * �Զ�����14
	 */
	public java.lang.String def14;
	/**
	 * �Զ�����15
	 */
	public java.lang.String def15;
	/**
	 * �Զ�����16
	 */
	public java.lang.String def16;
	/**
	 * �Զ�����17
	 */
	public java.lang.String def17;
	/**
	 * �Զ�����18
	 */
	public java.lang.String def18;
	/**
	 * �Զ�����19
	 */
	public java.lang.String def19;
	/**
	 * �Զ�����20
	 */
	public java.lang.String def20;
	/**
	 * �Զ�����21
	 */
	public java.lang.String def21;
	/**
	 * �Զ�����22
	 */
	public java.lang.String def22;
	/**
	 * �Զ�����23
	 */
	public java.lang.String def23;
	/**
	 * �Զ�����24
	 */
	public java.lang.String def24;
	/**
	 * �Զ�����25
	 */
	public java.lang.String def25;
	/**
	 * �Զ�����26
	 */
	public java.lang.String def26;
	/**
	 * �Զ�����27
	 */
	public java.lang.String def27;
	/**
	 * �Զ�����28
	 */
	public java.lang.String def28;
	/**
	 * �Զ�����29
	 */
	public java.lang.String def29;
	/**
	 * �Զ�����30
	 */
	public java.lang.String def30;
	/**
	 * ѧ������ѧУ����
	 */
	public java.lang.String schoolname;
	/**
	 * 身份证号
	 * 数据库身份证号唯一性约束脚本
	 * alter table cy_student add constraint  stuidcard_unique unique (idcard);  
	 */
	public java.lang.String idcard;
	/**
	 * ѧ������
	 */
	public java.lang.String studenttype;
	/**
	 * ����
	 */
	public java.lang.String email;
	/**
	 * �Ƿ�����
	 */
	public UFBoolean isuseing;
	/**
	 * ����
	 */
	public java.lang.String region;
	/**
	 * ʡ��
	 */
	public java.lang.String province;
	/**
	 * ��ǰ�����꼶
	 */
	public java.lang.String inthegrade;
	/**
	 * ��Դ�༶
	 */
	public java.lang.String cygrade;
	/**
	 * �����༶
	 */
	public java.lang.String xzgrade;
	/**
	 * ѧ������
	 */
	public java.lang.String sname;
	/**
	 * �Ա�
	 */
	public java.lang.Integer sex;
	/**
	 * ʱ���
	 */
	public UFDateTime ts;

	/**
	 * ���� pk_student��Getter����.��������ѧ������ ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_student() {
		return this.pk_student;
	}

	/**
	 * ����pk_student��Setter����.��������ѧ������ ��������:2021-7-21
	 * 
	 * @param newPk_student java.lang.String
	 */
	public void setPk_student(java.lang.String pk_student) {
		this.pk_student = pk_student;
	}

	/**
	 * ���� pk_group��Getter����.������������ ��������:2021-7-21
	 * 
	 * @return nc.vo.org.GroupVO
	 */
	public java.lang.String getPk_group() {
		return this.pk_group;
	}

	/**
	 * ����pk_group��Setter����.������������ ��������:2021-7-21
	 * 
	 * @param newPk_group nc.vo.org.GroupVO
	 */
	public void setPk_group(java.lang.String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * ���� pk_org��Getter����.����������֯ ��������:2021-7-21
	 * 
	 * @return nc.vo.org.OrgVO
	 */
	public java.lang.String getPk_org() {
		return this.pk_org;
	}

	/**
	 * ����pk_org��Setter����.����������֯ ��������:2021-7-21
	 * 
	 * @param newPk_org nc.vo.org.OrgVO
	 */
	public void setPk_org(java.lang.String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * ���� pk_org_v��Getter����.����������֯�汾 ��������:2021-7-21
	 * 
	 * @return nc.vo.vorg.OrgVersionVO
	 */
	public java.lang.String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * ����pk_org_v��Setter����.����������֯�汾 ��������:2021-7-21
	 * 
	 * @param newPk_org_v nc.vo.vorg.OrgVersionVO
	 */
	public void setPk_org_v(java.lang.String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * ���� dbilldate��Getter����.���������������� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public UFDate getDbilldate() {
		return this.dbilldate;
	}

	/**
	 * ����dbilldate��Setter����.���������������� ��������:2021-7-21
	 * 
	 * @param newDbilldate nc.vo.pub.lang.UFDate
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.dbilldate = dbilldate;
	}

	/**
	 * ���� billstatus��Getter����.������������״̬ ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.pf.BillStatusEnum
	 */
	public java.lang.Integer getBillstatus() {
		return this.billstatus;
	}

	/**
	 * ����billstatus��Setter����.������������״̬ ��������:2021-7-21
	 * 
	 * @param newBillstatus nc.vo.pub.pf.BillStatusEnum
	 */
	public void setBillstatus(java.lang.Integer billstatus) {
		this.billstatus = billstatus;
	}

	/**
	 * ���� bill_no��Getter����.�����������ݺ� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getBill_no() {
		return this.bill_no;
	}

	/**
	 * ����bill_no��Setter����.�����������ݺ� ��������:2021-7-21
	 * 
	 * @param newBill_no java.lang.String
	 */
	public void setBill_no(java.lang.String bill_no) {
		this.bill_no = bill_no;
	}

	/**
	 * ���� pk_busitype��Getter����.��������ҵ������ ��������:2021-7-21
	 * 
	 * @return nc.vo.pf.pub.BusitypeVO
	 */
	public java.lang.String getPk_busitype() {
		return this.pk_busitype;
	}

	/**
	 * ����pk_busitype��Setter����.��������ҵ������ ��������:2021-7-21
	 * 
	 * @param newPk_busitype nc.vo.pf.pub.BusitypeVO
	 */
	public void setPk_busitype(java.lang.String pk_busitype) {
		this.pk_busitype = pk_busitype;
	}

	/**
	 * ���� pk_billtypeid��Getter����.���������������� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.billtype.BilltypeVO
	 */
	public java.lang.String getPk_billtypeid() {
		return this.pk_billtypeid;
	}

	/**
	 * ����pk_billtypeid��Setter����.���������������� ��������:2021-7-21
	 * 
	 * @param newPk_billtypeid nc.vo.pub.billtype.BilltypeVO
	 */
	public void setPk_billtypeid(java.lang.String pk_billtypeid) {
		this.pk_billtypeid = pk_billtypeid;
	}

	/**
	 * ���� billtype��Getter����.���������������ͱ��� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getBilltype() {
		return this.billtype;
	}

	/**
	 * ����billtype��Setter����.���������������ͱ��� ��������:2021-7-21
	 * 
	 * @param newBilltype java.lang.String
	 */
	public void setBilltype(java.lang.String billtype) {
		this.billtype = billtype;
	}

	/**
	 * ���� transtypecode��Getter����.���������������ͱ��� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTranstypecode() {
		return this.transtypecode;
	}

	/**
	 * ����transtypecode��Setter����.���������������ͱ��� ��������:2021-7-21
	 * 
	 * @param newTranstypecode java.lang.String
	 */
	public void setTranstypecode(java.lang.String transtypecode) {
		this.transtypecode = transtypecode;
	}

	/**
	 * ���� pk_transtype��Getter����.���������������� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.billtype.BilltypeVO
	 */
	public java.lang.String getPk_transtype() {
		return this.pk_transtype;
	}

	/**
	 * ����pk_transtype��Setter����.���������������� ��������:2021-7-21
	 * 
	 * @param newPk_transtype nc.vo.pub.billtype.BilltypeVO
	 */
	public void setPk_transtype(java.lang.String pk_transtype) {
		this.pk_transtype = pk_transtype;
	}

	/**
	 * ���� billmaker��Getter����.���������Ƶ��� ��������:2021-7-21
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public java.lang.String getBillmaker() {
		return this.billmaker;
	}

	/**
	 * ����billmaker��Setter����.���������Ƶ��� ��������:2021-7-21
	 * 
	 * @param newBillmaker nc.vo.sm.UserVO
	 */
	public void setBillmaker(java.lang.String billmaker) {
		this.billmaker = billmaker;
	}

	/**
	 * ���� maketime��Getter����.���������Ƶ�ʱ�� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public UFDate getMaketime() {
		return this.maketime;
	}

	/**
	 * ����maketime��Setter����.���������Ƶ�ʱ�� ��������:2021-7-21
	 * 
	 * @param newMaketime nc.vo.pub.lang.UFDate
	 */
	public void setMaketime(UFDate maketime) {
		this.maketime = maketime;
	}

	/**
	 * ���� creator��Getter����.�������������� ��������:2021-7-21
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public java.lang.String getCreator() {
		return this.creator;
	}

	/**
	 * ����creator��Setter����.�������������� ��������:2021-7-21
	 * 
	 * @param newCreator nc.vo.sm.UserVO
	 */
	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}

	/**
	 * ���� creationtime��Getter����.������������ʱ�� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getCreationtime() {
		return this.creationtime;
	}

	/**
	 * ����creationtime��Setter����.������������ʱ�� ��������:2021-7-21
	 * 
	 * @param newCreationtime nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	/**
	 * ���� modifier��Getter����.���������޸��� ��������:2021-7-21
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public java.lang.String getModifier() {
		return this.modifier;
	}

	/**
	 * ����modifier��Setter����.���������޸��� ��������:2021-7-21
	 * 
	 * @param newModifier nc.vo.sm.UserVO
	 */
	public void setModifier(java.lang.String modifier) {
		this.modifier = modifier;
	}

	/**
	 * ���� modifiedtime��Getter����.���������޸�ʱ�� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getModifiedtime() {
		return this.modifiedtime;
	}

	/**
	 * ����modifiedtime��Setter����.���������޸�ʱ�� ��������:2021-7-21
	 * 
	 * @param newModifiedtime nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	/**
	 * ���� approver��Getter����.�������������� ��������:2021-7-21
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public java.lang.String getApprover() {
		return this.approver;
	}

	/**
	 * ����approver��Setter����.�������������� ��������:2021-7-21
	 * 
	 * @param newApprover nc.vo.sm.UserVO
	 */
	public void setApprover(java.lang.String approver) {
		this.approver = approver;
	}

	/**
	 * ���� approvestatus��Getter����.������������״̬ ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.pf.BillStatusEnum
	 */
	public java.lang.Integer getApprovestatus() {
		return this.approvestatus;
	}

	/**
	 * ����approvestatus��Setter����.������������״̬ ��������:2021-7-21
	 * 
	 * @param newApprovestatus nc.vo.pub.pf.BillStatusEnum
	 */
	public void setApprovestatus(java.lang.Integer approvestatus) {
		this.approvestatus = approvestatus;
	}

	/**
	 * ���� approvenote��Getter����.���������������� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getApprovenote() {
		return this.approvenote;
	}

	/**
	 * ����approvenote��Setter����.���������������� ��������:2021-7-21
	 * 
	 * @param newApprovenote java.lang.String
	 */
	public void setApprovenote(java.lang.String approvenote) {
		this.approvenote = approvenote;
	}

	/**
	 * ���� approvedate��Getter����.������������ʱ�� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getApprovedate() {
		return this.approvedate;
	}

	/**
	 * ����approvedate��Setter����.������������ʱ�� ��������:2021-7-21
	 * 
	 * @param newApprovedate nc.vo.pub.lang.UFDateTime
	 */
	public void setApprovedate(UFDateTime approvedate) {
		this.approvedate = approvedate;
	}

	/**
	 * ���� vnote��Getter����.����������ע ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVnote() {
		return this.vnote;
	}

	/**
	 * ����vnote��Setter����.����������ע ��������:2021-7-21
	 * 
	 * @param newVnote java.lang.String
	 */
	public void setVnote(java.lang.String vnote) {
		this.vnote = vnote;
	}

	/**
	 * ���� srcbilltype��Getter����.����������Դ�������� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSrcbilltype() {
		return this.srcbilltype;
	}

	/**
	 * ����srcbilltype��Setter����.����������Դ�������� ��������:2021-7-21
	 * 
	 * @param newSrcbilltype java.lang.String
	 */
	public void setSrcbilltype(java.lang.String srcbilltype) {
		this.srcbilltype = srcbilltype;
	}

	/**
	 * ���� srcbillid��Getter����.����������Դ����id ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSrcbillid() {
		return this.srcbillid;
	}

	/**
	 * ����srcbillid��Setter����.����������Դ����id ��������:2021-7-21
	 * 
	 * @param newSrcbillid java.lang.String
	 */
	public void setSrcbillid(java.lang.String srcbillid) {
		this.srcbillid = srcbillid;
	}

	/**
	 * ���� srccode��Getter����.����������Դ���ݺ� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSrccode() {
		return this.srccode;
	}

	/**
	 * ����srccode��Setter����.����������Դ���ݺ� ��������:2021-7-21
	 * 
	 * @param newSrccode java.lang.String
	 */
	public void setSrccode(java.lang.String srccode) {
		this.srccode = srccode;
	}

	/**
	 * ���� srcid��Getter����.����������Դ��������id ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSrcid() {
		return this.srcid;
	}

	/**
	 * ����srcid��Setter����.����������Դ��������id ��������:2021-7-21
	 * 
	 * @param newSrcid java.lang.String
	 */
	public void setSrcid(java.lang.String srcid) {
		this.srcid = srcid;
	}

	/**
	 * ���� srcbid��Getter����.����������Դ�����ӱ�id ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSrcbid() {
		return this.srcbid;
	}

	/**
	 * ����srcbid��Setter����.����������Դ�����ӱ�id ��������:2021-7-21
	 * 
	 * @param newSrcbid java.lang.String
	 */
	public void setSrcbid(java.lang.String srcbid) {
		this.srcbid = srcbid;
	}

	/**
	 * ���� srcrowno��Getter����.����������Դ�����к� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSrcrowno() {
		return this.srcrowno;
	}

	/**
	 * ����srcrowno��Setter����.����������Դ�����к� ��������:2021-7-21
	 * 
	 * @param newSrcrowno java.lang.String
	 */
	public void setSrcrowno(java.lang.String srcrowno) {
		this.srcrowno = srcrowno;
	}

	/**
	 * ���� srctrantype��Getter����.����������Դ�������� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSrctrantype() {
		return this.srctrantype;
	}

	/**
	 * ����srctrantype��Setter����.����������Դ�������� ��������:2021-7-21
	 * 
	 * @param newSrctrantype java.lang.String
	 */
	public void setSrctrantype(java.lang.String srctrantype) {
		this.srctrantype = srctrantype;
	}

	/**
	 * ���� vfirsttype��Getter����.��������Դͷ�������� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVfirsttype() {
		return this.vfirsttype;
	}

	/**
	 * ����vfirsttype��Setter����.��������Դͷ�������� ��������:2021-7-21
	 * 
	 * @param newVfirsttype java.lang.String
	 */
	public void setVfirsttype(java.lang.String vfirsttype) {
		this.vfirsttype = vfirsttype;
	}

	/**
	 * ���� vfirstcode��Getter����.��������Դͷ���ݺ� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVfirstcode() {
		return this.vfirstcode;
	}

	/**
	 * ����vfirstcode��Setter����.��������Դͷ���ݺ� ��������:2021-7-21
	 * 
	 * @param newVfirstcode java.lang.String
	 */
	public void setVfirstcode(java.lang.String vfirstcode) {
		this.vfirstcode = vfirstcode;
	}

	/**
	 * ���� vfirstid��Getter����.��������Դͷ��������id ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVfirstid() {
		return this.vfirstid;
	}

	/**
	 * ����vfirstid��Setter����.��������Դͷ��������id ��������:2021-7-21
	 * 
	 * @param newVfirstid java.lang.String
	 */
	public void setVfirstid(java.lang.String vfirstid) {
		this.vfirstid = vfirstid;
	}

	/**
	 * ���� vfirstbid��Getter����.��������Դͷ�����ӱ�id ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVfirstbid() {
		return this.vfirstbid;
	}

	/**
	 * ����vfirstbid��Setter����.��������Դͷ�����ӱ�id ��������:2021-7-21
	 * 
	 * @param newVfirstbid java.lang.String
	 */
	public void setVfirstbid(java.lang.String vfirstbid) {
		this.vfirstbid = vfirstbid;
	}

	/**
	 * ���� vfirstrowno��Getter����.��������Դͷ�����к� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVfirstrowno() {
		return this.vfirstrowno;
	}

	/**
	 * ����vfirstrowno��Setter����.��������Դͷ�����к� ��������:2021-7-21
	 * 
	 * @param newVfirstrowno java.lang.String
	 */
	public void setVfirstrowno(java.lang.String vfirstrowno) {
		this.vfirstrowno = vfirstrowno;
	}

	/**
	 * ���� vfirsttrantype��Getter����.��������Դͷ�������� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVfirsttrantype() {
		return this.vfirsttrantype;
	}

	/**
	 * ����vfirsttrantype��Setter����.��������Դͷ�������� ��������:2021-7-21
	 * 
	 * @param newVfirsttrantype java.lang.String
	 */
	public void setVfirsttrantype(java.lang.String vfirsttrantype) {
		this.vfirsttrantype = vfirsttrantype;
	}

	/**
	 * 班级
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef1() {
		return this.def1;
	}

	/**
	 * 班级
	 * 
	 * @param newDef1 java.lang.String
	 */
	public void setDef1(java.lang.String def1) {
		this.def1 = def1;
	}

	/**
	 * 学校
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef2() {
		return this.def2;
	}

	/**
	 * 学校
	 * 
	 * @param newDef2 java.lang.String
	 */
	public void setDef2(java.lang.String def2) {
		this.def2 = def2;
	}

	/**
	* 是否生成学生合同
	 */
	public java.lang.String getDef3() {
		return this.def3;
	}

	/**
	*是否生成学生合同
	 */
	public void setDef3(java.lang.String def3) {
		this.def3 = def3;
	}

	/**
	 * ���� def4��Getter����.���������Զ�����4 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef4() {
		return this.def4;
	}

	/**
	 * ����def4��Setter����.���������Զ�����4 ��������:2021-7-21
	 * 
	 * @param newDef4 java.lang.String
	 */
	public void setDef4(java.lang.String def4) {
		this.def4 = def4;
	}

	/**
	 * ���� def5��Getter����.���������Զ�����5 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef5() {
		return this.def5;
	}

	/**
	 * ����def5��Setter����.���������Զ�����5 ��������:2021-7-21
	 * 
	 * @param newDef5 java.lang.String
	 */
	public void setDef5(java.lang.String def5) {
		this.def5 = def5;
	}

	/**
	 * ���� def6��Getter����.���������Զ�����6 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef6() {
		return this.def6;
	}

	/**
	 * ����def6��Setter����.���������Զ�����6 ��������:2021-7-21
	 * 
	 * @param newDef6 java.lang.String
	 */
	public void setDef6(java.lang.String def6) {
		this.def6 = def6;
	}

	/**
	 * ���� def7��Getter����.���������Զ�����7 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef7() {
		return this.def7;
	}

	/**
	 * ����def7��Setter����.���������Զ�����7 ��������:2021-7-21
	 * 
	 * @param newDef7 java.lang.String
	 */
	public void setDef7(java.lang.String def7) {
		this.def7 = def7;
	}

	/**
	 * ���� def8��Getter����.���������Զ�����8 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef8() {
		return this.def8;
	}

	/**
	 * ����def8��Setter����.���������Զ�����8 ��������:2021-7-21
	 * 
	 * @param newDef8 java.lang.String
	 */
	public void setDef8(java.lang.String def8) {
		this.def8 = def8;
	}

	/**
	 * ���� def9��Getter����.���������Զ�����9 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef9() {
		return this.def9;
	}

	/**
	 * ����def9��Setter����.���������Զ�����9 ��������:2021-7-21
	 * 
	 * @param newDef9 java.lang.String
	 */
	public void setDef9(java.lang.String def9) {
		this.def9 = def9;
	}

	/**
	 * ���� def10��Getter����.���������Զ�����10 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef10() {
		return this.def10;
	}

	/**
	 * ����def10��Setter����.���������Զ�����10 ��������:2021-7-21
	 * 
	 * @param newDef10 java.lang.String
	 */
	public void setDef10(java.lang.String def10) {
		this.def10 = def10;
	}

	/**
	 * ���� def11��Getter����.���������Զ�����11 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef11() {
		return this.def11;
	}

	/**
	 * ����def11��Setter����.���������Զ�����11 ��������:2021-7-21
	 * 
	 * @param newDef11 java.lang.String
	 */
	public void setDef11(java.lang.String def11) {
		this.def11 = def11;
	}

	/**
	 * ���� def12��Getter����.���������Զ�����12 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef12() {
		return this.def12;
	}

	/**
	 * ����def12��Setter����.���������Զ�����12 ��������:2021-7-21
	 * 
	 * @param newDef12 java.lang.String
	 */
	public void setDef12(java.lang.String def12) {
		this.def12 = def12;
	}

	/**
	 * ���� def13��Getter����.���������Զ�����13 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef13() {
		return this.def13;
	}

	/**
	 * ����def13��Setter����.���������Զ�����13 ��������:2021-7-21
	 * 
	 * @param newDef13 java.lang.String
	 */
	public void setDef13(java.lang.String def13) {
		this.def13 = def13;
	}

	/**
	 * ���� def14��Getter����.���������Զ�����14 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef14() {
		return this.def14;
	}

	/**
	 * ����def14��Setter����.���������Զ�����14 ��������:2021-7-21
	 * 
	 * @param newDef14 java.lang.String
	 */
	public void setDef14(java.lang.String def14) {
		this.def14 = def14;
	}

	/**
	 * ���� def15��Getter����.���������Զ�����15 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef15() {
		return this.def15;
	}

	/**
	 * ����def15��Setter����.���������Զ�����15 ��������:2021-7-21
	 * 
	 * @param newDef15 java.lang.String
	 */
	public void setDef15(java.lang.String def15) {
		this.def15 = def15;
	}

	/**
	 * ���� def16��Getter����.���������Զ�����16 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef16() {
		return this.def16;
	}

	/**
	 * ����def16��Setter����.���������Զ�����16 ��������:2021-7-21
	 * 
	 * @param newDef16 java.lang.String
	 */
	public void setDef16(java.lang.String def16) {
		this.def16 = def16;
	}

	/**
	 * ���� def17��Getter����.���������Զ�����17 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef17() {
		return this.def17;
	}

	/**
	 * ����def17��Setter����.���������Զ�����17 ��������:2021-7-21
	 * 
	 * @param newDef17 java.lang.String
	 */
	public void setDef17(java.lang.String def17) {
		this.def17 = def17;
	}

	/**
	 * ���� def18��Getter����.���������Զ�����18 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef18() {
		return this.def18;
	}

	/**
	 * ����def18��Setter����.���������Զ�����18 ��������:2021-7-21
	 * 
	 * @param newDef18 java.lang.String
	 */
	public void setDef18(java.lang.String def18) {
		this.def18 = def18;
	}

	/**
	 * ���� def19��Getter����.���������Զ�����19 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef19() {
		return this.def19;
	}

	/**
	 * ����def19��Setter����.���������Զ�����19 ��������:2021-7-21
	 * 
	 * @param newDef19 java.lang.String
	 */
	public void setDef19(java.lang.String def19) {
		this.def19 = def19;
	}

	/**
	 * ���� def20��Getter����.���������Զ�����20 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef20() {
		return this.def20;
	}

	/**
	 * ����def20��Setter����.���������Զ�����20 ��������:2021-7-21
	 * 
	 * @param newDef20 java.lang.String
	 */
	public void setDef20(java.lang.String def20) {
		this.def20 = def20;
	}

	/**
	 * ���� def21��Getter����.���������Զ�����21 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef21() {
		return this.def21;
	}

	/**
	 * ����def21��Setter����.���������Զ�����21 ��������:2021-7-21
	 * 
	 * @param newDef21 java.lang.String
	 */
	public void setDef21(java.lang.String def21) {
		this.def21 = def21;
	}

	/**
	 * ���� def22��Getter����.���������Զ�����22 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef22() {
		return this.def22;
	}

	/**
	 * ����def22��Setter����.���������Զ�����22 ��������:2021-7-21
	 * 
	 * @param newDef22 java.lang.String
	 */
	public void setDef22(java.lang.String def22) {
		this.def22 = def22;
	}

	/**
	 * ���� def23��Getter����.���������Զ�����23 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef23() {
		return this.def23;
	}

	/**
	 * ����def23��Setter����.���������Զ�����23 ��������:2021-7-21
	 * 
	 * @param newDef23 java.lang.String
	 */
	public void setDef23(java.lang.String def23) {
		this.def23 = def23;
	}

	/**
	 * ���� def24��Getter����.���������Զ�����24 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef24() {
		return this.def24;
	}

	/**
	 * ����def24��Setter����.���������Զ�����24 ��������:2021-7-21
	 * 
	 * @param newDef24 java.lang.String
	 */
	public void setDef24(java.lang.String def24) {
		this.def24 = def24;
	}

	/**
	 * ���� def25��Getter����.���������Զ�����25 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef25() {
		return this.def25;
	}

	/**
	 * ����def25��Setter����.���������Զ�����25 ��������:2021-7-21
	 * 
	 * @param newDef25 java.lang.String
	 */
	public void setDef25(java.lang.String def25) {
		this.def25 = def25;
	}

	/**
	 * ���� def26��Getter����.���������Զ�����26 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef26() {
		return this.def26;
	}

	/**
	 * ����def26��Setter����.���������Զ�����26 ��������:2021-7-21
	 * 
	 * @param newDef26 java.lang.String
	 */
	public void setDef26(java.lang.String def26) {
		this.def26 = def26;
	}

	/**
	 * ���� def27��Getter����.���������Զ�����27 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef27() {
		return this.def27;
	}

	/**
	 * ����def27��Setter����.���������Զ�����27 ��������:2021-7-21
	 * 
	 * @param newDef27 java.lang.String
	 */
	public void setDef27(java.lang.String def27) {
		this.def27 = def27;
	}

	/**
	 * ���� def28��Getter����.���������Զ�����28 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef28() {
		return this.def28;
	}

	/**
	 * ����def28��Setter����.���������Զ�����28 ��������:2021-7-21
	 * 
	 * @param newDef28 java.lang.String
	 */
	public void setDef28(java.lang.String def28) {
		this.def28 = def28;
	}

	/**
	 * ���� def29��Getter����.���������Զ�����29 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef29() {
		return this.def29;
	}

	/**
	 * ����def29��Setter����.���������Զ�����29 ��������:2021-7-21
	 * 
	 * @param newDef29 java.lang.String
	 */
	public void setDef29(java.lang.String def29) {
		this.def29 = def29;
	}

	/**
	 * ���� def30��Getter����.���������Զ�����30 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDef30() {
		return this.def30;
	}

	/**
	 * ����def30��Setter����.���������Զ�����30 ��������:2021-7-21
	 * 
	 * @param newDef30 java.lang.String
	 */
	public void setDef30(java.lang.String def30) {
		this.def30 = def30;
	}

	/**
	 * ���� schoolname��Getter����.��������ѧ������ѧУ���� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSchoolname() {
		return this.schoolname;
	}

	/**
	 * ����schoolname��Setter����.��������ѧ������ѧУ���� ��������:2021-7-21
	 * 
	 * @param newSchoolname java.lang.String
	 */
	public void setSchoolname(java.lang.String schoolname) {
		this.schoolname = schoolname;
	}

	/**
	 * ���� idcard��Getter����.�����������֤�� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getIdcard() {
		return this.idcard;
	}

	/**
	 * ����idcard��Setter����.�����������֤�� ��������:2021-7-21
	 * 
	 * @param newIdcard java.lang.String
	 */
	public void setIdcard(java.lang.String idcard) {
		this.idcard = idcard;
	}

	/**
	 * ���� studenttype��Getter����.��������ѧ������ ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getStudenttype() {
		return this.studenttype;
	}

	/**
	 * ����studenttype��Setter����.��������ѧ������ ��������:2021-7-21
	 * 
	 * @param newStudenttype java.lang.String
	 */
	public void setStudenttype(java.lang.String studenttype) {
		this.studenttype = studenttype;
	}

	/**
	 * ���� email��Getter����.������������ ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 * ����email��Setter����.������������ ��������:2021-7-21
	 * 
	 * @param newEmail java.lang.String
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * ���� isuseing��Getter����.���������Ƿ����� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.lang.UFUFBoolean
	 */
	public UFBoolean getIsuseing() {
		return this.isuseing;
	}

	/**
	 * ����isuseing��Setter����.���������Ƿ����� ��������:2021-7-21
	 * 
	 * @param newIsuseing nc.vo.pub.lang.UFUFBoolean
	 */
	public void setIsuseing(UFBoolean isuseing) {
		this.isuseing = isuseing;
	}

	/**
	 * ���� region��Getter����.������������ ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getRegion() {
		return this.region;
	}

	/**
	 * ����region��Setter����.������������ ��������:2021-7-21
	 * 
	 * @param newRegion java.lang.String
	 */
	public void setRegion(java.lang.String region) {
		this.region = region;
	}

	/**
	 * ���� province��Getter����.��������ʡ�� ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getProvince() {
		return this.province;
	}

	/**
	 * ����province��Setter����.��������ʡ�� ��������:2021-7-21
	 * 
	 * @param newProvince java.lang.String
	 */
	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	/**
	 * ���� inthegrade��Getter����.����������ǰ�����꼶 ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getInthegrade() {
		return this.inthegrade;
	}

	/**
	 * ����inthegrade��Setter����.����������ǰ�����꼶 ��������:2021-7-21
	 * 
	 * @param newInthegrade java.lang.String
	 */
	public void setInthegrade(java.lang.String inthegrade) {
		this.inthegrade = inthegrade;
	}

	/**
	 * ���� cygrade��Getter����.����������Դ�༶ ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCygrade() {
		return this.cygrade;
	}

	/**
	 * ����cygrade��Setter����.����������Դ�༶ ��������:2021-7-21
	 * 
	 * @param newCygrade java.lang.String
	 */
	public void setCygrade(java.lang.String cygrade) {
		this.cygrade = cygrade;
	}

	/**
	 * ���� xzgrade��Getter����.�������������༶ ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getXzgrade() {
		return this.xzgrade;
	}

	/**
	 * ����xzgrade��Setter����.�������������༶ ��������:2021-7-21
	 * 
	 * @param newXzgrade java.lang.String
	 */
	public void setXzgrade(java.lang.String xzgrade) {
		this.xzgrade = xzgrade;
	}

	/**
	 * ���� sname��Getter����.��������ѧ������ ��������:2021-7-21
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSname() {
		return this.sname;
	}

	/**
	 * ����sname��Setter����.��������ѧ������ ��������:2021-7-21
	 * 
	 * @param newSname java.lang.String
	 */
	public void setSname(java.lang.String sname) {
		this.sname = sname;
	}

	/**
	 * ���� sex��Getter����.���������Ա� ��������:2021-7-21
	 * 
	 * @return nc.vo.bd.pub.SexEnum
	 */
	public java.lang.Integer getSex() {
		return this.sex;
	}

	/**
	 * ����sex��Setter����.���������Ա� ��������:2021-7-21
	 * 
	 * @param newSex nc.vo.bd.pub.SexEnum
	 */
	public void setSex(java.lang.Integer sex) {
		this.sex = sex;
	}

	/**
	 * ���� ����ʱ�����Getter����.��������ʱ��� ��������:2021-7-21
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * ��������ʱ�����Setter����.��������ʱ��� ��������:2021-7-21
	 * 
	 * @param newts nc.vo.pub.lang.UFDateTime
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("cy.studentHVO");
	}
}
