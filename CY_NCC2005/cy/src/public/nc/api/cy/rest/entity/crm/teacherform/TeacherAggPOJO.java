package nc.api.cy.rest.entity.crm.teacherform;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;

/**
 * @ClassName TeacherAggPOJO
 * @Description TODO 老师、讲师申请单详情查询响应实体
 * @Author NCC
 * @Date 2021/7/6 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class TeacherAggPOJO implements DeatilTagItf {
	
	/**
	 * 老师/讲师申请单主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.teacherform.TeacherformHVO")
	private TeacherInfoPOJO teacherbaseinfo;

	public TeacherInfoPOJO getTeacherbaseinfo() {
		return teacherbaseinfo;
	}

	public void setTeacherbaseinfo(TeacherInfoPOJO teacherbaseinfo) {
		this.teacherbaseinfo = teacherbaseinfo;
	}
}
