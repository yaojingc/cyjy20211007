package nc.api.cy.rest.entity.crm.taskallocation;

import java.util.List;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;
/**
 * @ClassName TaskallocationAggPOJO
 * @Description TODO 任务分配响应实体
 * @Author huangcong
 * @Date 2021/8/16 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class TaskallocationAggPOJO  implements DeatilTagItf{
	
	/**
	 * 任务分配主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.taskallocation.TaskallocationHVO")
	private TaskallocationInfoPOJO taskinfo;

	/**
     *  任务分配子表信息
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.taskallocation.TaskallocationDetailVO")
	private List<TaskallocationDvoPOJO> id_taskdvo;

	public TaskallocationInfoPOJO getTaskinfo() {
		return taskinfo;
	}

	public void setTaskinfo(TaskallocationInfoPOJO taskinfo) {
		this.taskinfo = taskinfo;
	}

	public List<TaskallocationDvoPOJO> getId_taskdvo() {
		return id_taskdvo;
	}

	public void setId_taskdvo(List<TaskallocationDvoPOJO> id_taskdvo) {
		this.id_taskdvo = id_taskdvo;
	}
	
	
}
