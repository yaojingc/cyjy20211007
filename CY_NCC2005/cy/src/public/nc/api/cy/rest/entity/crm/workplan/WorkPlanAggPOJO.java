package nc.api.cy.rest.entity.crm.workplan;

import java.util.List;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;

/**
 * @ClassName WorkPlanAggPOJO
 * @Description TODO 工作计划查询响应实体
 * @Author huangcong
 * @Date 2021/8/16 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class WorkPlanAggPOJO implements DeatilTagItf {
	
	/**
	 * 工作计划主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.workplan.WorkplanHVO")
	private WorkPlanInfoPOJO workplaninfo;

	/**
     * 工作计划子表信息
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.workplan.WorkplanbVO")
	private List<WorkPlanBvoPOJO> id_workplanbvo;

	public WorkPlanInfoPOJO getWorkplaninfo() {
		return workplaninfo;
	}

	public void setWorkplaninfo(WorkPlanInfoPOJO workplaninfo) {
		this.workplaninfo = workplaninfo;
	}

	public List<WorkPlanBvoPOJO> getId_workplanbvo() {
		return id_workplanbvo;
	}

	public void setId_workplanbvo(List<WorkPlanBvoPOJO> id_workplanbvo) {
		this.id_workplanbvo = id_workplanbvo;
	}
}
