package nc.vo.cy.taskallocation;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2021-9-2
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class TaskallocationHVO extends SuperVO {
	
/**
*任务分配主键
*/
public java.lang.String pk_taskallocation;
/**
*集团
*/
public java.lang.String pk_group;
/**
*组织
*/
public java.lang.String pk_org;
/**
*组织版本
*/
public java.lang.String pk_org_v;
/**
*单据日期
*/
public UFDate dbilldate;
/**
*单据状态
*/
public java.lang.Integer billstatus;
/**
*单据号
*/
public java.lang.String bill_no;
/**
*业务类型
*/
public java.lang.String pk_busitype;
/**
*单据类型
*/
public java.lang.String pk_billtypeid;
/**
*单据类型编码
*/
public java.lang.String billtype;
/**
*交易类型编码
*/
public java.lang.String transtypecode;
/**
*交易类型
*/
public java.lang.String pk_transtype;
/**
*制单人
*/
public java.lang.String billmaker;
/**
*制单时间
*/
public UFDate maketime;
/**
*创建人
*/
public java.lang.String creator;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*修改人
*/
public java.lang.String modifier;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*审批人
*/
public java.lang.String approver;
/**
*审批状态
*/
public java.lang.Integer approvestatus;
/**
*审批批语
*/
public java.lang.String approvenote;
/**
*审批时间
*/
public UFDateTime approvedate;
/**
*备注
*/
public java.lang.String vnote;
/**
*来源单据类型
*/
public java.lang.String srcbilltype;
/**
*来源单据id
*/
public java.lang.String srcbillid;
/**
*来源单据号
*/
public java.lang.String srccode;
/**
*来源单据主表id
*/
public java.lang.String srcid;
/**
*来源单据子表id
*/
public java.lang.String srcbid;
/**
*来源单据行号
*/
public java.lang.String srcrowno;
/**
*来源交易类型
*/
public java.lang.String srctrantype;
/**
*源头单据类型
*/
public java.lang.String vfirsttype;
/**
*源头单据号
*/
public java.lang.String vfirstcode;
/**
*源头单据主表id
*/
public java.lang.String vfirstid;
/**
*源头单据子表id
*/
public java.lang.String vfirstbid;
/**
*源头单据行号
*/
public java.lang.String vfirstrowno;
/**
*源头交易类型
*/
public java.lang.String vfirsttrantype;
/**
*自定义项1
*/
public java.lang.String def1;
/**
*自定义项2
*/
public java.lang.String def2;
/**
*自定义项3
*/
public java.lang.String def3;
/**
*自定义项4
*/
public java.lang.String def4;
/**
*自定义项5
*/
public java.lang.String def5;
/**
*自定义项6
*/
public java.lang.String def6;
/**
*自定义项7
*/
public java.lang.String def7;
/**
*自定义项8
*/
public java.lang.String def8;
/**
*自定义项9
*/
public java.lang.String def9;
/**
*自定义项10
*/
public java.lang.String def10;
/**
*自定义项11
*/
public java.lang.String def11;
/**
*自定义项12
*/
public java.lang.String def12;
/**
*自定义项13
*/
public java.lang.String def13;
/**
*自定义项14
*/
public java.lang.String def14;
/**
*自定义项15
*/
public java.lang.String def15;
/**
*自定义项16
*/
public java.lang.String def16;
/**
*自定义项17
*/
public java.lang.String def17;
/**
*自定义项18
*/
public java.lang.String def18;
/**
*自定义项19
*/
public java.lang.String def19;
/**
*自定义项20
*/
public java.lang.String def20;
/**
*自定义项21
*/
public java.lang.String def21;
/**
*自定义项22
*/
public java.lang.String def22;
/**
*自定义项23
*/
public java.lang.String def23;
/**
*自定义项24
*/
public java.lang.String def24;
/**
*自定义项25
*/
public java.lang.String def25;
/**
*自定义项26
*/
public java.lang.String def26;
/**
*自定义项27
*/
public java.lang.String def27;
/**
*自定义项28
*/
public java.lang.String def28;
/**
*自定义项29
*/
public java.lang.String def29;
/**
*自定义项30
*/
public java.lang.String def30;
/**
*业务员
*/
public java.lang.String salesman;
/**
*开始时间
*/
public UFDate assgindate;
/**
*结束时间
*/
public UFDate enddate;
/**
*指派任务内容
*/
public java.lang.String task;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_taskallocation的Getter方法.属性名：任务分配主键
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getPk_taskallocation() {
return this.pk_taskallocation;
} 

/**
* 属性pk_taskallocation的Setter方法.属性名：任务分配主键
* 创建日期:2021-9-2
* @param newPk_taskallocation java.lang.String
*/
public void setPk_taskallocation ( java.lang.String pk_taskallocation) {
this.pk_taskallocation=pk_taskallocation;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2021-9-2
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2021-9-2
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2021-9-2
* @return nc.vo.org.OrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2021-9-2
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：组织版本
*  创建日期:2021-9-2
* @return nc.vo.vorg.OrgVersionVO
*/
public java.lang.String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：组织版本
* 创建日期:2021-9-2
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( java.lang.String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 dbilldate的Getter方法.属性名：单据日期
*  创建日期:2021-9-2
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* 属性dbilldate的Setter方法.属性名：单据日期
* 创建日期:2021-9-2
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* 属性 billstatus的Getter方法.属性名：单据状态
*  创建日期:2021-9-2
* @return nc.vo.pub.pf.BillStatusEnum
*/
public java.lang.Integer getBillstatus() {
return this.billstatus;
} 

/**
* 属性billstatus的Setter方法.属性名：单据状态
* 创建日期:2021-9-2
* @param newBillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setBillstatus ( java.lang.Integer billstatus) {
this.billstatus=billstatus;
} 
 
/**
* 属性 bill_no的Getter方法.属性名：单据号
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getBill_no() {
return this.bill_no;
} 

/**
* 属性bill_no的Setter方法.属性名：单据号
* 创建日期:2021-9-2
* @param newBill_no java.lang.String
*/
public void setBill_no ( java.lang.String bill_no) {
this.bill_no=bill_no;
} 
 
/**
* 属性 pk_busitype的Getter方法.属性名：业务类型
*  创建日期:2021-9-2
* @return nc.vo.pf.pub.BusitypeVO
*/
public java.lang.String getPk_busitype() {
return this.pk_busitype;
} 

/**
* 属性pk_busitype的Setter方法.属性名：业务类型
* 创建日期:2021-9-2
* @param newPk_busitype nc.vo.pf.pub.BusitypeVO
*/
public void setPk_busitype ( java.lang.String pk_busitype) {
this.pk_busitype=pk_busitype;
} 
 
/**
* 属性 pk_billtypeid的Getter方法.属性名：单据类型
*  创建日期:2021-9-2
* @return nc.vo.pub.billtype.BilltypeVO
*/
public java.lang.String getPk_billtypeid() {
return this.pk_billtypeid;
} 

/**
* 属性pk_billtypeid的Setter方法.属性名：单据类型
* 创建日期:2021-9-2
* @param newPk_billtypeid nc.vo.pub.billtype.BilltypeVO
*/
public void setPk_billtypeid ( java.lang.String pk_billtypeid) {
this.pk_billtypeid=pk_billtypeid;
} 
 
/**
* 属性 billtype的Getter方法.属性名：单据类型编码
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getBilltype() {
return this.billtype;
} 

/**
* 属性billtype的Setter方法.属性名：单据类型编码
* 创建日期:2021-9-2
* @param newBilltype java.lang.String
*/
public void setBilltype ( java.lang.String billtype) {
this.billtype=billtype;
} 
 
/**
* 属性 transtypecode的Getter方法.属性名：交易类型编码
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getTranstypecode() {
return this.transtypecode;
} 

/**
* 属性transtypecode的Setter方法.属性名：交易类型编码
* 创建日期:2021-9-2
* @param newTranstypecode java.lang.String
*/
public void setTranstypecode ( java.lang.String transtypecode) {
this.transtypecode=transtypecode;
} 
 
/**
* 属性 pk_transtype的Getter方法.属性名：交易类型
*  创建日期:2021-9-2
* @return nc.vo.pub.billtype.BilltypeVO
*/
public java.lang.String getPk_transtype() {
return this.pk_transtype;
} 

/**
* 属性pk_transtype的Setter方法.属性名：交易类型
* 创建日期:2021-9-2
* @param newPk_transtype nc.vo.pub.billtype.BilltypeVO
*/
public void setPk_transtype ( java.lang.String pk_transtype) {
this.pk_transtype=pk_transtype;
} 
 
/**
* 属性 billmaker的Getter方法.属性名：制单人
*  创建日期:2021-9-2
* @return nc.vo.sm.UserVO
*/
public java.lang.String getBillmaker() {
return this.billmaker;
} 

/**
* 属性billmaker的Setter方法.属性名：制单人
* 创建日期:2021-9-2
* @param newBillmaker nc.vo.sm.UserVO
*/
public void setBillmaker ( java.lang.String billmaker) {
this.billmaker=billmaker;
} 
 
/**
* 属性 maketime的Getter方法.属性名：制单时间
*  创建日期:2021-9-2
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getMaketime() {
return this.maketime;
} 

/**
* 属性maketime的Setter方法.属性名：制单时间
* 创建日期:2021-9-2
* @param newMaketime nc.vo.pub.lang.UFDate
*/
public void setMaketime ( UFDate maketime) {
this.maketime=maketime;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2021-9-2
* @return nc.vo.sm.UserVO
*/
public java.lang.String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2021-9-2
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( java.lang.String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2021-9-2
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2021-9-2
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：修改人
*  创建日期:2021-9-2
* @return nc.vo.sm.UserVO
*/
public java.lang.String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：修改人
* 创建日期:2021-9-2
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( java.lang.String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：修改时间
*  创建日期:2021-9-2
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：修改时间
* 创建日期:2021-9-2
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 approver的Getter方法.属性名：审批人
*  创建日期:2021-9-2
* @return nc.vo.sm.UserVO
*/
public java.lang.String getApprover() {
return this.approver;
} 

/**
* 属性approver的Setter方法.属性名：审批人
* 创建日期:2021-9-2
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( java.lang.String approver) {
this.approver=approver;
} 
 
/**
* 属性 approvestatus的Getter方法.属性名：审批状态
*  创建日期:2021-9-2
* @return nc.vo.pub.pf.BillStatusEnum
*/
public java.lang.Integer getApprovestatus() {
return this.approvestatus;
} 

/**
* 属性approvestatus的Setter方法.属性名：审批状态
* 创建日期:2021-9-2
* @param newApprovestatus nc.vo.pub.pf.BillStatusEnum
*/
public void setApprovestatus ( java.lang.Integer approvestatus) {
this.approvestatus=approvestatus;
} 
 
/**
* 属性 approvenote的Getter方法.属性名：审批批语
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getApprovenote() {
return this.approvenote;
} 

/**
* 属性approvenote的Setter方法.属性名：审批批语
* 创建日期:2021-9-2
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( java.lang.String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* 属性 approvedate的Getter方法.属性名：审批时间
*  创建日期:2021-9-2
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getApprovedate() {
return this.approvedate;
} 

/**
* 属性approvedate的Setter方法.属性名：审批时间
* 创建日期:2021-9-2
* @param newApprovedate nc.vo.pub.lang.UFDateTime
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
} 
 
/**
* 属性 vnote的Getter方法.属性名：备注
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getVnote() {
return this.vnote;
} 

/**
* 属性vnote的Setter方法.属性名：备注
* 创建日期:2021-9-2
* @param newVnote java.lang.String
*/
public void setVnote ( java.lang.String vnote) {
this.vnote=vnote;
} 
 
/**
* 属性 srcbilltype的Getter方法.属性名：来源单据类型
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getSrcbilltype() {
return this.srcbilltype;
} 

/**
* 属性srcbilltype的Setter方法.属性名：来源单据类型
* 创建日期:2021-9-2
* @param newSrcbilltype java.lang.String
*/
public void setSrcbilltype ( java.lang.String srcbilltype) {
this.srcbilltype=srcbilltype;
} 
 
/**
* 属性 srcbillid的Getter方法.属性名：来源单据id
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getSrcbillid() {
return this.srcbillid;
} 

/**
* 属性srcbillid的Setter方法.属性名：来源单据id
* 创建日期:2021-9-2
* @param newSrcbillid java.lang.String
*/
public void setSrcbillid ( java.lang.String srcbillid) {
this.srcbillid=srcbillid;
} 
 
/**
* 属性 srccode的Getter方法.属性名：来源单据号
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getSrccode() {
return this.srccode;
} 

/**
* 属性srccode的Setter方法.属性名：来源单据号
* 创建日期:2021-9-2
* @param newSrccode java.lang.String
*/
public void setSrccode ( java.lang.String srccode) {
this.srccode=srccode;
} 
 
/**
* 属性 srcid的Getter方法.属性名：来源单据主表id
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getSrcid() {
return this.srcid;
} 

/**
* 属性srcid的Setter方法.属性名：来源单据主表id
* 创建日期:2021-9-2
* @param newSrcid java.lang.String
*/
public void setSrcid ( java.lang.String srcid) {
this.srcid=srcid;
} 
 
/**
* 属性 srcbid的Getter方法.属性名：来源单据子表id
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getSrcbid() {
return this.srcbid;
} 

/**
* 属性srcbid的Setter方法.属性名：来源单据子表id
* 创建日期:2021-9-2
* @param newSrcbid java.lang.String
*/
public void setSrcbid ( java.lang.String srcbid) {
this.srcbid=srcbid;
} 
 
/**
* 属性 srcrowno的Getter方法.属性名：来源单据行号
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getSrcrowno() {
return this.srcrowno;
} 

/**
* 属性srcrowno的Setter方法.属性名：来源单据行号
* 创建日期:2021-9-2
* @param newSrcrowno java.lang.String
*/
public void setSrcrowno ( java.lang.String srcrowno) {
this.srcrowno=srcrowno;
} 
 
/**
* 属性 srctrantype的Getter方法.属性名：来源交易类型
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getSrctrantype() {
return this.srctrantype;
} 

/**
* 属性srctrantype的Setter方法.属性名：来源交易类型
* 创建日期:2021-9-2
* @param newSrctrantype java.lang.String
*/
public void setSrctrantype ( java.lang.String srctrantype) {
this.srctrantype=srctrantype;
} 
 
/**
* 属性 vfirsttype的Getter方法.属性名：源头单据类型
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getVfirsttype() {
return this.vfirsttype;
} 

/**
* 属性vfirsttype的Setter方法.属性名：源头单据类型
* 创建日期:2021-9-2
* @param newVfirsttype java.lang.String
*/
public void setVfirsttype ( java.lang.String vfirsttype) {
this.vfirsttype=vfirsttype;
} 
 
/**
* 属性 vfirstcode的Getter方法.属性名：源头单据号
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getVfirstcode() {
return this.vfirstcode;
} 

/**
* 属性vfirstcode的Setter方法.属性名：源头单据号
* 创建日期:2021-9-2
* @param newVfirstcode java.lang.String
*/
public void setVfirstcode ( java.lang.String vfirstcode) {
this.vfirstcode=vfirstcode;
} 
 
/**
* 属性 vfirstid的Getter方法.属性名：源头单据主表id
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getVfirstid() {
return this.vfirstid;
} 

/**
* 属性vfirstid的Setter方法.属性名：源头单据主表id
* 创建日期:2021-9-2
* @param newVfirstid java.lang.String
*/
public void setVfirstid ( java.lang.String vfirstid) {
this.vfirstid=vfirstid;
} 
 
/**
* 属性 vfirstbid的Getter方法.属性名：源头单据子表id
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getVfirstbid() {
return this.vfirstbid;
} 

/**
* 属性vfirstbid的Setter方法.属性名：源头单据子表id
* 创建日期:2021-9-2
* @param newVfirstbid java.lang.String
*/
public void setVfirstbid ( java.lang.String vfirstbid) {
this.vfirstbid=vfirstbid;
} 
 
/**
* 属性 vfirstrowno的Getter方法.属性名：源头单据行号
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getVfirstrowno() {
return this.vfirstrowno;
} 

/**
* 属性vfirstrowno的Setter方法.属性名：源头单据行号
* 创建日期:2021-9-2
* @param newVfirstrowno java.lang.String
*/
public void setVfirstrowno ( java.lang.String vfirstrowno) {
this.vfirstrowno=vfirstrowno;
} 
 
/**
* 属性 vfirsttrantype的Getter方法.属性名：源头交易类型
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getVfirsttrantype() {
return this.vfirsttrantype;
} 

/**
* 属性vfirsttrantype的Setter方法.属性名：源头交易类型
* 创建日期:2021-9-2
* @param newVfirsttrantype java.lang.String
*/
public void setVfirsttrantype ( java.lang.String vfirsttrantype) {
this.vfirsttrantype=vfirsttrantype;
} 
 
/**
* 属性 def1的Getter方法.属性名：自定义项1
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef1() {
return this.def1;
} 

/**
* 属性def1的Setter方法.属性名：自定义项1
* 创建日期:2021-9-2
* @param newDef1 java.lang.String
*/
public void setDef1 ( java.lang.String def1) {
this.def1=def1;
} 
 
/**
* 属性 def2的Getter方法.属性名：自定义项2
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef2() {
return this.def2;
} 

/**
* 属性def2的Setter方法.属性名：自定义项2
* 创建日期:2021-9-2
* @param newDef2 java.lang.String
*/
public void setDef2 ( java.lang.String def2) {
this.def2=def2;
} 
 
/**
* 属性 def3的Getter方法.属性名：自定义项3
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef3() {
return this.def3;
} 

/**
* 属性def3的Setter方法.属性名：自定义项3
* 创建日期:2021-9-2
* @param newDef3 java.lang.String
*/
public void setDef3 ( java.lang.String def3) {
this.def3=def3;
} 
 
/**
* 属性 def4的Getter方法.属性名：自定义项4
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef4() {
return this.def4;
} 

/**
* 属性def4的Setter方法.属性名：自定义项4
* 创建日期:2021-9-2
* @param newDef4 java.lang.String
*/
public void setDef4 ( java.lang.String def4) {
this.def4=def4;
} 
 
/**
* 属性 def5的Getter方法.属性名：自定义项5
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef5() {
return this.def5;
} 

/**
* 属性def5的Setter方法.属性名：自定义项5
* 创建日期:2021-9-2
* @param newDef5 java.lang.String
*/
public void setDef5 ( java.lang.String def5) {
this.def5=def5;
} 
 
/**
* 属性 def6的Getter方法.属性名：自定义项6
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef6() {
return this.def6;
} 

/**
* 属性def6的Setter方法.属性名：自定义项6
* 创建日期:2021-9-2
* @param newDef6 java.lang.String
*/
public void setDef6 ( java.lang.String def6) {
this.def6=def6;
} 
 
/**
* 属性 def7的Getter方法.属性名：自定义项7
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef7() {
return this.def7;
} 

/**
* 属性def7的Setter方法.属性名：自定义项7
* 创建日期:2021-9-2
* @param newDef7 java.lang.String
*/
public void setDef7 ( java.lang.String def7) {
this.def7=def7;
} 
 
/**
* 属性 def8的Getter方法.属性名：自定义项8
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef8() {
return this.def8;
} 

/**
* 属性def8的Setter方法.属性名：自定义项8
* 创建日期:2021-9-2
* @param newDef8 java.lang.String
*/
public void setDef8 ( java.lang.String def8) {
this.def8=def8;
} 
 
/**
* 属性 def9的Getter方法.属性名：自定义项9
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef9() {
return this.def9;
} 

/**
* 属性def9的Setter方法.属性名：自定义项9
* 创建日期:2021-9-2
* @param newDef9 java.lang.String
*/
public void setDef9 ( java.lang.String def9) {
this.def9=def9;
} 
 
/**
* 属性 def10的Getter方法.属性名：自定义项10
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef10() {
return this.def10;
} 

/**
* 属性def10的Setter方法.属性名：自定义项10
* 创建日期:2021-9-2
* @param newDef10 java.lang.String
*/
public void setDef10 ( java.lang.String def10) {
this.def10=def10;
} 
 
/**
* 属性 def11的Getter方法.属性名：自定义项11
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef11() {
return this.def11;
} 

/**
* 属性def11的Setter方法.属性名：自定义项11
* 创建日期:2021-9-2
* @param newDef11 java.lang.String
*/
public void setDef11 ( java.lang.String def11) {
this.def11=def11;
} 
 
/**
* 属性 def12的Getter方法.属性名：自定义项12
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef12() {
return this.def12;
} 

/**
* 属性def12的Setter方法.属性名：自定义项12
* 创建日期:2021-9-2
* @param newDef12 java.lang.String
*/
public void setDef12 ( java.lang.String def12) {
this.def12=def12;
} 
 
/**
* 属性 def13的Getter方法.属性名：自定义项13
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef13() {
return this.def13;
} 

/**
* 属性def13的Setter方法.属性名：自定义项13
* 创建日期:2021-9-2
* @param newDef13 java.lang.String
*/
public void setDef13 ( java.lang.String def13) {
this.def13=def13;
} 
 
/**
* 属性 def14的Getter方法.属性名：自定义项14
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef14() {
return this.def14;
} 

/**
* 属性def14的Setter方法.属性名：自定义项14
* 创建日期:2021-9-2
* @param newDef14 java.lang.String
*/
public void setDef14 ( java.lang.String def14) {
this.def14=def14;
} 
 
/**
* 属性 def15的Getter方法.属性名：自定义项15
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef15() {
return this.def15;
} 

/**
* 属性def15的Setter方法.属性名：自定义项15
* 创建日期:2021-9-2
* @param newDef15 java.lang.String
*/
public void setDef15 ( java.lang.String def15) {
this.def15=def15;
} 
 
/**
* 属性 def16的Getter方法.属性名：自定义项16
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef16() {
return this.def16;
} 

/**
* 属性def16的Setter方法.属性名：自定义项16
* 创建日期:2021-9-2
* @param newDef16 java.lang.String
*/
public void setDef16 ( java.lang.String def16) {
this.def16=def16;
} 
 
/**
* 属性 def17的Getter方法.属性名：自定义项17
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef17() {
return this.def17;
} 

/**
* 属性def17的Setter方法.属性名：自定义项17
* 创建日期:2021-9-2
* @param newDef17 java.lang.String
*/
public void setDef17 ( java.lang.String def17) {
this.def17=def17;
} 
 
/**
* 属性 def18的Getter方法.属性名：自定义项18
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef18() {
return this.def18;
} 

/**
* 属性def18的Setter方法.属性名：自定义项18
* 创建日期:2021-9-2
* @param newDef18 java.lang.String
*/
public void setDef18 ( java.lang.String def18) {
this.def18=def18;
} 
 
/**
* 属性 def19的Getter方法.属性名：自定义项19
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef19() {
return this.def19;
} 

/**
* 属性def19的Setter方法.属性名：自定义项19
* 创建日期:2021-9-2
* @param newDef19 java.lang.String
*/
public void setDef19 ( java.lang.String def19) {
this.def19=def19;
} 
 
/**
* 属性 def20的Getter方法.属性名：自定义项20
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef20() {
return this.def20;
} 

/**
* 属性def20的Setter方法.属性名：自定义项20
* 创建日期:2021-9-2
* @param newDef20 java.lang.String
*/
public void setDef20 ( java.lang.String def20) {
this.def20=def20;
} 
 
/**
* 属性 def21的Getter方法.属性名：自定义项21
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef21() {
return this.def21;
} 

/**
* 属性def21的Setter方法.属性名：自定义项21
* 创建日期:2021-9-2
* @param newDef21 java.lang.String
*/
public void setDef21 ( java.lang.String def21) {
this.def21=def21;
} 
 
/**
* 属性 def22的Getter方法.属性名：自定义项22
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef22() {
return this.def22;
} 

/**
* 属性def22的Setter方法.属性名：自定义项22
* 创建日期:2021-9-2
* @param newDef22 java.lang.String
*/
public void setDef22 ( java.lang.String def22) {
this.def22=def22;
} 
 
/**
* 属性 def23的Getter方法.属性名：自定义项23
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef23() {
return this.def23;
} 

/**
* 属性def23的Setter方法.属性名：自定义项23
* 创建日期:2021-9-2
* @param newDef23 java.lang.String
*/
public void setDef23 ( java.lang.String def23) {
this.def23=def23;
} 
 
/**
* 属性 def24的Getter方法.属性名：自定义项24
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef24() {
return this.def24;
} 

/**
* 属性def24的Setter方法.属性名：自定义项24
* 创建日期:2021-9-2
* @param newDef24 java.lang.String
*/
public void setDef24 ( java.lang.String def24) {
this.def24=def24;
} 
 
/**
* 属性 def25的Getter方法.属性名：自定义项25
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef25() {
return this.def25;
} 

/**
* 属性def25的Setter方法.属性名：自定义项25
* 创建日期:2021-9-2
* @param newDef25 java.lang.String
*/
public void setDef25 ( java.lang.String def25) {
this.def25=def25;
} 
 
/**
* 属性 def26的Getter方法.属性名：自定义项26
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef26() {
return this.def26;
} 

/**
* 属性def26的Setter方法.属性名：自定义项26
* 创建日期:2021-9-2
* @param newDef26 java.lang.String
*/
public void setDef26 ( java.lang.String def26) {
this.def26=def26;
} 
 
/**
* 属性 def27的Getter方法.属性名：自定义项27
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef27() {
return this.def27;
} 

/**
* 属性def27的Setter方法.属性名：自定义项27
* 创建日期:2021-9-2
* @param newDef27 java.lang.String
*/
public void setDef27 ( java.lang.String def27) {
this.def27=def27;
} 
 
/**
* 属性 def28的Getter方法.属性名：自定义项28
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef28() {
return this.def28;
} 

/**
* 属性def28的Setter方法.属性名：自定义项28
* 创建日期:2021-9-2
* @param newDef28 java.lang.String
*/
public void setDef28 ( java.lang.String def28) {
this.def28=def28;
} 
 
/**
* 属性 def29的Getter方法.属性名：自定义项29
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef29() {
return this.def29;
} 

/**
* 属性def29的Setter方法.属性名：自定义项29
* 创建日期:2021-9-2
* @param newDef29 java.lang.String
*/
public void setDef29 ( java.lang.String def29) {
this.def29=def29;
} 
 
/**
* 属性 def30的Getter方法.属性名：自定义项30
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getDef30() {
return this.def30;
} 

/**
* 属性def30的Setter方法.属性名：自定义项30
* 创建日期:2021-9-2
* @param newDef30 java.lang.String
*/
public void setDef30 ( java.lang.String def30) {
this.def30=def30;
} 
 
/**
* 属性 salesman的Getter方法.属性名：业务员
*  创建日期:2021-9-2
* @return nc.vo.bd.psn.PsndocVO
*/
public java.lang.String getSalesman() {
return this.salesman;
} 

/**
* 属性salesman的Setter方法.属性名：业务员
* 创建日期:2021-9-2
* @param newSalesman nc.vo.bd.psn.PsndocVO
*/
public void setSalesman ( java.lang.String salesman) {
this.salesman=salesman;
} 
 
/**
* 属性 assgindate的Getter方法.属性名：开始时间
*  创建日期:2021-9-2
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getAssgindate() {
return this.assgindate;
} 

/**
* 属性assgindate的Setter方法.属性名：开始时间
* 创建日期:2021-9-2
* @param newAssgindate nc.vo.pub.lang.UFDate
*/
public void setAssgindate ( UFDate assgindate) {
this.assgindate=assgindate;
} 
 
/**
* 属性 enddate的Getter方法.属性名：结束时间
*  创建日期:2021-9-2
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getEnddate() {
return this.enddate;
} 

/**
* 属性enddate的Setter方法.属性名：结束时间
* 创建日期:2021-9-2
* @param newEnddate nc.vo.pub.lang.UFDate
*/
public void setEnddate ( UFDate enddate) {
this.enddate=enddate;
} 
 
/**
* 属性 task的Getter方法.属性名：指派任务内容
*  创建日期:2021-9-2
* @return java.lang.String
*/
public java.lang.String getTask() {
return this.task;
} 

/**
* 属性task的Setter方法.属性名：指派任务内容
* 创建日期:2021-9-2
* @param newTask java.lang.String
*/
public void setTask ( java.lang.String task) {
this.task=task;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2021-9-2
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2021-9-2
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("cy.taskallocationHVO");
    }
   }
    