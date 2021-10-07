package nc.api.cy.rest.entity.crm.jiedaiapply;

import java.util.List;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;
import nc.api.cy.rest.entity.crm.workplan.WorkPlanBvoPOJO;
import nc.api.cy.rest.entity.crm.workplan.WorkPlanInfoPOJO;
/**
 * @ClassName JiedaiAggPOJO
 * @Description TODO 客情接待申请单查询响应实体
 * @Author huangcong
 * @Date 2021/8/16 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class JiedaiAggPOJO implements DeatilTagItf {

	/**
	 * 客情接待申请单
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.jiedaiapply.JiedaiapplyHVO")
	private JiedaiInfoPOJO jiedaiInfo;
	
	/**
     * 到访人员信息
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.jiedaiapply.JiedaiapplyvisitVO")
	private List<JiedaiVisitPOJO> id_jiedaiapplyvisitVO;
	
	/**
     * 考察效果维护
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.jiedaiapply.JiedaiapplykcVO")
	private List<JiedaiKcPOJO> id_jiedaiapplykcVO;
	
	/**
     *  行程安排维护
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.jiedaiapply.JiedaiapplyschVO")
	private List<JiedaiSchPOJO> id_jiedaiapplyschVO;

	public JiedaiInfoPOJO getJiedaiInfo() {
		return jiedaiInfo;
	}

	public void setJiedaiInfo(JiedaiInfoPOJO jiedaiInfo) {
		this.jiedaiInfo = jiedaiInfo;
	}

	public List<JiedaiVisitPOJO> getId_jiedaiapplyvisitVO() {
		return id_jiedaiapplyvisitVO;
	}

	public void setId_jiedaiapplyvisitVO(List<JiedaiVisitPOJO> id_jiedaiapplyvisitVO) {
		this.id_jiedaiapplyvisitVO = id_jiedaiapplyvisitVO;
	}

	public List<JiedaiKcPOJO> getId_jiedaiapplykcVO() {
		return id_jiedaiapplykcVO;
	}

	public void setId_jiedaiapplykcVO(List<JiedaiKcPOJO> id_jiedaiapplykcVO) {
		this.id_jiedaiapplykcVO = id_jiedaiapplykcVO;
	}

	public List<JiedaiSchPOJO> getId_jiedaiapplyschVO() {
		return id_jiedaiapplyschVO;
	}

	public void setId_jiedaiapplyschVO(List<JiedaiSchPOJO> id_jiedaiapplyschVO) {
		this.id_jiedaiapplyschVO = id_jiedaiapplyschVO;
	}
	
}
