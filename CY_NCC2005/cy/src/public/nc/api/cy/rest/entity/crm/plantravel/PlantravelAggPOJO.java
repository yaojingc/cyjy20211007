package nc.api.cy.rest.entity.crm.plantravel;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;
import nc.api.cy.rest.entity.crm.jiedaiapply.JiedaiInfoPOJO;
/**
 * @ClassName PlantravelAggPOJO
 * @Description TODO 行程安排响应实体
 * @Author huangcong
 * @Date 2021/9/10 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class PlantravelAggPOJO implements DeatilTagItf{
	/**
	 * 行程安排
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.plantravel.PlantravelHVO")
	private PlantravelInfoPOJO plantravebaseinfo;
	
	/**
	 * 行程安排子表
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.plantravel.PlantravelBVO")
	private PlantravelBPOJO id_plantraveinfo;
	
	/**
	 * 行程安排考察维护
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.plantravel.PlantravelkcVO")
	private PlantravelKcPOJO id_kcxginfo;

	public PlantravelInfoPOJO getPlantravebaseinfo() {
		return plantravebaseinfo;
	}

	public void setPlantravebaseinfo(PlantravelInfoPOJO plantravebaseinfo) {
		this.plantravebaseinfo = plantravebaseinfo;
	}

	public PlantravelBPOJO getId_plantraveinfo() {
		return id_plantraveinfo;
	}

	public void setId_plantraveinfo(PlantravelBPOJO id_plantraveinfo) {
		this.id_plantraveinfo = id_plantraveinfo;
	}

	public PlantravelKcPOJO getId_kcxginfo() {
		return id_kcxginfo;
	}

	public void setId_kcxginfo(PlantravelKcPOJO id_kcxginfo) {
		this.id_kcxginfo = id_kcxginfo;
	}
	
}
