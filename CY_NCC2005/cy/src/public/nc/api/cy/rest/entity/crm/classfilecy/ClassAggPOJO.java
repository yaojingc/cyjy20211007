package nc.api.cy.rest.entity.crm.classfilecy;

import java.util.List;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;

/**
 * @ClassName ClassAggPOJO
 * @Description TODO 词源班级详情查询响应实体
 * @Author NCC
 * @Date 2021/7/6 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class ClassAggPOJO implements DeatilTagItf  {
	
	/**
	  * 词源班级主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.classfilecy.ClasscyHVO")
	private ClassInfoPOJO classbaseinfo;
	
	/**
         * 词源班级子表信息
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.classfilecy.ClasscyDetailVO")
	private List<ClassDetailPOJO> id_classcydetail;

	public ClassInfoPOJO getClassbaseinfo() {
		return classbaseinfo;
	}

	public void setClassbaseinfo(ClassInfoPOJO classbaseinfo) {
		this.classbaseinfo = classbaseinfo;
	}

	public List<ClassDetailPOJO> getId_classcydetail() {
		return id_classcydetail;
	}

	public void setId_classcydetail(List<ClassDetailPOJO> id_classcydetail) {
		this.id_classcydetail = id_classcydetail;
	}
}
