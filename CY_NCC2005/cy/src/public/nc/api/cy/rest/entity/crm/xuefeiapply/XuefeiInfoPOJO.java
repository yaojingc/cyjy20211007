package nc.api.cy.rest.entity.crm.xuefeiapply;

import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.basepojo.TranslateSql;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;
/**
 * @ClassName XuefeiInfoPOJO
 * @Description TODO 学费减免申请单基本信息
 * @Author huangcong
 * @Date 2021/8/14 17:00
 * @Version 1.0
 **/
public class XuefeiInfoPOJO  implements ParentPojoTagItf{
	
	private String pk_xfapply;
	
	/**
	 * 学校档案（自定义字段参照学校档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_SCHOOLEXP)
	private School def1;
	
	/**
	 * 班级档案（自定义字段参照班级档案)
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_CLASSCY)
	private RefPOJO def3;
	
	/**
	 *学生档案（自定义字段参照学生档案）
	 */
	@ZdyTranslateAnnotation(type = 1, refQuerySql = TranslateSql.QUERYSQL_STUDENT)
	private RefPOJO def2;
	
	
	private String alltuition;
	
	private String reducation;
	
	private String amount;	
	
	private String img;	
	
	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	private String infonote;
	
	private String saddress;
	
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_REDUCTION)
	private RefPOJO def4;
	
	@ZdyTranslateAnnotation(type = 2, refQuerySql = TranslateSql.QUERYSQL_DEFDOC, defdocCode = CrmBaseTag.DEFDOC_ISCONSUMED)
	private RefPOJO def5;
	
	
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_UFBOOLEAN)
	private RefPOJO isupload;

	/**
	 * 审批状态
	 */
	
	@ZdyTranslateAnnotation(type = 3, enumTag = CrmBaseTag.ENUM_BILLSTATUS)
	private RefPOJO approvestatus;
	
	
	
	public RefPOJO getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(RefPOJO approvestatus) {
		this.approvestatus = approvestatus;
	}

	public String getPk_xfapply() {
		return pk_xfapply;
	}


	public void setPk_xfapply(String pk_xfapply) {
		this.pk_xfapply = pk_xfapply;
	}

	public School getDef1() {
		return def1;
	}


	public void setDef1(School def1) {
		this.def1 = def1;
	}


	public RefPOJO getDef2() {
		return def2;
	}


	public void setDef2(RefPOJO def2) {
		this.def2 = def2;
	}


	public RefPOJO getDef3() {
		return def3;
	}


	public void setDef3(RefPOJO def3) {
		this.def3 = def3;
	}


	public String getAlltuition() {
		return alltuition;
	}


	public void setAlltuition(String alltuition) {
		this.alltuition = alltuition;
	}


	public String getReducation() {
		return reducation;
	}


	public void setReducation(String reducation) {
		this.reducation = reducation;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getInfonote() {
		return infonote;
	}


	public void setInfonote(String infonote) {
		this.infonote = infonote;
	}


	public String getSaddress() {
		return saddress;
	}


	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}


	public RefPOJO getDef4() {
		return def4;
	}


	public void setDef4(RefPOJO def4) {
		this.def4 = def4;
	}


	public RefPOJO getIsupload() {
		return isupload;
	}


	public void setIsupload(RefPOJO isupload) {
		this.isupload = isupload;
	}


	public RefPOJO getDef5() {
		return def5;
	}


	public void setDef5(RefPOJO def5) {
		this.def5 = def5;
	}
	
	
}
