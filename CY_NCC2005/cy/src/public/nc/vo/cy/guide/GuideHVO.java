package nc.vo.cy.guide;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2021-8-9
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class GuideHVO extends SuperVO {
	
/**
*����ָ������
*/
public java.lang.String pk_guide;
/**
*����
*/
public java.lang.String pk_group;
/**
*��֯
*/
public java.lang.String pk_org;
/**
*��֯�汾
*/
public java.lang.String pk_org_v;
/**
*��������
*/
public UFDate dbilldate;
/**
*����״̬
*/
public java.lang.Integer billstatus;
/**
*���ݺ�
*/
public java.lang.String bill_no;
/**
*ҵ������
*/
public java.lang.String pk_busitype;
/**
*��������
*/
public java.lang.String pk_billtypeid;
/**
*�������ͱ���
*/
public java.lang.String billtype;
/**
*�������ͱ���
*/
public java.lang.String transtypecode;
/**
*��������
*/
public java.lang.String pk_transtype;
/**
*�Ƶ���
*/
public java.lang.String billmaker;
/**
*�Ƶ�ʱ��
*/
public UFDate maketime;
/**
*������
*/
public java.lang.String creator;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*�޸���
*/
public java.lang.String modifier;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*������
*/
public java.lang.String approver;
/**
*����״̬
*/
public java.lang.Integer approvestatus;
/**
*��������
*/
public java.lang.String approvenote;
/**
*����ʱ��
*/
public UFDateTime approvedate;
/**
*��ע
*/
public java.lang.String vnote;
/**
*��Դ��������
*/
public java.lang.String srcbilltype;
/**
*��Դ����id
*/
public java.lang.String srcbillid;
/**
*��Դ���ݺ�
*/
public java.lang.String srccode;
/**
*��Դ��������id
*/
public java.lang.String srcid;
/**
*��Դ�����ӱ�id
*/
public java.lang.String srcbid;
/**
*��Դ�����к�
*/
public java.lang.String srcrowno;
/**
*��Դ��������
*/
public java.lang.String srctrantype;
/**
*Դͷ��������
*/
public java.lang.String vfirsttype;
/**
*Դͷ���ݺ�
*/
public java.lang.String vfirstcode;
/**
*Դͷ��������id
*/
public java.lang.String vfirstid;
/**
*Դͷ�����ӱ�id
*/
public java.lang.String vfirstbid;
/**
*Դͷ�����к�
*/
public java.lang.String vfirstrowno;
/**
*Դͷ��������
*/
public java.lang.String vfirsttrantype;
/**
*�̲��Ƿ�����
*/
public UFBoolean isapply;
/**
*��Ⱥ�Ƿ���
*/
public UFBoolean isbuild;
/**
*�����ѧ�ƻ��Ƿ��ϴ�
*/
public UFBoolean isuploadall;
/**
*��ѧ�ڽ�ѧ�ƻ��Ƿ��ϴ�
*/
public UFBoolean isuploadnow;
/**
*ѧ������Ӣ��ɼ��Ƿ�ά��
*/
public UFBoolean ismaintain;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_guide��Getter����.������������ָ������
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getPk_guide() {
return this.pk_guide;
} 

/**
* ����pk_guide��Setter����.������������ָ������
* ��������:2021-8-9
* @param newPk_guide java.lang.String
*/
public void setPk_guide ( java.lang.String pk_guide) {
this.pk_guide=pk_guide;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2021-8-9
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2021-8-9
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2021-8-9
* @return nc.vo.org.OrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2021-8-9
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯�汾
*  ��������:2021-8-9
* @return nc.vo.vorg.OrgVersionVO
*/
public java.lang.String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯�汾
* ��������:2021-8-9
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( java.lang.String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� dbilldate��Getter����.����������������
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.����������������
* ��������:2021-8-9
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* ���� billstatus��Getter����.������������״̬
*  ��������:2021-8-9
* @return nc.vo.pub.pf.BillStatusEnum
*/
public java.lang.Integer getBillstatus() {
return this.billstatus;
} 

/**
* ����billstatus��Setter����.������������״̬
* ��������:2021-8-9
* @param newBillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setBillstatus ( java.lang.Integer billstatus) {
this.billstatus=billstatus;
} 
 
/**
* ���� bill_no��Getter����.�����������ݺ�
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getBill_no() {
return this.bill_no;
} 

/**
* ����bill_no��Setter����.�����������ݺ�
* ��������:2021-8-9
* @param newBill_no java.lang.String
*/
public void setBill_no ( java.lang.String bill_no) {
this.bill_no=bill_no;
} 
 
/**
* ���� pk_busitype��Getter����.��������ҵ������
*  ��������:2021-8-9
* @return nc.vo.pf.pub.BusitypeVO
*/
public java.lang.String getPk_busitype() {
return this.pk_busitype;
} 

/**
* ����pk_busitype��Setter����.��������ҵ������
* ��������:2021-8-9
* @param newPk_busitype nc.vo.pf.pub.BusitypeVO
*/
public void setPk_busitype ( java.lang.String pk_busitype) {
this.pk_busitype=pk_busitype;
} 
 
/**
* ���� pk_billtypeid��Getter����.����������������
*  ��������:2021-8-9
* @return nc.vo.pub.billtype.BilltypeVO
*/
public java.lang.String getPk_billtypeid() {
return this.pk_billtypeid;
} 

/**
* ����pk_billtypeid��Setter����.����������������
* ��������:2021-8-9
* @param newPk_billtypeid nc.vo.pub.billtype.BilltypeVO
*/
public void setPk_billtypeid ( java.lang.String pk_billtypeid) {
this.pk_billtypeid=pk_billtypeid;
} 
 
/**
* ���� billtype��Getter����.���������������ͱ���
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getBilltype() {
return this.billtype;
} 

/**
* ����billtype��Setter����.���������������ͱ���
* ��������:2021-8-9
* @param newBilltype java.lang.String
*/
public void setBilltype ( java.lang.String billtype) {
this.billtype=billtype;
} 
 
/**
* ���� transtypecode��Getter����.���������������ͱ���
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getTranstypecode() {
return this.transtypecode;
} 

/**
* ����transtypecode��Setter����.���������������ͱ���
* ��������:2021-8-9
* @param newTranstypecode java.lang.String
*/
public void setTranstypecode ( java.lang.String transtypecode) {
this.transtypecode=transtypecode;
} 
 
/**
* ���� pk_transtype��Getter����.����������������
*  ��������:2021-8-9
* @return nc.vo.pub.billtype.BilltypeVO
*/
public java.lang.String getPk_transtype() {
return this.pk_transtype;
} 

/**
* ����pk_transtype��Setter����.����������������
* ��������:2021-8-9
* @param newPk_transtype nc.vo.pub.billtype.BilltypeVO
*/
public void setPk_transtype ( java.lang.String pk_transtype) {
this.pk_transtype=pk_transtype;
} 
 
/**
* ���� billmaker��Getter����.���������Ƶ���
*  ��������:2021-8-9
* @return nc.vo.sm.UserVO
*/
public java.lang.String getBillmaker() {
return this.billmaker;
} 

/**
* ����billmaker��Setter����.���������Ƶ���
* ��������:2021-8-9
* @param newBillmaker nc.vo.sm.UserVO
*/
public void setBillmaker ( java.lang.String billmaker) {
this.billmaker=billmaker;
} 
 
/**
* ���� maketime��Getter����.���������Ƶ�ʱ��
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getMaketime() {
return this.maketime;
} 

/**
* ����maketime��Setter����.���������Ƶ�ʱ��
* ��������:2021-8-9
* @param newMaketime nc.vo.pub.lang.UFDate
*/
public void setMaketime ( UFDate maketime) {
this.maketime=maketime;
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2021-8-9
* @return nc.vo.sm.UserVO
*/
public java.lang.String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.��������������
* ��������:2021-8-9
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( java.lang.String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2021-8-9
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2021-8-9
* @return nc.vo.sm.UserVO
*/
public java.lang.String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2021-8-9
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( java.lang.String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.���������޸�ʱ��
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.���������޸�ʱ��
* ��������:2021-8-9
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� approver��Getter����.��������������
*  ��������:2021-8-9
* @return nc.vo.sm.UserVO
*/
public java.lang.String getApprover() {
return this.approver;
} 

/**
* ����approver��Setter����.��������������
* ��������:2021-8-9
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( java.lang.String approver) {
this.approver=approver;
} 
 
/**
* ���� approvestatus��Getter����.������������״̬
*  ��������:2021-8-9
* @return nc.vo.pub.pf.BillStatusEnum
*/
public java.lang.Integer getApprovestatus() {
return this.approvestatus;
} 

/**
* ����approvestatus��Setter����.������������״̬
* ��������:2021-8-9
* @param newApprovestatus nc.vo.pub.pf.BillStatusEnum
*/
public void setApprovestatus ( java.lang.Integer approvestatus) {
this.approvestatus=approvestatus;
} 
 
/**
* ���� approvenote��Getter����.����������������
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getApprovenote() {
return this.approvenote;
} 

/**
* ����approvenote��Setter����.����������������
* ��������:2021-8-9
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( java.lang.String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* ���� approvedate��Getter����.������������ʱ��
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getApprovedate() {
return this.approvedate;
} 

/**
* ����approvedate��Setter����.������������ʱ��
* ��������:2021-8-9
* @param newApprovedate nc.vo.pub.lang.UFDateTime
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
} 
 
/**
* ���� vnote��Getter����.����������ע
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getVnote() {
return this.vnote;
} 

/**
* ����vnote��Setter����.����������ע
* ��������:2021-8-9
* @param newVnote java.lang.String
*/
public void setVnote ( java.lang.String vnote) {
this.vnote=vnote;
} 
 
/**
* ���� srcbilltype��Getter����.����������Դ��������
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getSrcbilltype() {
return this.srcbilltype;
} 

/**
* ����srcbilltype��Setter����.����������Դ��������
* ��������:2021-8-9
* @param newSrcbilltype java.lang.String
*/
public void setSrcbilltype ( java.lang.String srcbilltype) {
this.srcbilltype=srcbilltype;
} 
 
/**
* ���� srcbillid��Getter����.����������Դ����id
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getSrcbillid() {
return this.srcbillid;
} 

/**
* ����srcbillid��Setter����.����������Դ����id
* ��������:2021-8-9
* @param newSrcbillid java.lang.String
*/
public void setSrcbillid ( java.lang.String srcbillid) {
this.srcbillid=srcbillid;
} 
 
/**
* ���� srccode��Getter����.����������Դ���ݺ�
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getSrccode() {
return this.srccode;
} 

/**
* ����srccode��Setter����.����������Դ���ݺ�
* ��������:2021-8-9
* @param newSrccode java.lang.String
*/
public void setSrccode ( java.lang.String srccode) {
this.srccode=srccode;
} 
 
/**
* ���� srcid��Getter����.����������Դ��������id
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getSrcid() {
return this.srcid;
} 

/**
* ����srcid��Setter����.����������Դ��������id
* ��������:2021-8-9
* @param newSrcid java.lang.String
*/
public void setSrcid ( java.lang.String srcid) {
this.srcid=srcid;
} 
 
/**
* ���� srcbid��Getter����.����������Դ�����ӱ�id
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getSrcbid() {
return this.srcbid;
} 

/**
* ����srcbid��Setter����.����������Դ�����ӱ�id
* ��������:2021-8-9
* @param newSrcbid java.lang.String
*/
public void setSrcbid ( java.lang.String srcbid) {
this.srcbid=srcbid;
} 
 
/**
* ���� srcrowno��Getter����.����������Դ�����к�
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getSrcrowno() {
return this.srcrowno;
} 

/**
* ����srcrowno��Setter����.����������Դ�����к�
* ��������:2021-8-9
* @param newSrcrowno java.lang.String
*/
public void setSrcrowno ( java.lang.String srcrowno) {
this.srcrowno=srcrowno;
} 
 
/**
* ���� srctrantype��Getter����.����������Դ��������
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getSrctrantype() {
return this.srctrantype;
} 

/**
* ����srctrantype��Setter����.����������Դ��������
* ��������:2021-8-9
* @param newSrctrantype java.lang.String
*/
public void setSrctrantype ( java.lang.String srctrantype) {
this.srctrantype=srctrantype;
} 
 
/**
* ���� vfirsttype��Getter����.��������Դͷ��������
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getVfirsttype() {
return this.vfirsttype;
} 

/**
* ����vfirsttype��Setter����.��������Դͷ��������
* ��������:2021-8-9
* @param newVfirsttype java.lang.String
*/
public void setVfirsttype ( java.lang.String vfirsttype) {
this.vfirsttype=vfirsttype;
} 
 
/**
* ���� vfirstcode��Getter����.��������Դͷ���ݺ�
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getVfirstcode() {
return this.vfirstcode;
} 

/**
* ����vfirstcode��Setter����.��������Դͷ���ݺ�
* ��������:2021-8-9
* @param newVfirstcode java.lang.String
*/
public void setVfirstcode ( java.lang.String vfirstcode) {
this.vfirstcode=vfirstcode;
} 
 
/**
* ���� vfirstid��Getter����.��������Դͷ��������id
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getVfirstid() {
return this.vfirstid;
} 

/**
* ����vfirstid��Setter����.��������Դͷ��������id
* ��������:2021-8-9
* @param newVfirstid java.lang.String
*/
public void setVfirstid ( java.lang.String vfirstid) {
this.vfirstid=vfirstid;
} 
 
/**
* ���� vfirstbid��Getter����.��������Դͷ�����ӱ�id
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getVfirstbid() {
return this.vfirstbid;
} 

/**
* ����vfirstbid��Setter����.��������Դͷ�����ӱ�id
* ��������:2021-8-9
* @param newVfirstbid java.lang.String
*/
public void setVfirstbid ( java.lang.String vfirstbid) {
this.vfirstbid=vfirstbid;
} 
 
/**
* ���� vfirstrowno��Getter����.��������Դͷ�����к�
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getVfirstrowno() {
return this.vfirstrowno;
} 

/**
* ����vfirstrowno��Setter����.��������Դͷ�����к�
* ��������:2021-8-9
* @param newVfirstrowno java.lang.String
*/
public void setVfirstrowno ( java.lang.String vfirstrowno) {
this.vfirstrowno=vfirstrowno;
} 
 
/**
* ���� vfirsttrantype��Getter����.��������Դͷ��������
*  ��������:2021-8-9
* @return java.lang.String
*/
public java.lang.String getVfirsttrantype() {
return this.vfirsttrantype;
} 

/**
* ����vfirsttrantype��Setter����.��������Դͷ��������
* ��������:2021-8-9
* @param newVfirsttrantype java.lang.String
*/
public void setVfirsttrantype ( java.lang.String vfirsttrantype) {
this.vfirsttrantype=vfirsttrantype;
} 
 
/**
* ���� isapply��Getter����.���������̲��Ƿ�����
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getIsapply() {
return this.isapply;
} 

/**
* ����isapply��Setter����.���������̲��Ƿ�����
* ��������:2021-8-9
* @param newIsapply nc.vo.pub.lang.UFUFBoolean
*/
public void setIsapply ( UFBoolean isapply) {
this.isapply=isapply;
} 
 
/**
* ���� isbuild��Getter����.����������Ⱥ�Ƿ���
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getIsbuild() {
return this.isbuild;
} 

/**
* ����isbuild��Setter����.����������Ⱥ�Ƿ���
* ��������:2021-8-9
* @param newIsbuild nc.vo.pub.lang.UFUFBoolean
*/
public void setIsbuild ( UFBoolean isbuild) {
this.isbuild=isbuild;
} 
 
/**
* ���� isuploadall��Getter����.�������������ѧ�ƻ��Ƿ��ϴ�
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getIsuploadall() {
return this.isuploadall;
} 

/**
* ����isuploadall��Setter����.�������������ѧ�ƻ��Ƿ��ϴ�
* ��������:2021-8-9
* @param newIsuploadall nc.vo.pub.lang.UFUFBoolean
*/
public void setIsuploadall ( UFBoolean isuploadall) {
this.isuploadall=isuploadall;
} 
 
/**
* ���� isuploadnow��Getter����.����������ѧ�ڽ�ѧ�ƻ��Ƿ��ϴ�
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getIsuploadnow() {
return this.isuploadnow;
} 

/**
* ����isuploadnow��Setter����.����������ѧ�ڽ�ѧ�ƻ��Ƿ��ϴ�
* ��������:2021-8-9
* @param newIsuploadnow nc.vo.pub.lang.UFUFBoolean
*/
public void setIsuploadnow ( UFBoolean isuploadnow) {
this.isuploadnow=isuploadnow;
} 
 
/**
* ���� ismaintain��Getter����.��������ѧ������Ӣ��ɼ��Ƿ�ά��
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getIsmaintain() {
return this.ismaintain;
} 

/**
* ����ismaintain��Setter����.��������ѧ������Ӣ��ɼ��Ƿ�ά��
* ��������:2021-8-9
* @param newIsmaintain nc.vo.pub.lang.UFUFBoolean
*/
public void setIsmaintain ( UFBoolean ismaintain) {
this.ismaintain=ismaintain;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2021-8-9
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2021-8-9
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("cy.guideHVO");
    }
   }
    