
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import nc.api.cy.rest.entity.crm.refpojo.RefDefDoc;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.itf.uap.pf.IPFBusiAction;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.CrmUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.itf.cy.cy.IDailyhvoMaintain;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.schoolarchives.SchoolBasicsBusinVO;
import nc.vo.cy.schoolarchives.SchoolBasicsClassVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.cy.schoolarchives.SchoolBasicsLinkVO;
import nc.vo.cy.schoolarchives.SchoolBasicsTopVO;
import nc.vo.cy.schoolarchives.SchoolBasicsVisitVO;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.cy.schoolform.SchoolformHVO;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.taskallocation.TaskallocationDetailVO;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.cy.workplan.WorkplanbVO;

/**
 * 标准单据审核的BP
 */
public class AceSchoolformHVOApproveBP {
	private static IPFBusiAction pfBusiAction = null;

	private static BaseDAO baseDao;

	private static IMDPersistenceQueryService aggvoQueryService;

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggSchoolformHVO[] approve(AggSchoolformHVO[] clientBills, AggSchoolformHVO[] originBills) {
		for (AggSchoolformHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSchoolformHVO> update = new BillUpdate<AggSchoolformHVO>();
		AggSchoolformHVO[] returnVos = update.update(clientBills, originBills);
		SchoolformHVO tvo = returnVos[0].getParentVO();
		try {
			// 审批时更新学校档案
			if (tvo.getApprovestatus() == 1) {
				updateSchool(returnVos[0]);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnVos;
	}

	/**
	 * 
	 * 申请单申批通过同步到学校档案
	 * 
	 * @author huangcong
	 * @param aggvo
	 * @return
	 */
	public void updateSchool(AggSchoolformHVO aggVO) throws BusinessException {
		try {
			SchoolformHVO tvo = aggVO.getParentVO();
			AggSchoolBasicsHVO aggvo = new AggSchoolBasicsHVO();
			SchoolBasicsHVO svo = new SchoolBasicsHVO();
			if (CyCommonUtils.isNotEmpty(tvo.getCity())) {
				svo.setCity(tvo.getCity());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getProvince())) {
				svo.setProvince(tvo.getProvince());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getCounty())) {
				svo.setCounty(tvo.getCounty());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getSphone())) {
				svo.setSphone(tvo.getSphone());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getDef1())) {
				svo.setDef11(tvo.getDef1());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getSchool())) {
				svo.setSname(tvo.getSchool());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getAddress())) {
				svo.setSaddress(tvo.getAddress());
			}
			if (CyCommonUtils.isNotEmpty(tvo.getVnote())) {
				svo.setVnote(tvo.getVnote());
			}
			svo.setIsuseing(new UFBoolean(false));
			String doc = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocPk( "未开发","cy05");
			if (CyCommonUtils.isNotEmpty(doc)) {
				svo.setDef12(doc);
			}
			aggvo.setParentVO(svo);
			aggvo.getParentVO().setAttributeValue("pk_group", aggVO.getParentVO().getPk_group());// 集团
			aggvo.getParentVO().setAttributeValue("pk_org", aggVO.getParentVO().getPk_org());// 组织
			aggvo.getParentVO().setAttributeValue("pk_org_v", aggVO.getParentVO().getPk_org_v());// 组织多版本
			aggvo.getParentVO().setAttributeValue("dbilldate", new UFDate());// 单据日期
			aggvo.getParentVO().setAttributeValue("billstatus", -1);// 单据状态（默认自由）
			aggvo.getParentVO().setAttributeValue("approvestatus", -1);// 审批状态（默认自由）
			aggvo.getParentVO().setAttributeValue("billmaker", aggVO.getParentVO().getBillmaker());// 制单人（用户表主键）
			aggvo.getParentVO().setAttributeValue("creator", aggVO.getParentVO().getCreator());// 创建人（用户表主键）
			aggvo.getParentVO().setAttributeValue("creationtime", new UFDateTime());// 创建时间
			aggvo.getParentVO().setStatus(VOStatus.NEW);
			SchoolBasicsLinkVO[] arr1 = new SchoolBasicsLinkVO[0];
			SchoolBasicsTopVO[] arr2 = new SchoolBasicsTopVO[0];
			SchoolBasicsVisitVO[] arr3 = new SchoolBasicsVisitVO[0];
			SchoolBasicsBusinVO[] arr4 = new SchoolBasicsBusinVO[0];
			SchoolBasicsClassVO[] arr5 = new SchoolBasicsClassVO[0];
			aggvo.setChildren(SchoolBasicsLinkVO.class, arr1);
			aggvo.setChildren(SchoolBasicsTopVO.class, arr2);
			aggvo.setChildren(SchoolBasicsVisitVO.class, arr3);
			aggvo.setChildren(SchoolBasicsBusinVO.class, arr4);
			aggvo.setChildren(SchoolBasicsClassVO.class, arr5);
			CrmUtil.setBillType(aggvo);
			CyCommonUtils.getInstance(ICrmService.class).pfSaveBusiAction(aggvo.getParentVO().getBilltype(), aggvo,
					false);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
