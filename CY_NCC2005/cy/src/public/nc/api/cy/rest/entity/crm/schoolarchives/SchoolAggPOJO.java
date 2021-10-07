package nc.api.cy.rest.entity.crm.schoolarchives;

import java.util.List;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.entity.crm.itf.DeatilTagItf;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;

/**
 * @ClassName SchoolAggPOJO
 * @Description TODO 学校详情查询响应实体
 * @Author NCC
 * @Date 2021/7/6 16:07
 * @Version 1.0
 **/
@ZdyClassTagAnnotation(isMultiEntity = true)
public class SchoolAggPOJO implements DeatilTagItf {

    /**
         * 学校档案主表信息
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.schoolarchives.SchoolBasicsHVO")
    private SchoolPOJO schoolbaseinfo;

    /**
     * 拜访记录
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.schoolarchives.SchoolBasicsVisitVO")
    private List<SchoolVisitPOJO> id_schoolbasicsvisitvo;

    /**
         * 年级情况
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.schoolarchives.SchoolBasicsClassVO")
    private List<SchoolGradeInfoPOJO> id_schoolbasicsclassvo;

    /**
         * 联系人信息
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.schoolarchives.SchoolBasicsLinkVO")
    private List<SchoolLinkerPOJO> id_schoolbasicslinkvo;

    /**
     * 高管学校维护记录
     */
	@ZdyFieldTagAnnotation(isEntityFiled=true, nccVO="nc.vo.cy.schoolarchives.SchoolBasicsTopVO")
    private List<SchoolInfoPOJO> id_schoolbasicstopvo;

	public SchoolPOJO getSchoolbaseinfo() {
		return schoolbaseinfo;
	}

	public void setSchoolbaseinfo(SchoolPOJO schoolbaseinfo) {
		this.schoolbaseinfo = schoolbaseinfo;
	}

	public List<SchoolVisitPOJO> getId_schoolbasicsvisitvo() {
		return id_schoolbasicsvisitvo;
	}

	public void setId_schoolbasicsvisitvo(List<SchoolVisitPOJO> id_schoolbasicsvisitvo) {
		this.id_schoolbasicsvisitvo = id_schoolbasicsvisitvo;
	}

	public List<SchoolGradeInfoPOJO> getId_schoolbasicsclassvo() {
		return id_schoolbasicsclassvo;
	}

	public void setId_schoolbasicsclassvo(List<SchoolGradeInfoPOJO> id_schoolbasicsclassvo) {
		this.id_schoolbasicsclassvo = id_schoolbasicsclassvo;
	}

	public List<SchoolLinkerPOJO> getId_schoolbasicslinkvo() {
		return id_schoolbasicslinkvo;
	}

	public void setId_schoolbasicslinkvo(List<SchoolLinkerPOJO> id_schoolbasicslinkvo) {
		this.id_schoolbasicslinkvo = id_schoolbasicslinkvo;
	}

	public List<SchoolInfoPOJO> getId_schoolbasicstopvo() {
		return id_schoolbasicstopvo;
	}

	public void setId_schoolbasicstopvo(List<SchoolInfoPOJO> id_schoolbasicstopvo) {
		this.id_schoolbasicstopvo = id_schoolbasicstopvo;
	}
}
