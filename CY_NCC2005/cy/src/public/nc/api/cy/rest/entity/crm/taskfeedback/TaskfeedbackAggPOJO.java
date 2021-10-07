package nc.api.cy.rest.entity.crm.taskfeedback;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;

@ZdyClassTagAnnotation(isMultiEntity = true)
public class TaskfeedbackAggPOJO implements DeatilTagItf{
	/**
	 * 任务分配反馈主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.taskfeedback.TaskfeedbackHVO")
	private TaskfeedbackInfoPOJO taskfeedbackInfo;

	public TaskfeedbackInfoPOJO getTaskfeedbackInfo() {
		return taskfeedbackInfo;
	}

	public void setTaskfeedbackInfo(TaskfeedbackInfoPOJO taskfeedbackInfo) {
		this.taskfeedbackInfo = taskfeedbackInfo;
	}	
}
