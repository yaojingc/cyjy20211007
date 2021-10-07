package nc.api.cy.rest.entity.crm.schoolform;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;

/**
 * @ClassName RestSchoolFindQuery
 * @Description TODO 新学校发现详情查询响应实体
 * @Author 黎兵
 * @Date 2021/7/9 9:19
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class SchoolFindAggPOJO implements DeatilTagItf {

	/**
	 * 新学校发现主表信息
	 */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.schoolform.SchoolformHVO")
	private SchoolFindInfoPOJO schoolfindbaseinfo;

	public SchoolFindInfoPOJO getSchoolfindbaseinfo() {
		return schoolfindbaseinfo;
	}

	public void setSchoolfindbaseinfo(SchoolFindInfoPOJO schoolfindbaseinfo) {
		this.schoolfindbaseinfo = schoolfindbaseinfo;
	}
}
